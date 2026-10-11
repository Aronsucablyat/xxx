package com.brahma.bioquaderno.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import androidx.compose.ui.unit.sp

/** Una "piastra" di colori da cui viene generato tutto il tema. */
data class Palette(
    val name: String,
    val seed: Color,
    val aurora: List<Color>
)

/** Tantissime tinte predefinite: l'utente sceglie quella che preferisce. */
val palettes = listOf(
    Palette("Smeraldo", Color(0xFF2E7D32), listOf(Color(0xFF2E7D32), Color(0xFF00BFA5), Color(0xFF9CCC65))),
    Palette("Foresta", Color(0xFF1B5E20), listOf(Color(0xFF1B5E20), Color(0xFF43A047), Color(0xFF00897B))),
    Palette("Menta", Color(0xFF00897B), listOf(Color(0xFF26A69A), Color(0xFF80CBC4), Color(0xFF4DD0E1))),
    Palette("Oceano", Color(0xFF0288D1), listOf(Color(0xFF0288D1), Color(0xFF26C6DA), Color(0xFF7E57C2))),
    Palette("Blu Notte", Color(0xFF283593), listOf(Color(0xFF3949AB), Color(0xFF0288D1), Color(0xFF5C6BC0))),
    Palette("Laguna", Color(0xFF00838F), listOf(Color(0xFF26A69A), Color(0xFF00838F), Color(0xFF4FC3F7))),
    Palette("Turchesi", Color(0xFF00695C), listOf(Color(0xFF009688), Color(0xFF26C6DA), Color(0xFFA5D6A7))),
    Palette("Alba", Color(0xFFEF6C00), listOf(Color(0xFFFF9800), Color(0xFFF4511E), Color(0xFFFFD54F))),
    Palette("Tramonto", Color(0xFFF4511E), listOf(Color(0xFFE64A19), Color(0xFFFF7043), Color(0xFFFFCA28))),
    Palette("Miele", Color(0xFFF9A825), listOf(Color(0xFFFBC02D), Color(0xFFFFB300), Color(0xFFFFE082))),
    Palette("Viola", Color(0xFF8E24AA), listOf(Color(0xFFAB47BC), Color(0xFF7E57C2), Color(0xFF4DD0E1))),
    Palette("Orchidea", Color(0xFF6A1B9A), listOf(Color(0xFF8E24AA), Color(0xFFBA68C8), Color(0xFF5C6BC0))),
    Palette("Rosa", Color(0xFFD81B60), listOf(Color(0xFFEC407A), Color(0xFFF48FB1), Color(0xFF7986CB))),
    Palette("Ciliegia", Color(0xFFC2185B), listOf(Color(0xFFE91E63), Color(0xFFF06292), Color(0xFF9C27B0))),
    Palette("Caffè", Color(0xFF6D4C41), listOf(Color(0xFF8D6E63), Color(0xFFBCAAA4), Color(0xFFA1887F))),
    Palette("Grafite", Color(0xFF455A64), listOf(Color(0xFF546E7A), Color(0xFF90A4AE), Color(0xFF37474F)))
)

