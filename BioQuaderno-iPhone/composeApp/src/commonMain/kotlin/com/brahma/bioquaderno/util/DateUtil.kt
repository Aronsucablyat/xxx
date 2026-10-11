package com.brahma.bioquaderno.util

/**
 * Formatta una data (millisecondi dall'epoch) come "dd/MM/yyyy · HH:mm".
 * Implementazione puramente Kotlin, valida sia su Android sia su iPhone
 * (niente java.text, che non esiste su iOS).
 */
fun formatDate(ts: Long): String {
    val millis = ts.coerceAtLeast(0)
    val days = millis / 86_400_000L

    // Conversione giorni -> (anno, mese, giorno) [algoritmo di H. Hinnant]
    val z = days + 719468
    val era = (if (z >= 0) z else z - 146096) / 146097
    val doe = z - era * 146097                                   // [0, 146096]
    val yoe = (doe - doe / 1460 + doe / 36524 - doe / 146096) / 365 // [0, 399]
    val y = yoe + era * 400
    val doy = doe - (365 * yoe + yoe / 4 - yoe / 100)            // [0, 365]
    val mp = (5 * doy + 2) / 153                                 // [0, 11]
    val day = doy - (153 * mp + 2) / 5 + 1                       // [1, 31]
    val month = if (mp < 10) mp + 3 else mp - 9                  // [1, 12]
    val year = if (month <= 2) y + 1 else y

    var secOfDay = (millis / 1000L) % 86_400L
    if (secOfDay < 0) secOfDay += 86_400L
    val hour = (secOfDay / 3600L).toInt()
    val minute = ((secOfDay % 3600L) / 60L).toInt()

    fun p(n: Long) = n.toString().padStart(2, '0')
    return "${p(day.toLong())}/${p(month.toLong())}/${year.toString().padStart(4, '0')} · ${p(hour.toLong())}:${p(minute.toLong())}"
}