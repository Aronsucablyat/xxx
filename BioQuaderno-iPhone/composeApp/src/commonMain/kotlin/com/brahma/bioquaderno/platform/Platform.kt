package com.brahma.bioquaderno.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap

/**
 * Piccolo archivio chiave/valore dell'app.
 * Su Android usa SharedPreferences, su iPhone NSUserDefaults.
 */
interface Prefs {
    fun get(key: String): String?

    fun put(key: String, value: String)
}

fun Prefs.getInt(key: String, def: Int): Int = get(key)?.toIntOrNull() ?: def

fun Prefs.putInt(key: String, value: Int) = put(key, value.toString())

class PlatformHttpResponse(val code: Int, val body: String)

/** Un'azione avviabile (es. aprire il selettore di foto o di PDF). */
fun interface Launcher {
    fun launch()
}

/**
 * Servizi di piattaforma forniti da Android (androidMain) e iPhone (iosMain).
 * Tutte le schermate comuni usano SOLO queste funzioni: così il codice è identico
 * per le due versioni dell'app.
 */
expect object Platform {

    /** L'archivio chiave/valore persistente dell'app. */
    fun prefs(): Prefs

    /** Cartella privata dell'app (Android: filesDir, iPhone: Documents). */
    fun appDataDir(): String

    fun writeBytes(path: String, data: ByteArray)

    fun readBytes(path: String): ByteArray?

    fun writeText(path: String, text: String)

    fun readText(path: String): String?

    fun delete(path: String)

    /** Nomi dei file in una cartella, in ordine alfabetico. */
    fun listFiles(dir: String): List<String>

    /**
     * Copia un file scelto dal selettore di sistema (foto o PDF) nella memoria
     * dell'app. [source] è l'identificatore restituito dal picker
     * (su Android: content URI; su iPhone: percorso del file temporaneo).
     */
    fun copyPicked(source: String, dest: String): Boolean

    /** Riconoscimento del testo in un'immagine (OCR on-device, offline). */
    suspend fun ocr(imageBytes: ByteArray): String

    /** Estrae il testo di un PDF pagina per pagina, salvandolo in [outDir]. */
    suspend fun pdfExtract(
        pdfPath: String,
        outDir: String,
        onPage: suspend (done: Int, total: Int) -> Unit
    ): Int

    /** Chiamata POST JSON via HTTP(S) (usata per la Gemini API). */
    suspend fun httpPostJson(url: String, headers: Map<String, String>, body: String): PlatformHttpResponse
}

/** Selettore di più foto (galleria). [onPicked] riceve gli identificatori scelti. */
@Composable
expect fun rememberPhotoPicker(onPicked: (List<String>) -> Unit): Launcher

/** Selettore di un documento PDF. [onPicked] riceve l'identificatore o null. */
@Composable
expect fun rememberPdfPicker(onPicked: (String?) -> Unit): Launcher

/** Decodifica dei byte di un'immagine per mostrarla (JPEG/PNG). */
@Composable
expect fun platformImage(bytes: ByteArray): ImageBitmap?

/** Finestra web in-app (Android: WebView, iPhone: WKWebView). */
@Composable
expect fun WebViewHost(url: String, onBack: () -> Unit, modifier: Modifier = Modifier)