fun buildColorScheme(
    palette: Palette,
    dark: Boolean,
    bold: Boolean = false,
    highContrast: Boolean = false
): ColorScheme {
    val s = if (bold) boostSeed(palette.seed) else palette.seed
    val (h, sat, v) = rgbToHsv(s)

    val secondaryC = hsvToColor(
        (h + 32f) % 360f,
        minOf(sat + 0.12f, 1f),
        if (dark) minOf(v + 0.22f, 1f) else maxOf(v - 0.10f, 0.30f)
    )
    val tertiaryC = hsvToColor(
        (h + 150f) % 360f,
        minOf(sat + 0.05f, 1f),
        if (dark) minOf(v + 0.15f, 1f) else maxOf(v - 0.05f, 0.35f)
    )

    val base = if (!dark) lightColorScheme(
        primary = s,
        onPrimary = Color.White,
        primaryContainer = lerp(s, Color.White, 0.78f),
        onPrimaryContainer = lerp(s, Color.Black, 0.60f),
        inversePrimary = lerp(s, Color.White, 0.30f),
        secondary = secondaryC,
        onSecondary = Color.White,
        secondaryContainer = lerp(secondaryC, Color.White, 0.80f),
        onSecondaryContainer = lerp(secondaryC, Color.Black, 0.62f),
        tertiary = tertiaryC,
        onTertiary = Color.White,
        tertiaryContainer = lerp(tertiaryC, Color.White, 0.80f),
        onTertiaryContainer = lerp(tertiaryC, Color.Black, 0.62f),
        background = Color(0xFFF6FAF3),
        onBackground = Color(0xFF191D19),
        surface = Color(0xFFFBFFFA),
        onSurface = Color(0xFF191D19),
        surfaceVariant = lerp(s, Color.White, 0.88f),
        onSurfaceVariant = Color(0xFF444A42),
        outline = Color(0xFF757A72),
        outlineVariant = lerp(s, Color.White, 0.82f),
        error = Color(0xFFBA1A1A),
        onError = Color.White,
        errorContainer = Color(0xFFFFDAD6),
        onErrorContainer = Color(0xFF410002)
    ) else darkColorScheme(
        primary = lerp(s, Color.White, 0.16f),
        onPrimary = lerp(s, Color.White, 0.90f),
        primaryContainer = lerp(s, Color.Black, 0.45f),
        onPrimaryContainer = lerp(s, Color.White, 0.75f),
        inversePrimary = s,
        secondary = lerp(secondaryC, Color.White, 0.40f),
        onSecondary = lerp(secondaryC, Color.Black, 0.75f),
        secondaryContainer = lerp(secondaryC, Color.Black, 0.55f),
        onSecondaryContainer = lerp(secondaryC, Color.White, 0.75f),
        tertiary = lerp(tertiaryC, Color.White, 0.40f),
        onTertiary = lerp(tertiaryC, Color.Black, 0.75f),
        tertiaryContainer = lerp(tertiaryC, Color.Black, 0.55f),
        onTertiaryContainer = lerp(tertiaryC, Color.White, 0.75f),
        background = Color(0xFF0F120E),
        onBackground = Color(0xFFE2E7DF),
        surface = Color(0xFF141A14),
        onSurface = Color(0xFFE2E7DF),
        surfaceVariant = Color(0xFF41483F),
        onSurfaceVariant = Color(0xFFC2C9BE),
        outline = Color(0xFF8C9388),
        outlineVariant = Color(0xFF41483F),
        error = Color(0xFFFFB4AB),
        onError = Color(0xFF690005),
        errorContainer = Color(0xFF93000A),
        onErrorContainer = Color(0xFFFFDAD6)
    )

    // Contrasto alto: bordi e testi più netti per chi ha difficoltà di lettura.
    return if (highContrast) base.copy(
        outline = if (dark) Color(0xFFE6E6E6) else Color(0xFF2B2B2B),
        outlineVariant = if (dark) Color(0xFF9AA096) else Color(0xFF6A6F68),
        onSurfaceVariant = if (dark) Color(0xFFF2F4F0) else Color(0xFF1F231F),
        surfaceVariant = if (dark) Color(0xFF2A322A) else Color(0xFFEDF2EA),
        onBackground = if (dark) Color(0xFFFFFFFF) else Color(0xFF000000),
        onSurface = if (dark) Color(0xFFFFFFFF) else Color(0xFF000000)
    ) else base
}

/** Rende il colore principale più saturo e luminoso (modalità "colori vivaci"). */
private fun boostSeed(c: Color): Color {
    val (h, sat, v) = rgbToHsv(c)
    return hsvToColor(h, minOf(sat * 1.35f + 0.05f, 1f), minOf(v + 0.12f, 1f))
}

private fun fontScaleOf(fontSize: String): Float = when (fontSize) {
    "xs" -> 0.82f
    "piccola" -> 0.90f
    "grande" -> 1.15f
    "xl" -> 1.30f
    else -> 1f
}

