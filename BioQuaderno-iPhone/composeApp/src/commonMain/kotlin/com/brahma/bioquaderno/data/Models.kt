package com.brahma.bioquaderno.data

/** A block of rendered text inside a study section. */
sealed class Block {
    data class Text(val s: String) : Block()
    data class Bullet(val s: String) : Block()
    data class Key(val s: String) : Block() // highlighted takeaway
}

data class Section(val title: String, val blocks: List<Block>)

data class Chapter(
    val id: Int,
    val title: String,
    val icon: String,
    val summary: String,
    val sections: List<Section>
)

data class GlossaryTerm(val term: String, val def: String)

data class Flashcard(val topic: String, val question: String, val answer: String)

data class Question(
    val topic: String,
    val q: String,
    val options: List<String>,
    val correct: Int,
    val explanation: String
)

val topicNames: List<String> = listOf(
    "La cellula",
    "Biomolecole",
    "Membrana e trasporto",
    "Metabolismo",
    "Genetica",
    "Riproduzione e ciclo cellulare",
    "Anatomia umana",
    "Ecologia e evoluzione"
)