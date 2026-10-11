package com.brahma.bioquaderno.data

/** Le lezioni interattive che Prof può aprire direttamente nella chat. */
enum class Lesson(val title: String, val emoji: String, val subtitle: String) {
    PUNNETT(
        "Quadrato di Punnet",
        "🧬",
        "Incrocia i genitori e scopri genotipi e fenotipi della prole"
    )
}

/** Risposta di Prof: un testo normale oppure un invito ad aprire una lezione interattiva. */
data class AssistantReply(val text: String, val lesson: Lesson? = null)

/**
 * "Prof": l'assistente offline dell'app.
 *
 * Cerca negli appunti (glossario, capitoli, flashcard, quiz) di TUTTE le materie attive,
 * ma sa capire meglio di un semplice "contiene": toglie le parole vuote, confronta le
 * radici delle parole (es. "cellule" ~ "cellula"), riconosce frasi come "cos'è X",
 * usa il contesto della domanda precedente e, se non trova nulla, propone i termini
 * più vicini per errore di battitura. Funziona senza internet.
 */
object Assistant {

    data class SectionMatch(
        val subjectName: String,
        val subjectEmoji: String,
        val chapterTitle: String,
        val sectionTitle: String,
        val text: String
    )

    private data class TermMatch(
        val term: String,
        val def: String,
        val score: Int,
        val subjectName: String?,
        val subjectEmoji: String?
    )

    private data class CardMatch(val text: String, val answer: String, val score: Int)

    private data class BookPageMatch(val page: Int, val text: String)

    private data class FuzzyHit(val term: String, val def: String, val dist: Int)

    private data class HitSet(
        val terms: List<TermMatch>,
        val section: SectionMatch?,
        val book: BookPageMatch?,
        val flash: CardMatch?,
        val quiz: CardMatch?,
        val score: Int
    ) {
        val isEmpty: Boolean
            get() = terms.isEmpty() && section == null && book == null && flash == null && quiz == null
    }

    private val greetings = setOf(
        "ciao", "hi", "hey", "salve", "hello", "prof",
        "buongiorno", "buonasera", "buon pomeriggio", "ciao prof"
    )

    private val thanks = setOf(
        "grazie", "grazie mille", "grazie prof", "grazie tante", "thanks", "thx"
    )

    /** Parole che non portano significato e vanno ignorate nella ricerca. */
    private val stopWords = setOf(
        "il", "lo", "la", "l", "i", "gli", "le", "un", "una", "uno",
        "del", "dello", "della", "dei", "degli", "delle", "dell", "all",
        "al", "allo", "alla", "ai", "agli", "alle", "dal", "dallo", "dalla",
        "dai", "dagli", "dalle", "nel", "nello", "nella", "nei", "negli", "nelle",
        "col", "con", "su", "per", "tra", "fra", "in", "da", "di", "a", "o", "e", "ed",
        "ma", "se", "anche", "piu", "più", "non", "ne", "lo", "li",
        "questo", "questa", "questi", "queste", "quel", "quella", "quelli", "quelle",
        "mi", "ti", "si", "ci", "vi", "puoi", "puo", "può", "posso", "sai", "so", "vuoi",
        "vorrei", "voglio", "voi", "no", "si", "ben", "bene", "molto", "tanto",
        "spiegami", "spiega", "spiegare", "definisci", "definiscimi", "dimmi",
        "parlami", "dammi", "fammi", "fare", "fai", "fa", "cosa", "come", "quale",
        "quali", "quanto", "quanti", "quante", "vuol", "significa", "significano",
        "significato", "modo", "testo", "testi", "libro", "prof", "appunti",
        "scrivimi", "scrivi", "rispondimi", "rispondi", "senti", "ascolta"
    )

    /** Riconosce se la domanda merita una lezione interattiva invece di una risposta testuale. */
    fun detectLesson(question: String): Lesson? {
        val t = normalize(question)
        val keywords = listOf(
            "punnett", "punnet", "quadrato", "incroci", "incrocio", "gamet", "croce"
        )
        return if (keywords.any { t.contains(it) }) Lesson.PUNNETT else null
    }

