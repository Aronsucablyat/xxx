package com.brahma.bioquaderno.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import com.brahma.bioquaderno.platform.Prefs
import com.brahma.bioquaderno.platform.getInt

/**
 * Tutte le impostazioni di aspetto dell'app.
 * Ogni opzione è salvata e applicata subito (stesse opzioni su Android e iPhone).
 */
data class AppSettings(
    // ── Colori ────────────────────────────────────────────────────────
    val paletteIndex: Int = 0,                 // colore principale (indice in palettes)
    val darkMode: String = "system",           // system | light | dark
    val boldColors: Boolean = false,           // colori più vivaci e intensi
    val highContrast: Boolean = false,         // contrasto alto (accessibilità)
    val backgroundStyle: String = "aurora",    // aurora | gradiente | tinta | nessuno
    val auroraIntensity: String = "normale",   // tenue | normale | vivace

    // ── Forme ─────────────────────────────────────────────────────────
    val cornerStyle: String = "arrotondato",   // arrotondato | quadrato | morbido | pillola
    val cardElevation: String = "morbida",     // piatta | morbida | forte
    val cardBorder: String = "sottile",        // nessuno | sottile | spesso

    // ── Testo ─────────────────────────────────────────────────────────
    val fontSize: String = "normale",          // xs | piccola | normale | grande | xl
    val fontFamily: String = "sistema",        // sistema | serif | mono
    val fontWeight: String = "normale",        // normale | medio | grassetto
    val letterSpacing: String = "normale",     // stretto | normale | ampio
    val lineSpacing: String = "normale",       // compatto | normale | arioso

    // ── Disposizione ──────────────────────────────────────────────────
    val denseMode: Boolean = false,            // elenchi e schede più compatti
    val cardPadding: String = "normale",       // compatta | normale | ampia
    val navLabels: String = "entrambi",        // entrambi | icone | testo
    val largeTouchTargets: Boolean = false,    // icone e tocchi più grandi

    // ── Animazioni ────────────────────────────────────────────────────
    val animations: Boolean = true,            // animazioni attive
    val animationSpeed: String = "media",      // lenta | media | veloce
    val tabTransition: String = "dissolvenza", // dissolvenza | scorrimento | nessuna

    // ── Vetro ─────────────────────────────────────────────────────────
    val glass: Boolean = true,                 // effetto liquid glass
    val reduceTransparency: Boolean = false,   // vetro più opaco (più leggibile)

    // ── Avvio ─────────────────────────────────────────────────────────
    val startTab: String = "studio"            // studio | glossario | flashcard | quiz | prof
)

object AppSettingsStore {
    private const val KEY_PALETTE = "palette_index"
    private const val KEY_DARK = "dark_mode"
    private const val KEY_BOLD = "bold_colors"
    private const val KEY_CONTRAST = "high_contrast"
    private const val KEY_BG = "background_style"
    private const val KEY_AURORA = "aurora_intensity"
    private const val KEY_CORNERS = "corner_style"
    private const val KEY_ELEV = "card_elevation"
    private const val KEY_BORDER = "card_border"
    private const val KEY_FONT = "font_size"
    private const val KEY_FAMILY = "font_family"
    private const val KEY_WEIGHT = "font_weight"
    private const val KEY_LETTER = "letter_spacing"
    private const val KEY_LINE = "line_spacing"
    private const val KEY_DENSE = "dense_mode"
    private const val KEY_CARD_PAD = "card_padding"
    private const val KEY_NAV = "nav_labels"
    private const val KEY_TOUCH = "large_touch"
    private const val KEY_ANIM = "animations"
    private const val KEY_ANIM_SPEED = "anim_speed"
    private const val KEY_TAB_TRANS = "tab_transition"
    private const val KEY_GLASS = "glass"
    private const val KEY_TRANSP = "reduce_transparency"
    private const val KEY_START = "start_tab"

    fun load(prefs: Prefs): AppSettings = AppSettings(
        paletteIndex = prefs.getInt(KEY_PALETTE, 0).coerceIn(0, palettes.lastIndex),
        darkMode = prefs.get(KEY_DARK) ?: "system",
        boldColors = prefs.get(KEY_BOLD) == "true",
        highContrast = prefs.get(KEY_CONTRAST) == "true",
        backgroundStyle = prefs.get(KEY_BG) ?: "aurora",
        auroraIntensity = prefs.get(KEY_AURORA) ?: "normale",
        cornerStyle = prefs.get(KEY_CORNERS) ?: "arrotondato",
        cardElevation = prefs.get(KEY_ELEV) ?: "morbida",
        cardBorder = prefs.get(KEY_BORDER) ?: "sottile",
        fontSize = prefs.get(KEY_FONT) ?: "normale",
        fontFamily = prefs.get(KEY_FAMILY) ?: "sistema",
        fontWeight = prefs.get(KEY_WEIGHT) ?: "normale",
        letterSpacing = prefs.get(KEY_LETTER) ?: "normale",
        lineSpacing = prefs.get(KEY_LINE) ?: "normale",
        denseMode = prefs.get(KEY_DENSE) == "true",
        cardPadding = prefs.get(KEY_CARD_PAD) ?: "normale",
        navLabels = prefs.get(KEY_NAV) ?: "entrambi",
        largeTouchTargets = prefs.get(KEY_TOUCH) == "true",
        animations = prefs.get(KEY_ANIM) != "false",
        animationSpeed = prefs.get(KEY_ANIM_SPEED) ?: "media",
        tabTransition = prefs.get(KEY_TAB_TRANS) ?: "dissolvenza",
        glass = prefs.get(KEY_GLASS) != "false",
        reduceTransparency = prefs.get(KEY_TRANSP) == "true",
        startTab = prefs.get(KEY_START) ?: "studio"
    )

    fun save(prefs: Prefs, s: AppSettings) {
        prefs.put(KEY_PALETTE, s.paletteIndex.toString())
        prefs.put(KEY_DARK, s.darkMode)
        prefs.put(KEY_BOLD, s.boldColors.toString())
        prefs.put(KEY_CONTRAST, s.highContrast.toString())
        prefs.put(KEY_BG, s.backgroundStyle)
        prefs.put(KEY_AURORA, s.auroraIntensity)
        prefs.put(KEY_CORNERS, s.cornerStyle)
        prefs.put(KEY_ELEV, s.cardElevation)
        prefs.put(KEY_BORDER, s.cardBorder)
        prefs.put(KEY_FONT, s.fontSize)
        prefs.put(KEY_FAMILY, s.fontFamily)
        prefs.put(KEY_WEIGHT, s.fontWeight)
        prefs.put(KEY_LETTER, s.letterSpacing)
        prefs.put(KEY_LINE, s.lineSpacing)
        prefs.put(KEY_DENSE, s.denseMode.toString())
        prefs.put(KEY_CARD_PAD, s.cardPadding)
        prefs.put(KEY_NAV, s.navLabels)
        prefs.put(KEY_TOUCH, s.largeTouchTargets.toString())
        prefs.put(KEY_ANIM, s.animations.toString())
        prefs.put(KEY_ANIM_SPEED, s.animationSpeed)
        prefs.put(KEY_TAB_TRANS, s.tabTransition)
        prefs.put(KEY_GLASS, s.glass.toString())
        prefs.put(KEY_TRANSP, s.reduceTransparency.toString())
        prefs.put(KEY_START, s.startTab)
    }
}

/** Lettura rapida delle impostazioni di aspetto da qualunque punto dell'UI. */
val LocalAppSettings = staticCompositionLocalOf { AppSettings() }