/** Tipografia costruita dalle impostazioni: dimensione, carattere, peso, spaziature. */
fun appTypography(settings: AppSettings): Typography {
    val scale = fontScaleOf(settings.fontSize)
    val family = when (settings.fontFamily) {
        "serif" -> FontFamily.Serif
        "mono" -> FontFamily.Monospace
        else -> FontFamily.SansSerif
    }
    val weight = when (settings.fontWeight) {
        "medio" -> FontWeight.Medium
        "grassetto" -> FontWeight.SemiBold
        else -> null
    }
    val ls = when (settings.letterSpacing) {
        "stretto" -> (-0.3).sp
        "ampio" -> 0.5.sp
        else -> 0.sp
    }
    val lhFactor = when (settings.lineSpacing) {
        "compatto" -> 0.92f
        "arioso" -> 1.20f
        else -> 1f
    }

    val isDefault = scale == 1f && settings.fontFamily == "sistema" &&
        weight == null && settings.letterSpacing == "normale" && lhFactor == 1f
    val base = Typography()
    if (isDefault) return base

    fun style(t: TextStyle): TextStyle {
        var r = t.copy(
            fontFamily = family,
            letterSpacing = ls,
            lineHeight = if (t.lineHeight.isSpecified) (t.lineHeight.value * scale * lhFactor).sp else t.lineHeight
        )
        if (scale != 1f) r = r.copy(fontSize = (t.fontSize.value * scale).sp)
        if (weight != null) r = r.copy(fontWeight = weight)
        return r
    }

    return base.copy(
        displayLarge = style(base.displayLarge),
        displayMedium = style(base.displayMedium),
        displaySmall = style(base.displaySmall),
        headlineLarge = style(base.headlineLarge),
        headlineMedium = style(base.headlineMedium),
        headlineSmall = style(base.headlineSmall),
        titleLarge = style(base.titleLarge),
        titleMedium = style(base.titleMedium),
        titleSmall = style(base.titleSmall),
        bodyLarge = style(base.bodyLarge),
        bodyMedium = style(base.bodyMedium),
        bodySmall = style(base.bodySmall),
        labelLarge = style(base.labelLarge),
        labelMedium = style(base.labelMedium),
        labelSmall = style(base.labelSmall)
    )
}

/** Angoli delle superfici in base allo stile scelto. */
fun appShapes(cornerStyle: String): Shapes {
    val (xs, s, m, l, xl) = when (cornerStyle) {
        "quadrato" -> listOf(0, 2, 3, 4, 6)
        "morbido" -> listOf(8, 12, 18, 24, 34)
        "pillola" -> listOf(14, 20, 28, 34, 44)
        else -> listOf(4, 8, 12, 16, 28) // arrotondato
    }
    return Shapes(
        extraSmall = RoundedCornerShape(xs.dp),
        small = RoundedCornerShape(s.dp),
        medium = RoundedCornerShape(m.dp),
        large = RoundedCornerShape(l.dp),
        extraLarge = RoundedCornerShape(xl.dp)
    )
}

@Composable
fun BioQuadernoTheme(
    palette: Palette = palettes.first(),
    darkTheme: Boolean = isSystemInDarkTheme(),
    settings: AppSettings = AppSettings(),
    content: @Composable () -> Unit
) {
    val typography = remember(
        settings.fontSize, settings.fontFamily, settings.fontWeight,
        settings.letterSpacing, settings.lineSpacing
    ) { appTypography(settings) }
    val shapes = remember(settings.cornerStyle) { appShapes(settings.cornerStyle) }
    val scheme = remember(palette, darkTheme, settings.boldColors, settings.highContrast) {
        buildColorScheme(palette, darkTheme, settings.boldColors, settings.highContrast)
    }

    // Le impostazioni sono disponibili anche dentro il tema (Glass, pannelli…)
    CompositionLocalProvider(LocalAppSettings provides settings) {
        MaterialTheme(
            colorScheme = scheme,
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}

/** Converte un colore RGB in tonalità (0..360), saturazione (0..1) e luminosità (0..1). */
private fun rgbToHsv(c: Color): Triple<Float, Float, Float> {
    val r = c.red
    val g = c.green
    val b = c.blue
    val max = maxOf(r, g, b)
    val min = minOf(r, g, b)
    val d = max - min
    val v = max
    val s = if (max == 0f) 0f else d / max
    val h = when {
        d == 0f -> 0f
        max == r -> 60f * (((g - b) / d) % 6f)
        max == g -> 60f * ((b - r) / d + 2f)
        else -> 60f * ((r - g) / d + 4f)
    }
    return Triple(if (h < 0f) h + 360f else h, s, v)
}

/** Costruisce un colore da tonalità (0..360), saturazione (0..1) e luminosità (0..1). */
private fun hsvToColor(h: Float, s: Float, v: Float): Color {
    val c = v * s
    val x = c * (1f - kotlin.math.abs((h / 60f) % 2f - 1f))
    val m = v - c
    val (r, g, b) = when {
        h < 60f -> Triple(c, x, 0f)
        h < 120f -> Triple(x, c, 0f)
        h < 180f -> Triple(0f, c, x)
        h < 240f -> Triple(0f, x, c)
        h < 300f -> Triple(x, 0f, c)
        else -> Triple(c, 0f, x)
    }
    return Color(r + m, g + m, b + m)
}
