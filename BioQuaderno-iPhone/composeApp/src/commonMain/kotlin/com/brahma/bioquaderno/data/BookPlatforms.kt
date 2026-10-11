package com.brahma.bioquaderno.data

/** Una piattaforma web italiana di libri digitali scolastici. */
data class BookPlatform(val name: String, val emoji: String, val url: String)

/**
 * Le principali piattaforme italiane di libri digitali per la scuola.
 * Se il tuo libro è su uno di questi siti, puoi aprirlo qui dentro
 * (serve comunque il login della scuola / casa editrice, come nello studio da casa).
 */
object BookPlatforms {

    val all: List<BookPlatform> = listOf(
        BookPlatform("Scuolabook", "📔", "https://www.scuolabook.it/"),
        BookPlatform("bSmart", "📗", "https://www.bsmart.it/"),
        BookPlatform("myLIM", "🧮", "https://www.mylim.it/"),
        BookPlatform("Zanichelli", "🟢", "https://www.zanichelli.it/"),
        BookPlatform("Rizzoli Education", "📘", "https://www.rizzolieducation.it/"),
        BookPlatform("Mondadori Education", "📕", "https://www.mondadorieducation.it/"),
        BookPlatform("Pearson Italia", "🏫", "https://www.pearson.it/"),
        BookPlatform("Deascuola", "🎨", "https://www.deascuola.it/"),
        BookPlatform("Sanoma Italia", "📚", "https://www.sanoma.it/"),
        BookPlatform("Giunti Scuola", "🌈", "https://www.giuntiscuola.it/"),
        BookPlatform("La Scuola", "🏛️", "https://www.lascuola.it/"),
        BookPlatform("Cetem", "✏️", "https://www.cetem.it/"),
        BookPlatform("D'Anna", "🖋️", "https://www.danna.it/"),
        BookPlatform("Loescher", "📖", "https://www.loescher.it/"),
        BookPlatform("Clitt", "📐", "https://www.clitt.it/"),
        BookPlatform("Sei", "🎯", "https://www.seieditrice.com/"),
        BookPlatform("Il Capitello", "🏺", "https://www.capitello.it/"),
        BookPlatform("Treccani Scuola", "📜", "https://www.treccani.it/scuola/"),
        BookPlatform("WeSchool", "👩‍🏫", "https://www.weschool.com/"),
        BookPlatform("Elemedia", "🧩", "https://www.elemedia.it/")
    )
}