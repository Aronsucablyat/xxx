package com.brahma.bioquaderno.data

/**
 * Una materia dell'istituto tecnico, con il suo contenuto completo:
 * teoria (capitoli), glossario, flashcard e quiz. Tutto offline, in italiano.
 */
data class Subject(
    val id: String,
    val name: String,
    val emoji: String,
    val indirizzo: String,
    val colorHex: Long,
    val chapters: List<Chapter>,
    val glossary: List<GlossaryTerm>,
    val flashcards: List<Flashcard>,
    val questions: List<Question>
) {
    val topicNames: List<String> get() = questions.map { it.topic }.distinct()
    val flashTopics: List<String> get() = flashcards.map { it.topic }.distinct()
}

/** Tutte le materie disponibili e le comodità per cercarci dentro. */
object Subjects {

    val all: List<Subject> by lazy {
        assignUniqueChapterIds(biennioSubjects() + triennioASubjects() + triennioBSubjects())
    }

    fun byId(id: String?): Subject? = all.firstOrNull { it.id == id }

    fun of(indirizzo: String): List<Subject> = all.filter { it.indirizzo == indirizzo }

    /** Le materie "accese" dall'utente. */
    fun enabled(allSubjects: List<Subject>, enabledIds: Set<String>): List<Subject> =
        allSubjects.filter { it.id in enabledIds }

    /** Ricerca nei capitoli delle materie date. */
    fun searchChapters(allSubjects: List<Subject>, q: String): List<Chapter> {
        val query = q.trim().lowercase().trim()
        val list = allSubjects.flatMap { s -> s.chapters }
        if (query.isEmpty()) return list
        return list.filter {
            it.title.lowercase().contains(query) ||
                it.summary.lowercase().contains(query) ||
                it.sections.any { sec ->
                    sec.title.lowercase().contains(query) ||
                        sec.blocks.any { b -> b.text().lowercase().contains(query) }
                }
        }
    }

    /** Ricerca nel glossario delle materie date, in ordine alfabetico. */
    fun searchGlossary(allSubjects: List<Subject>, q: String): List<GlossaryTerm> {
        val query = q.trim().lowercase()
        val list = allSubjects.flatMap { s -> s.glossary }
        return if (query.isEmpty()) list.sortedBy { it.term.lowercase() }
        else list.filter {
            it.term.lowercase().contains(query) || it.def.lowercase().contains(query)
        }.sortedBy { it.term.lowercase() }
    }

    /** Rinumera i capitoli con id univoci globali (per i segnaposto "studiato"). */
    private fun assignUniqueChapterIds(list: List<Subject>): List<Subject> {
        var counter = 1
        return list.map { s ->
            s.copy(chapters = s.chapters.map { c -> c.copy(id = counter++) })
        }
    }
}

/** Testo di un blocco: un solo modo per leggerli ovunque. */
fun Block.text(): String = when (this) {
    is Block.Text -> s
    is Block.Bullet -> s
    is Block.Key -> s
}