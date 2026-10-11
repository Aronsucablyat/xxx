package com.brahma.bioquaderno.platform

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.viewinterop.AndroidView
import com.brahma.bioquaderno.ui.components.ScreenHeader
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.io.File
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.resume

/**
 * Implementazione Android dei servizi di piattaforma:
 * SharedPreferences, file interni, ML Kit (OCR), PdfRenderer, HttpURLConnection,
 * selettori foto/PDF, WebView.
 */
actual object Platform {
    private lateinit var appContext: Context

    /** Da chiamare in MainActivity prima di mostrare l'UI. */
    fun init(context: Context) {
        appContext = context.applicationContext
    }

    actual fun prefs(): Prefs = AndroidPrefs(appContext)

    actual fun appDataDir(): String = appContext.filesDir.absolutePath

    actual fun writeBytes(path: String, data: ByteArray) {
        val file = File(path)
        file.parentFile?.mkdirs()
        file.writeBytes(data)
    }

    actual fun readBytes(path: String): ByteArray? =
        runCatching { File(path).readBytes() }.getOrNull()

    actual fun writeText(path: String, text: String) = writeBytes(path, text.encodeToByteArray())

    actual fun readText(path: String): String? = readBytes(path)?.decodeToString()

    actual fun delete(path: String) {
        runCatching { File(path).deleteRecursively() }
    }

    actual fun listFiles(dir: String): List<String> =
        runCatching { File(dir).listFiles()?.map { it.name }?.sorted() ?: emptyList() }
            .getOrDefault(emptyList())

    actual fun copyPicked(source: String, dest: String): Boolean =
        try {
            val input = appContext.contentResolver.openInputStream(Uri.parse(source)) ?: return false
            val file = File(dest)
            file.parentFile?.mkdirs()
            input.use { ins -> file.outputStream().use { out -> ins.copyTo(out) } }
            true
        } catch (e: Exception) {
            false
        }

    actual suspend fun ocr(imageBytes: ByteArray): String = withContext(Dispatchers.IO) {
        val bitmap = runCatching { BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size) }.getOrNull()
        recognizeBitmap(bitmap)
    }

    actual suspend fun pdfExtract(
        pdfPath: String,
        outDir: String,
        onPage: suspend (done: Int, total: Int) -> Unit
    ): Int = withContext(Dispatchers.Default) {
        val outFile = File(outDir).apply { mkdirs() }
        val fd = ParcelFileDescriptor.open(File(pdfPath), ParcelFileDescriptor.MODE_READ_ONLY)
        val renderer = PdfRenderer(fd)
        val total = renderer.pageCount
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
        try {
            for (i in 0 until total) {
                val page = renderer.openPage(i)
                val scale = 1240f / page.width.toFloat().coerceAtLeast(1f)
                val w = (page.width * scale).toInt().coerceAtLeast(1)
                val h = (page.height * scale).toInt().coerceAtLeast(1)
                val bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
                // RENDER_MODE_FOR_PRINT: qualità alta, testo più nitido per l'OCR
                page.render(bmp, null, null, PdfRenderer.Page.RENDER_MODE_FOR_PRINT)
                page.close()
                val text = recognizeBitmap(bmp, recognizer)
                File(outFile, "%04d.txt".format(i + 1)).writeText(text.trim())
                onPage(i + 1, total)
            }
            total
        } finally {
            runCatching { recognizer.close() }
            runCatching { renderer.close() }
            runCatching { fd.close() }
        }
    }

    actual suspend fun httpPostJson(
        url: String,
        headers: Map<String, String>,
        body: String
    ): PlatformHttpResponse = withContext(Dispatchers.IO) {
        val conn = URL(url).openConnection() as HttpURLConnection
        try {
            conn.requestMethod = "POST"
            headers.forEach { (k, v) -> conn.setRequestProperty(k, v) }
            conn.doOutput = true
            conn.connectTimeout = 25000
            conn.readTimeout = 45000
            conn.outputStream.use { it.write(body.toByteArray(Charsets.UTF_8)) }
            val code = conn.responseCode
            val stream = if (code in 200..299) conn.inputStream else conn.errorStream
            val response = stream?.bufferedReader(Charsets.UTF_8)?.use { it.readText() } ?: ""
            PlatformHttpResponse(code, response)
        } finally {
            conn.disconnect()
        }
    }

    private suspend fun recognizeBitmap(bitmap: Bitmap?, recognizer: TextRecognizer? = null): String {
        if (bitmap == null) return ""
        val owned = recognizer == null
        val engine = recognizer ?: TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
        return try {
            suspendCancellableCoroutine { cont ->
                engine.process(InputImage.fromBitmap(bitmap, 0))
                    .addOnSuccessListener { r -> if (cont.isActive) cont.resume(cleanOcrText(r)) }
                    .addOnFailureListener { if (cont.isActive) cont.resume("") }
            }
        } finally {
            if (owned) engine.close()
            bitmap.recycle()
        }
    }
}