    /** Testo introduttivo mostrato nella bolla di una lezione, prima del pulsante "Apri". */
    fun lessonIntro(lesson: Lesson): String = when (lesson) {
        Lesson.PUNNETT ->
            "Perfetto! 🧬 La genetica si capisce molto meglio da vicino: con il quadrato di " +
                "Punnet puoi incrociare due genitori e comporre tu stesso i genotipi della prole.\n\n" +
                "Scegli un incrocio, tocca le celle della griglia e osserva i rapporti fenotipici " +
                "prendere forma. Pronto? 👇"
    }

    /**
     * Risponde offline a una domanda cercando nelle materie date e, se presente,
     * nelle pagine estratte dal libro di testo digitale importato.
     * @param allSubjects tutte le materie attive (scope completo)
     * @param currentSubjectId se presente, la materia su cui concentrarsi, altrimenti tutte
     * @param previousQuestion la domanda precedente, per il contesto della conversazione
     * @param bookPages il testo delle pagine del libro digitale (indice = pagina - 1)
     */
    fun offlineReply(
        question: String,
        allSubjects: List<Subject> = Subjects.all,
        currentSubjectId: String? = null,
        previousQuestion: String? = null,
        bookPages: List<String> = emptyList()
    ): String {
        val t = question.trim().lowercase()
        val scope = scopeOf(allSubjects, currentSubjectId)

        if (t in greetings) return greeting(scope)
        if (t in thanks) {
            return "Di nulla! 😄 Ripassa con calma e, se vuoi, ti interrogo io: scrivimi \"fammi una domanda\". 💪"
        }
        if (t.contains("fammi una domanda") || t.contains("interroga") || t.contains("interrogami") || t.contains("ripassami")) {
            return quizQuestion(scope)
        }

        val q = normalize(question)
        val effective = extractTerm(q) ?: q

        val primary = searchAll(scope, effective, bookPages)

        // Contesto della conversazione: una domanda corta senza risposta può continuare
        // il discorso precedente (es. dopo "cos'è la fotosintesi?" chiede "e il glucosio?").
        if (primary.isEmpty && previousQuestion != null) {
            val prevQ = normalize(previousQuestion)
            val prevEffective = extractTerm(prevQ) ?: prevQ
            val secondary = searchAll(scope, "$prevEffective $effective", bookPages)
            if (!secondary.isEmpty) {
                return renderAnswer(scope, secondary, hintFromPrevious = prevQ.trim())
            }
        }

        if (primary.isEmpty) {
            return fallback(question, scope, fuzzyCandidates(scope, q))
        }

        return renderAnswer(scope, primary, hintFromPrevious = null)
    }

    /** Alias per compatibilità con il vecchio nome. */
    fun offlineAnswer(question: String): String = offlineReply(question, Subjects.all, null, null)

    /** La sezione più pertinente per la domanda, usata come contesto. */
    fun bestSection(scope: List<Subject>, q: String): SectionMatch? {
        var best: SectionMatch? = null
        var bestScore = 0
        scope.forEach { s ->
            s.chapters.forEach { ch ->
                ch.sections.forEach { sec ->
                    val text = sectionText(sec)
                    val sc = scoreFor("${sec.title} ${text}", q)
                    if (sc > bestScore) {
                        bestScore = sc
                        best = SectionMatch(s.name, s.emoji, ch.title, sec.title, text)
                    }
                }
            }
        }
        return if (bestScore > 0) best else null
    }

    /** Contesto da passare a Gemini: se trovato, aiuta il modello a centrare la risposta. */
    fun geminiContext(
        question: String,
        allSubjects: List<Subject>,
        currentSubjectId: String? = null,
        bookPages: List<String> = emptyList()
    ): String {
        val scope = scopeOf(allSubjects, currentSubjectId)
        val sb = StringBuilder()
        val s = bestSection(scope, normalize(question))
        if (s != null) {
            val subj = if (scope.size > 1) "${s.subjectName}: " else ""
            sb.append("\n\nContesto tratto dai miei appunti (usalo solo se utile, non è obbligatorio):\n")
            sb.append("[$subj${s.chapterTitle} - ${s.sectionTitle}]\n")
            sb.append(trimTo(s.text, 700))
        }
        val book = bestBookPage(bookPages, normalize(question))
        if (book != null) {
            sb.append("\n\nContesto dal libro di testo dell'alunno (pag. ${book.page}):\n")
            sb.append(trimTo(book.text, 600))
        }
        return sb.toString()
    }

