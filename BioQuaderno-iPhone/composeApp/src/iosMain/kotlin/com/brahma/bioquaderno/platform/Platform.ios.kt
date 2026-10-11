package com.brahma.bioquaderno.platform

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.viewinterop.UIKitView
import com.brahma.bioquaderno.ui.components.ScreenHeader
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.useContents
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import org.jetbrains.skia.Image
import platform.CoreGraphics.CGSizeMake
import platform.Foundation.NSData
import platform.Foundation.NSHTTPURLResponse
import platform.Foundation.NSMutableURLRequest
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.Foundation.NSURLSession
import platform.Foundation.NSString
import platform.Foundation.NSUserDefaults
import platform.Foundation.NSUUID
import platform.Foundation.dataWithContentsOfURL
import platform.Foundation.create
import platform.Foundation.dataUsingEncoding
import platform.Foundation.stringForKey
import platform.Foundation.NSUTF8StringEncoding
import platform.PDFKit.PDFDocument
import platform.PDFKit.kPDFDisplayBoxMediaBox
import platform.UIKit.UIImage
import platform.UIKit.UIImageJPEGRepresentation
import platform.Vision.VNImageRequestHandler
import platform.Vision.VNRecognizeTextRequest
import platform.Vision.VNRecognizedText
import platform.Vision.VNRecognizedTextObservation
import platform.Vision.VNRequestTextRecognitionLevelAccurate
import platform.WebKit.WKWebView
import kotlin.coroutines.resume
import platform.Foundation.NSFileManager
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSTemporaryDirectory
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSUserDomainMask
import platform.Foundation.fileExistsAtPath
import platform.Foundation.createDirectoryAtPath
import platform.Foundation.contentsOfDirectoryAtPath
import platform.Foundation.removeItemAtPath
import platform.Foundation.writeToFile

/**
 * Implementazione iPhone dei servizi di piattaforma:
 * NSUserDefaults, file in Documents, Vision (OCR), PDFKit, NSURLSession,
 * WKWebView. (Richiede macOS/Xcode per compilare.)
 */