/**
 * Ricostruisce il testo di un'immagine nell'ordine di lettura corretto
 * (alto→basso, sinistra→destra). ML Kit restituisce i blocchi in un ordine
 * legato al layout: su colonne/tabelle i testi escono incasinati. Qui si
 * riordinano le righe usando i bounding box.
 */
private fun cleanOcrText(result: Text): String {
    data class LineData(val top: Int, val bottom: Int, val left: Int, val right: Int, val text: String)

    val lines = mutableListOf<LineData>()
    for (block in result.textBlocks) {
        for (l in block.lines) {
            val bb = l.boundingBox ?: continue
            lines += LineData(bb.top, bb.bottom, bb.left, bb.right, l.text)
        }
    }
    lines.sortWith(compareBy({ it.top }, { it.left }))
    if (lines.isEmpty()) {
        return result.text.orEmpty().trim()
    }

    val sb = StringBuilder()
    var prev: LineData? = null
    for (l in lines) {
        if (prev == null) {
            sb.append(l.text)
        } else {
            val h = (l.bottom - l.top).coerceAtLeast(1)
            val ph = (prev.bottom - prev.top).coerceAtLeast(1)
            val hMax = maxOf(h, ph)
            val sameRow = l.top < prev.bottom + hMax / 3
            val rowGap = l.top - prev.bottom
            val paragraph = rowGap > hMax * 1.6f && !sameRow
            val newLine = rowGap > hMax * 0.55f && !sameRow
            when {
                sameRow -> sb.append("  ").append(l.text)
                paragraph -> sb.append("\n\n").append(l.text)
                newLine -> sb.append("\n").append(l.text)
                else -> sb.append(" ").append(l.text)
            }
        }
        prev = l
    }
    return sb.toString().trim()
}

private class AndroidPrefs(private val context: Context) : Prefs {
    private val sp = context.getSharedPreferences("bio_prefs", Context.MODE_PRIVATE)
    override fun get(key: String): String? = sp.getString(key, null)
    override fun put(key: String, value: String) = sp.edit().putString(key, value).apply()
}

private const val MAX_PHOTOS = 10

@Composable
actual fun rememberPhotoPicker(onPicked: (List<String>) -> Unit): Launcher {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.PickMultipleVisualMedia(MAX_PHOTOS)
    ) { uris ->
        if (uris.isNotEmpty()) onPicked(uris.map { it.toString() })
    }
    return remember { Launcher { launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) } }
}

@Composable
actual fun rememberPdfPicker(onPicked: (String?) -> Unit): Launcher {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri -> onPicked(uri?.toString()) }
    return remember { Launcher { launcher.launch(arrayOf("application/pdf")) } }
}

@Composable
actual fun platformImage(bytes: ByteArray): ImageBitmap? {
    val bitmap = remember(bytes) {
        runCatching { BitmapFactory.decodeByteArray(bytes, 0, bytes.size) }.getOrNull()
    }
    return bitmap?.asImageBitmap()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun WebViewHost(url: String, onBack: () -> Unit, modifier: Modifier) {
    Column(modifier.fillMaxSize()) {
        ScreenHeader(title = "Piattaforma libro", subtitle = url, onBack = onBack)
        AndroidView(
            factory = { ctx ->
                WebView(ctx).apply {
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    loadUrl(url)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}