    // ---------------------------------------------------------------------
    // Ricerca
    // ---------------------------------------------------------------------

    private fun scopeOf(all: List<Subject>, currentId: String?): List<Subject> =
        if (currentId != null) all.filter { it.id == currentId } else all

    private fun searchAll(scope: List<Subject>, q: String, bookPages: List<String>): HitSet {
        val terms = matchingTerms(scope, q)
        val section = bestSection(scope, q)
        val book = bestBookPage(bookPages, q)
        val flash = scope
            .flatMap { it.flashcards }
            .map { CardSeed(it.question, it.answer) }
            .let { matchingCard(it, q) }
        val quiz = scope
            .flatMap { it.questions }
            .map { CardSeed(it.q, "${it.options.joinToString(" ")} ${it.explanation}") }
            .let { matchingCard(it, q) }
        val score = terms.sumOf { it.score } +
            (if (section != null) 8 else 0) +
            (if (book != null) 10 else 0) +
            (flash?.score ?: 0) + (quiz?.score ?: 0)
        return HitSet(terms, section, book, flash, quiz, score)
    }

    /** La pagina del libro digitale più pertinente alla domanda. */
    private fun bestBookPage(bookPages: List<String>, q: String): BookPageMatch? {
        var best: BookPageMatch? = null
        var bestScore = 0
        bookPages.forEachIndexed { i, text ->
            if (text.isBlank()) return@forEachIndexed
            val sc = scoreFor(text, q)
            if (sc > bestScore) {
                bestScore = sc
                best = BookPageMatch(i + 1, text)
            }
        }
        return if (bestScore > 0) best else null
    }

    private data class CardSeed(val text: String, val extra: String)

    private fun matchingCard(cards: List<CardSeed>, q: String): CardMatch? =
        cards
            .map { CardMatch(it.text, it.extra, scoreFor("${it.text} ${it.extra}", q)) }
            .filter { it.score > 0 }
            .sortedByDescending { it.score }
            .firstOrNull()

    private fun matchingTerms(scope: List<Subject>, q: String): List<TermMatch> =
        scope
            .flatMap { s ->
                s.glossary.map { g ->
                    TermMatch(g.term, g.def, scoreFor("${g.term} ${g.def}", q), s.name, s.emoji)
                }
            }
            .filter { it.score > 0 }
            .sortedByDescending { it.score }
            .take(3)

    /** Estrae il termine chiave da frasi come "cos'è X", "definisci X", "cosa significa X". */
    private fun extractTerm(q: String): String? {
        val patterns = listOf(
            "che cosa sono", "che cos'e", "cosa sono", "che cosa e ",
            "cosa e ", "cos'e", "cose ", "chi e", "definisci", "spiegami",
            "spiega", "parlami", "dimmi", "significato di", "cosa vuol dire",
            "vuol dire", "significa", "come funziona"
        )
        var idx = -1
        patterns.forEach { p ->
            val i = q.lastIndexOf(p)
            if (i >= 0) idx = maxOf(idx, i + p.length)
        }
        if (idx <= 0) return null
        val tail = q.substring(idx).trim()
        if (tail.isEmpty()) return null
        val words = keywordsOf(tail).filter { it.length >= 2 && it !in stopWords }
        if (words.isEmpty()) return null
        return words.joinToString(" ")
    }

    /** Suggerimenti "forse cercavi" per errori di battitura o declinazioni strane. */
    private fun fuzzyCandidates(scope: List<Subject>, q: String): List<FuzzyHit> {
        val words = keywordsOf(q).filter { it.length >= 3 && it !in stopWords }
        if (words.size != 1) return emptyList()
        val qStem = stem(words.first())
        if (qStem.length < 3) return emptyList()
        return scope
            .flatMap { s -> s.glossary }
            .mapNotNull { g ->
                val d = levenshtein(qStem, stem(g.term))
                val maxD = 2.coerceAtLeast(qStem.length / 3)
                if (d in 1..maxD) FuzzyHit(g.term, g.def, d) else null
            }
            .sortedBy { it.dist }
            .take(3)
    }