@OptIn(ExperimentalForeignApi::class)
actual object Platform {

    private val fm = NSFileManager.defaultManager

    actual fun prefs(): Prefs = object : Prefs {
        private val defaults = NSUserDefaults.standardUserDefaults
        override fun get(key: String): String? = defaults.stringForKey(key)
        override fun put(key: String, value: String) = defaults.setObject(value, forKey = key)
    }

    actual fun appDataDir(): String {
        val docs = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, true)
            .firstOrNull() as? String ?: NSTemporaryDirectory()
        val dir = "$docs/bioquaderno"
        if (!fm.fileExistsAtPath(dir)) fm.createDirectoryAtPath(dir, true, null, null)
        return dir
    }

    actual fun writeBytes(path: String, data: ByteArray) {
        val dir = path.substringBeforeLast('/')
        if (!fm.fileExistsAtPath(dir)) fm.createDirectoryAtPath(dir, true, null, null)
        data.usePinned { pinned ->
            NSData.create(bytes = pinned.addressOf(0), length = data.size.toULong())
                .writeToFile(path, true)
        }
    }

    actual fun readBytes(path: String): ByteArray? {
        val ns = NSData.dataWithContentsOfURL(NSURL.fileURLWithPath(path))
            ?: throw IllegalArgumentException("file non leggibile")
        return ns.toByteArrayShared()
    }

    actual fun writeText(path: String, text: String) = writeBytes(path, text.encodeToByteArray())

    actual fun readText(path: String): String? =
        runCatching { readBytes(path)?.decodeToString() }.getOrNull()

    actual fun delete(path: String) {
        fm.removeItemAtPath(path, null)
    }

    actual fun listFiles(dir: String): List<String> {
        val items = try { fm.contentsOfDirectoryAtPath(dir, null) ?: emptyList() } catch (e: Exception) { emptyList() }
        return items.mapNotNull { (it as? String)?.substringAfterLast('/') }.sorted()
    }

    actual fun copyPicked(source: String, dest: String): Boolean {
        val bytes = runCatching { readBytes(source) }.getOrNull() ?: return false
        writeBytes(dest, bytes)
        return true
    }

    actual suspend fun ocr(imageBytes: ByteArray): String = withContext(Dispatchers.Default) {
        ocrData(imageBytes.toNSDataShared())
    }

    actual suspend fun pdfExtract(
        pdfPath: String,
        outDir: String,
        onPage: suspend (done: Int, total: Int) -> Unit
    ): Int = withContext(Dispatchers.Default) {
        val doc = PDFDocument(URL = NSURL.fileURLWithPath(pdfPath))
            ?: throw IllegalArgumentException("PDF non apribile (forse è protetto da DRM)")
        val total = doc.pageCount.toInt()
        for (i in 0 until total) {
            val page = doc.pageAtIndex(i.toULong()) ?: continue
            val img = page.thumbnailOfSize(CGSizeMake(1240.0, 1754.0), forBox = kPDFDisplayBoxMediaBox)
            val data = UIImageJPEGRepresentation(img, 0.8)
            val text = if (data != null) ocrData(data) else ""
            writeText("$outDir/${(i + 1).toString().padStart(4, '0')}.txt", text)
            onPage(i + 1, total)
        }
        total
    }

    actual suspend fun httpPostJson(
        url: String,
        headers: Map<String, String>,
        body: String
    ): PlatformHttpResponse = suspendCancellableCoroutine { cont ->
        val nsurl = NSURL.URLWithString(url)
        if (nsurl == null) {
            if (cont.isActive) cont.resume(PlatformHttpResponse(-1, ""))
            return@suspendCancellableCoroutine
        }
        val req = NSMutableURLRequest.requestWithURL(nsurl)
        req.HTTPMethod = "POST"
        req.HTTPBody = (body as NSString).dataUsingEncoding(NSUTF8StringEncoding)
        headers.forEach { (k, v) -> req.setValue(v, forHTTPHeaderField = k) }
        val task = NSURLSession.sharedSession.dataTaskWithRequest(req) { data, response, _ ->
            val http = response as? NSHTTPURLResponse
            val code = http?.statusCode?.toInt() ?: -1
            val text = if (data != null)
                (NSString.create(data = data, encoding = NSUTF8StringEncoding) as String?) else null
            if (cont.isActive) cont.resume(PlatformHttpResponse(code, text ?: ""))
        }
        task.resume()
    }

    // ------------------- OCR con Vision (Apple) -------------------

    private fun ocrData(data: NSData): String {
        val ui = UIImage.imageWithData(data) ?: return ""
        val cg = ui.CGImage ?: return ""
        val request = VNRecognizeTextRequest(completionHandler = { _, _ -> })
        request.recognitionLevel = VNRequestTextRecognitionLevelAccurate
        request.usesLanguageCorrection = true
        val handler = VNImageRequestHandler(cg, options = emptyMap<Any?, Any?>())
        val results = try {
            handler.performRequests(listOf(request), error = null)
            request.results ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }

        data class RectD(val x: Double, val y: Double, val width: Double, val height: Double)
        data class Obs(val yMid: Double, val xMin: Double, val xMax: Double, val text: String)
        val items = results.mapNotNull { r ->
            val obs = r as? VNRecognizedTextObservation ?: return@mapNotNull null
            val s = (obs.topCandidates(1u).firstOrNull() as? VNRecognizedText)?.string ?: return@mapNotNull null
            // Coordinata normalizzata (0..1) con origine in basso a sinistra
            val (x0, y0, w0, h0) = obs.boundingBox.useContents {
                RectD(origin.x, origin.y, size.width, size.height)
            }
            Obs(
                yMid = y0 + h0 / 2.0,
                xMin = x0,
                xMax = x0 + w0,
                text = s
            )
        }.toMutableList()

        // Vision usa coordinate con origine in basso a sinistra: il testo in alto
        // ha y maggiore. Riordina in ordine di lettura (alto → basso, sinistra → destra).
        items.sortWith(compareByDescending<Obs> { it.yMid }.thenBy { it.xMin })
        if (items.isEmpty()) return ""

        val sb = StringBuilder()
        var prev: Obs? = null
        for (o in items) {
            if (prev == null) {
                sb.append(o.text)
            } else {
                val sameRow = kotlin.math.abs(o.yMid - prev.yMid) < 0.02
                val drop = prev.yMid - o.yMid
                val joined = prev.xMax <= o.xMin  // colonna a destra sulla stessa riga
                if (sameRow && joined) {
                    sb.append("  ").append(o.text)
                } else if (sameRow) {
                    sb.append(" ").append(o.text)
                } else if (drop > 0.04) {
                    sb.append("\n\n").append(o.text)
                } else {
                    sb.append("\n").append(o.text)
                }
            }
            prev = o
        }
        return sb.toString().trim()
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun ByteArray.toNSDataShared(): NSData = usePinned { pinned ->
        NSData.create(bytes = pinned.addressOf(0), length = size.toULong())
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun NSData.toByteArrayShared(): ByteArray {
        val len = length.toInt()
        return ByteArray(len).apply {
            if (len > 0) usePinned { p -> getBytes(p.addressOf(0), length) }
        }
    }
}

@Composable
actual fun platformImage(bytes: ByteArray): ImageBitmap? =
    remember(bytes) { runCatching { Image.makeFromEncoded(bytes).toComposeImageBitmap() }.getOrNull() }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun WebViewHost(url: String, onBack: () -> Unit, modifier: Modifier) {
    Column(modifier.fillMaxSize()) {
        ScreenHeader(title = "Piattaforma libro", subtitle = url, onBack = onBack)
        UIKitView(
            factory = {
                WKWebView().apply {
                    loadRequest(NSURLRequest.requestWithURL(NSURL.URLWithString(url)!!))
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}