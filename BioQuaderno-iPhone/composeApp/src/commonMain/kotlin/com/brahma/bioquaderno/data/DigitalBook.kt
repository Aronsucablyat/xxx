package com.brahma.bioquaderno.data

import com.brahma.bioquaderno.platform.Platform
import com.brahma.bioquaderno.platform.Prefs
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/** Stato dell'analisi di un libro digitale importato. */
enum class BookStatus { PROCESSING, READY, FAILED }

/**
 * Un libro di testo digitale importato dentro l'app.
 * Il PDF viene copiato nella memoria interna; le pagine estratte (testo OCR)
 * vengono salvate come file di testo e il Prof le consulta per rispondere.
 */
@Serializable
data class DigitalBook(
    val id: String,
    val title: String,
    val fileName: String,
    val pageCount: Int,
    val processed: Int,
    val status: BookStatus,
    val date: Long = System.currentTimeMillis()
)

/** Salvataggio locale dei libri digitali (stesso formato su Android e iPhone). */
object DigitalBookStore {
    private const val KEY = "books"
    private val json = Json { ignoreUnknownKeys = true }

    fun load(prefs: Prefs): List<DigitalBook> {
        val raw = prefs.get(KEY) ?: return emptyList()
        return runCatching { json.decodeFromString<List<DigitalBook>>(raw) }.getOrDefault(emptyList())
    }

    fun save(prefs: Prefs, books: List<DigitalBook>) {
        prefs.put(KEY, json.encodeToString(books))
    }

    fun bookDir(book: DigitalBook): String = "${Platform.appDataDir()}/books/${book.id}"

    fun pdfPath(book: DigitalBook): String = "${bookDir(book)}/${book.fileName}"

    fun pagesDir(book: DigitalBook): String = "${bookDir(book)}/pages"

    /**
     * Copia il PDF scelto nella memoria interna e prepara tutto per l'analisi.
     * @return il libro creato, o null se non si riesce a leggere il file
     */
    fun importPdf(picked: String): DigitalBook? {
        val id = "book${System.currentTimeMillis()}"
        val name = picked.substringAfterLast('/')
            .substringAfterLast(':')
            .takeIf { it.isNotBlank() && it.contains(".") && !it.contains("document") }
            ?.let { if (it.endsWith(".pdf", true)) it else "$it.pdf" }
            ?: "libro.pdf"
        val dest = "${bookDir(DigitalBook(id, "tmp", name, 0, 0, BookStatus.PROCESSING))}/$name"
        if (!Platform.copyPicked(picked, dest)) return null
        val title = name.removeSuffix(".pdf").replace('_', ' ').replace('-', ' ')
            .replace(Regex("\\s+"), " ").trim().ifBlank { "Libro digitale" }
        return DigitalBook(id = id, title = title, fileName = name, pageCount = 0, processed = 0, status = BookStatus.PROCESSING)
    }

    /** File di testo delle pagine già estratte, in ordine. */
    fun pageFiles(book: DigitalBook): List<String> =
        Platform.listFiles(pagesDir(book)).filter { it.endsWith(".txt") }

    /** Il testo delle pagine come lista (indice = numero di pagina - 1). */
    fun readPages(book: DigitalBook): List<String> =
        pageFiles(book).mapNotNull { Platform.readText("${pagesDir(book)}/$it") }

    /** Elimina PDF, pagine e metadati (i metadati vanno tolti anche dalla lista). */
    fun delete(book: DigitalBook) = Platform.delete(bookDir(book))
}