    // ---------------------------------------------------------------------
    // Risposte
    // ---------------------------------------------------------------------

    private fun renderAnswer(scope: List<Subject>, hits: HitSet, hintFromPrevious: String?): String {
        val multi = scope.size > 1
        val sb = StringBuilder()
        if (hintFromPrevious != null) {
            sb.append("Questo sembra la continuazione del tuo discorso precedente " +
                "(\"${trimTo(hintFromPrevious, 60)}\"), quindi cerco lì 👇\n\n")
        } else {
            sb.append("Ecco cosa dicono i miei appunti 📚\n\n")
        }
        if (hits.terms.isNotEmpty()) {
            sb.append("📖 Dal glossario:\n")
            hits.terms.forEach { m ->
                sb.append("• ${m.term}: ${m.def}")
                if (multi && m.subjectName != null) sb.append("  (${m.subjectEmoji} ${m.subjectName})")
                sb.append("\n")
            }
            sb.append("\n")
        }
        hits.section?.let {
            val prefix = if (multi) "${it.subjectEmoji} ${it.subjectName} — " else ""
            sb.append("📗 Da \"$prefix${it.chapterTitle} — ${it.sectionTitle}\":\n")
            sb.append(trimTo(it.text, 700))
            sb.append("\n\n")
        }
        hits.book?.let {
            sb.append("📘 Dal tuo libro di testo (pag. ${it.page}):\n")
            sb.append(trimTo(it.text, 700))
            sb.append("\n\n")
        }
        hits.flash?.let {
            sb.append("🃏 Una flashcard per fissare il concetto:\n«${it.text}»\n→ ${it.answer}\n\n")
        }
        hits.quiz?.let {
            sb.append("❓ Per testarti subito:\n«${it.text}»\nSpiegazione: ${it.answer}\n\n")
        }
        sb.append("Vuoi che ti interroghi? Scrivimi \"fammi una domanda\"! 💪")
        return sb.toString()
    }

    private fun greeting(scope: List<Subject>): String {
        val names = scope.take(6).joinToString(", ") { "${it.emoji} ${it.name}" }
        val extra = if (scope.size > 6) "\n… e altre ${scope.size - 6} materie." else ""
        return if (scope.isEmpty()) {
            "Ciao! 👋 Sono Prof. Attiva almeno una materia dal menu \"Le mie materie\" (icona lista 📋 in alto a destra nella home) e sarò pieno di appunti per te. 💬"
        } else {
            "Ciao! 👋 Sono Prof, la mini-copia dell'assistente dentro l'app.\n\n" +
                "Sono pieno di appunti. Adesso sto studiando con te: $names.$extra\n" +
                "Scrivimi una domanda in italiano oppure scegli un suggerimento qui sopra. 💬"
        }
    }

    private fun quizQuestion(scope: List<Subject>): String {
        val cards = scope.flatMap { it.flashcards }
        if (cards.isEmpty()) {
            return "Non ho ancora flashcard attive: attiva almeno una materia dal menu \"Le mie materie\"! 📚"
        }
        val fc = cards.random()
        return "Ecco una domanda per interrogarti! 🎤\n\n«${fc.question}»\n\n" +
            "E la risposta, perché oggi voglio essere buono: ${fc.answer} 😄\n\n" +
            "Ne vuoi un'altra? Scrivimi di nuovo \"fammi una domanda\"."
    }

    private fun fallback(question: String, scope: List<Subject>, fuzzy: List<FuzzyHit>): String {
        val sb = StringBuilder()
        sb.append("Non ho trovato appunti precisi su \"${question.trim()}\" nella mia libreria… 😕\n\n")
        if (fuzzy.isNotEmpty()) {
            sb.append("Forse cercavi:\n")
            fuzzy.forEach { sb.append("• ${it.term} — ${it.def}\n") }
            sb.append("\n")
        }
        if (scope.isNotEmpty()) {
            sb.append("📚 Quello che copro ${if (scope.size == 1) "in questa materia" else "nelle materie attive"}:\n")
            val topics = scope.flatMap { it.flashTopics }.distinct().take(10)
            if (topics.isNotEmpty()) {
                topics.forEach { sb.append("• $it\n") }
                sb.append("\n")
            }
        }
        sb.append("Prova a riformulare la domanda oppure scegli un suggerimento qui sopra. ")
        sb.append("Se inserisci una chiave API di Gemini nelle impostazioni ⚙️, posso spiegarti davvero qualsiasi cosa! 📡")
        return sb.toString()
    }

