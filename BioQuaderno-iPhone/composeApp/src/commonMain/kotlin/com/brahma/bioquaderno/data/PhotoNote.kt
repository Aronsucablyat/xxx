package com.brahma.bioquaderno.data

import com.brahma.bioquaderno.platform.Platform
import com.brahma.bioquaderno.platform.Prefs
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Una foto di appunti insieme al testo estratto dal modello OCR.
 * L'immagine viene copiata nella memoria interna dell'app, il testo (ed i metadati)
 * vengono salvati nelle preferenze come JSON. Stesso formato su Android e iPhone.
 */
@Serializable
data class PhotoNote(
    val id: Long,
    val imagePath: String,
    val text: String,
    val date: Long = System.currentTimeMillis()
)

/** Salvataggio locale delle note-foto. */
object PhotoNoteStore {
    private const val KEY = "photo_notes"
    private val json = Json { ignoreUnknownKeys = true }

    fun load(prefs: Prefs): List<PhotoNote> {
        val raw = prefs.get(KEY) ?: return emptyList()
        return runCatching { json.decodeFromString<List<PhotoNote>>(raw) }.getOrDefault(emptyList())
    }

    fun save(prefs: Prefs, notes: List<PhotoNote>) {
        prefs.put(KEY, json.encodeToString(notes))
    }

    /**
     * Copia l'immagine scelta (selettore di sistema) in un file interno.
     * @return il percorso assoluto del file copiato, o null se non riuscito
     */
    fun importImage(picked: String): String? {
        val dest = "${Platform.appDataDir()}/photos/note_${System.currentTimeMillis()}.jpg"
        return if (Platform.copyPicked(picked, dest)) dest else null
    }

    /** Elimina il file immagine di una nota (i metadati vanno tolti dalla lista). */
    fun deleteFile(note: PhotoNote) = Platform.delete(note.imagePath)
}