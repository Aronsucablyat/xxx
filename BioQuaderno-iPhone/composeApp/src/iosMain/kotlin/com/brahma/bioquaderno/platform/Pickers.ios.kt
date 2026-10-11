package com.brahma.bioquaderno.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData
import platform.Foundation.NSLock
import platform.Foundation.NSURL
import platform.Foundation.dataWithContentsOfURL
import platform.Foundation.create
import platform.Foundation.getBytes
import platform.PhotosUI.PHPickerConfiguration
import platform.PhotosUI.PHPickerFilter
import platform.PhotosUI.PHPickerResult
import platform.PhotosUI.PHPickerViewController
import platform.PhotosUI.PHPickerViewControllerDelegateProtocol
import platform.UIKit.UIApplication
import platform.UIKit.UIColor
import platform.UIKit.UIDocumentPickerDelegateProtocol
import platform.UIKit.UIDocumentPickerModeImport
import platform.UIKit.UIDocumentPickerViewController
import platform.UIKit.UIViewController
import platform.darwin.NSObject
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask

/**
 * Selettori di sistema per iPhone:
 * - foto di più immagini: PHPickerViewController (galleria, niente permessi)
 * - un documento PDF: UIDocumentPickerViewController
 * I file scelti vengono copiati subito in una cartella temporanea dell'app;
 * [Platform.copyPicked] poi li sposta nella posizione definitiva.
 */

@Composable
actual fun rememberPhotoPicker(onPicked: (List<String>) -> Unit): Launcher =
    remember { Launcher { PlatformPickers.presentPhotos { ids -> onPicked(ids) } } }

@Composable
actual fun rememberPdfPicker(onPicked: (String?) -> Unit): Launcher =
    remember { Launcher { PlatformPickers.presentPdf { path -> onPicked(path) } } }

private object PlatformPickers {

    private var photoDelegate: PhotoPickerDelegate? = null
    private var pdfDelegate: PdfPickerDelegate? = null

    fun presentPhotos(onDone: (List<String>) -> Unit) {
        val config = PHPickerConfiguration()
        config.selectionLimit = 10
        config.filter = PHPickerFilter.imagesFilter
        val picker = PHPickerViewController(configuration = config)
        photoDelegate = PhotoPickerDelegate { paths -> onDone(paths) }
        picker.delegate = photoDelegate
        topViewController()?.presentViewController(picker, animated = true, completion = null)
    }

    fun presentPdf(onDone: (String?) -> Unit) {
        // UTI "com.adobe.pdf" come stringa: API classica, niente tipi solo-Swift
        val picker = UIDocumentPickerViewController(
            documentTypes = listOf("com.adobe.pdf"),
            inMode = UIDocumentPickerModeImport
        )
        pdfDelegate = PdfPickerDelegate { path -> onDone(path) }
        picker.delegate = pdfDelegate
        topViewController()?.presentViewController(picker, animated = true, completion = null)
    }

    private fun topViewController(): UIViewController? {
        var top = UIApplication.sharedApplication.keyWindow?.rootViewController
        while (top?.presentedViewController != null) top = top.presentedViewController
        return top
    }
}

@OptIn(ExperimentalForeignApi::class)
private class PhotoPickerDelegate(
    private val onDone: (List<String>) -> Unit
) : NSObject(), PHPickerViewControllerDelegateProtocol {

    override fun picker(picker: PHPickerViewController, didFinishPicking: List<*>) {
        picker.dismissViewControllerAnimated(true, completion = null)
        val results = didFinishPicking.filterIsInstance<PHPickerResult>()
        if (results.isEmpty()) {
            onMainThread { onDone(emptyList()) }
            return
        }
        val tmpDir = Platform.appDataDir() + "/tmp"
        // Un posto fisso per ogni foto scelta: le letture possono finire in ordine sparso
        val slots = arrayOfNulls<String>(results.size)
        val lock = NSLock()
        var remaining = results.size
        results.forEachIndexed { idx, result ->
            result.itemProvider.loadDataRepresentationForTypeIdentifier("public.image") { data, _ ->
                val bytes = data?.toByteArrayShared2()
                if (bytes != null) {
                    val dest = "$tmpDir/pick_${System.currentTimeMillis()}_$idx.jpg"
                    Platform.writeBytes(dest, bytes)
                    slots[idx] = dest
                }
                lock.lock()
                remaining -= 1
                val finished = remaining == 0
                lock.unlock()
                if (finished) {
                    onMainThread { onDone(slots.filterNotNull()) }
                }
            }
        }
    }
}

private class PdfPickerDelegate(
    private val onDone: (String?) -> Unit
) : NSObject(), UIDocumentPickerDelegateProtocol {

    override fun documentPicker(controller: UIDocumentPickerViewController, didPickDocumentsAtURLs: List<*>) {
        val url = didPickDocumentsAtURLs.firstOrNull() as? NSURL
        if (url == null) {
            onMainThread { onDone(null) }
            return
        }
        val accessed = url.startAccessingSecurityScopedResource()
        val bytes = NSData.dataWithContentsOfURL(url)?.toByteArrayShared2()
        if (accessed) url.stopAccessingSecurityScopedResource()
        if (bytes == null) {
            onMainThread { onDone(null) }
            return
        }
        val dest = "${Platform.appDataDir()}/tmp/pick_pdf_${System.currentTimeMillis()}.pdf"
        Platform.writeBytes(dest, bytes)
        onMainThread { onDone(dest) }
    }

    override fun documentPickerWasCancelled(controller: UIDocumentPickerViewController) {
        onMainThread { onDone(null) }
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun NSData.toByteArrayShared2(): ByteArray {
    val len = length.toInt()
    return ByteArray(len).apply {
        if (len > 0) usePinned { p -> getBytes(p.addressOf(0), length) }
    }
}

private fun onMainThread(block: () -> Unit) {
    dispatch_async(dispatch_get_main_queue()) { block() }
}