    // ---------------------------------------------------------------------
    // Testo e punteggio
    // ---------------------------------------------------------------------

    private fun sectionText(s: Section): String {
        val sb = StringBuilder()
        s.blocks.forEach { b ->
            when (b) {
                is Block.Text -> sb.append(b.s).append(' ')
                is Block.Bullet -> sb.append("• ").append(b.s).append(' ')
                is Block.Key -> sb.append("★ ").append(b.s).append(' ')
            }
        }
        return sb.toString().trim()
    }

    /**
     * Punteggio di pertinenza di un testo rispetto alla domanda.
     * Premia: la presenza della frase intera, delle parole chiave, delle loro radici
     * ("cellule" ↔ "cellula") e delle distanze di battitura dentro al testo.
     */
    private fun scoreFor(hay: String, q: String): Int {
        val h = normalize(hay)
        val nq = normalize(q).trim()
        if (nq.isEmpty() || h.isEmpty()) return 0
        if (h.contains(nq)) return 12
        val hWords = wordsOf(h)
        val qWords = keywordsOf(nq)
        var score = 0
        qWords.forEach { kw ->
            when {
                kw.length >= 5 -> {
                    if (h.contains(kw)) score += 3
                    else if (stem(kw).length >= 3 && hWords.contains(stem(kw))) score += 2
                    else if (stem(kw).length >= 3 && h.contains(stem(kw))) score += 1
                }
                kw.length >= 3 -> {
                    if (h.contains(kw)) score += 1
                    else if (hWords.contains(stem(kw))) score += 1
                }
            }
        }
        if (qWords.size == 1 && qWords.first().length >= 4) {
            val s = stem(qWords.first())
            if (hWords.contains(s)) score += 4
            else if (s.length >= 4 && h.contains(s)) score += 2
        }
        return score
    }

    /** Radice di una parola: toglie le vocali finali per unire plurale e declinazioni. */
    private fun stem(w: String): String {
        var s = w
        while (s.length > 5 && s.last() in "aeiou") s = s.dropLast(1)
        return s
    }

    private fun keywordsOf(q: String): List<String> =
        q.split(Regex("[^a-z0-9]+")).map { it.trim() }.filter { it.isNotEmpty() }.distinct()

    private fun wordsOf(h: String): Set<String> =
        h.split(Regex("[^a-z0-9]+")).filter { it.isNotEmpty() }.toSet()

    private fun normalize(s: String): String {
        val sb = StringBuilder(s.length)
        for (c in s.lowercase()) {
            when (c) {
                'à' -> sb.append('a')
                'è', 'é' -> sb.append('e')
                'ì' -> sb.append('i')
                'ò' -> sb.append('o')
                'ù' -> sb.append('u')
                else -> sb.append(c)
            }
        }
        return sb.toString()
    }

    private fun levenshtein(a: String, b: String): Int {
        if (a == b) return 0
        if (a.isEmpty()) return b.length
        if (b.isEmpty()) return a.length
        val dp = IntArray(b.length + 1) { it }
        for (i in 1..a.length) {
            var prev = dp[0]
            dp[0] = i
            for (j in 1..b.length) {
                val tmp = dp[j]
                dp[j] = minOf(
                    dp[j] + 1,
                    dp[j - 1] + 1,
                    prev + if (a[i - 1] == b[j - 1]) 0 else 1
                )
                prev = tmp
            }
        }
        return dp[b.length]
    }

    private fun trimTo(s: String, max: Int): String {
        val clean = s.replace(Regex("\\s+"), " ").trim()
        return if (clean.length <= max) clean else clean.take(max - 1).trimEnd() + "…"
    }
}