package com.brahma.bioquaderno.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.brahma.bioquaderno.ui.theme.AppSettings
import com.brahma.bioquaderno.ui.theme.palettes

/** Titolo di sezione dentro il pannello impostazioni. */
@Composable
private fun SectionTitle(text: String) {
    Text(
        text,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.width(Int.MAX_VALUE.dp)
    )
}

/** Riga con interruttore: titolo + descrizione + switch. */
@Composable
private fun SettingsSwitchRow(
    title: String,
    subtitle: String,
    checked: Boolean,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
            Text(
                subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Switch(checked = checked, enabled = enabled, onCheckedChange = onCheckedChange)
    }
}

/** Gruppo di scelte (chip) che va a capo da solo se non c'è spazio. */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ChoiceChips(
    options: List<Pair<String, String>>,
    selected: String,
    onSelect: (String) -> Unit
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        options.forEach { (value, label) ->
            FilterChip(
                selected = selected == value,
                onClick = { onSelect(value) },
                label = { Text(label) }
            )
        }
    }
}

/** Etichetta di gruppo (sotto al titolo di sezione). */
@Composable
private fun GroupLabel(text: String) {
    Text(
        text,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.width(Int.MAX_VALUE.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppearanceDialog(
    settings: AppSettings,
    onSettings: (AppSettings) -> Unit,
    onDismiss: () -> Unit
) {
    fun upd(s: AppSettings) = onSettings(s)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Personalizza l'app", fontWeight = FontWeight.Bold) },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                // ── Colori ──────────────────────────────────────────────
                SectionTitle("🎨 Colore")
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(palettes) { p ->
                        val i = palettes.indexOf(p)
                        val selected = settings.paletteIndex == i
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(p.seed)
                                    .border(
                                        width = if (selected) 3.dp else 1.dp,
                                        color = if (selected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.outline,
                                        shape = CircleShape
                                    )
                                    .clickable { upd(settings.copy(paletteIndex = i)) },
                                contentAlignment = Alignment.Center
                            ) {
                                if (selected) {
                                    Icon(
                                        Icons.Filled.Check,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                            Text(p.name, style = MaterialTheme.typography.labelSmall, maxLines = 1)
                        }
                    }
                }
                ChoiceChips(
                    options = listOf("light" to "Chiaro", "dark" to "Scuro", "system" to "Auto"),
                    selected = settings.darkMode,
                    onSelect = { upd(settings.copy(darkMode = it)) }
                )
                SettingsSwitchRow(
                    title = "🌟 Colori vivaci",
                    subtitle = "Saturazione e luminosità maggiori su tutto il tema",
                    checked = settings.boldColors,
                    onCheckedChange = { upd(settings.copy(boldColors = it)) }
                )
                SettingsSwitchRow(
                    title = "🔆 Contrasto alto",
                    subtitle = "Bordi e testi più netti, ideale per leggere meglio",
                    checked = settings.highContrast,
                    onCheckedChange = { upd(settings.copy(highContrast = it)) }
                )

                HorizontalDivider()
                SectionTitle("🌌 Sfondo")
                ChoiceChips(
                    options = listOf(
                        "aurora" to "Aurora",
                        "gradiente" to "Gradiente",
                        "tinta" to "Tinta unita",
                        "nessuno" to "Nessuno"
                    ),
                    selected = settings.backgroundStyle,
                    onSelect = { upd(settings.copy(backgroundStyle = it)) }
                )
                if (settings.backgroundStyle == "aurora") {
                    GroupLabel("Intensità")
                    ChoiceChips(
                        options = listOf("tenue" to "Tenue", "normale" to "Normale", "vivace" to "Vivace"),
                        selected = settings.auroraIntensity,
                        onSelect = { upd(settings.copy(auroraIntensity = it)) }
                    )
                }

                HorizontalDivider()
                // ── Forme ───────────────────────────────────────────────
                SectionTitle("📐 Forme")
                GroupLabel("Angoli")
                ChoiceChips(
                    options = listOf(
                        "arrotondato" to "Arrotondati",
                        "quadrato" to "Quadri",
                        "morbido" to "Morbidi",
                        "pillola" to "Pillola"
                    ),
                    selected = settings.cornerStyle,
                    onSelect = { upd(settings.copy(cornerStyle = it)) }
                )
                GroupLabel("Ombra schede")
                ChoiceChips(
                    options = listOf("piatta" to "Piatta", "morbida" to "Morbida", "forte" to "Forte"),
                    selected = settings.cardElevation,
                    onSelect = { upd(settings.copy(cardElevation = it)) }
                )
                GroupLabel("Bordo schede")
                ChoiceChips(
                    options = listOf("nessuno" to "Nessuno", "sottile" to "Sottile", "spesso" to "Spesso"),
                    selected = settings.cardBorder,
                    onSelect = { upd(settings.copy(cardBorder = it)) }
                )

                HorizontalDivider()
                // ── Testo ───────────────────────────────────────────────
                SectionTitle("🔠 Testo")
                GroupLabel("Dimensione")
                ChoiceChips(
                    options = listOf(
                        "xs" to "XS", "piccola" to "Piccola", "normale" to "Normale",
                        "grande" to "Grande", "xl" to "XL"
                    ),
                    selected = settings.fontSize,
                    onSelect = { upd(settings.copy(fontSize = it)) }
                )
                GroupLabel("Carattere")
                ChoiceChips(
                    options = listOf("sistema" to "Sistema", "serif" to "Serif", "mono" to "Mono"),
                    selected = settings.fontFamily,
                    onSelect = { upd(settings.copy(fontFamily = it)) }
                )
                GroupLabel("Peso")
                ChoiceChips(
                    options = listOf("normale" to "Normale", "medio" to "Medio", "grassetto" to "Grassetto"),
                    selected = settings.fontWeight,
                    onSelect = { upd(settings.copy(fontWeight = it)) }
                )
                GroupLabel("Spaziatura lettere")
                ChoiceChips(
                    options = listOf("stretto" to "Stretta", "normale" to "Normale", "ampio" to "Ampia"),
                    selected = settings.letterSpacing,
                    onSelect = { upd(settings.copy(letterSpacing = it)) }
                )
                GroupLabel("Interlinea")
                ChoiceChips(
                    options = listOf("compatto" to "Compatta", "normale" to "Normale", "arioso" to "Ariosa"),
                    selected = settings.lineSpacing,
                    onSelect = { upd(settings.copy(lineSpacing = it)) }
                )

                HorizontalDivider()
                // ── Disposizione ────────────────────────────────────────
                SectionTitle("🗂 Disposizione")
                SettingsSwitchRow(
                    title = "Elenchi compatti",
                    subtitle = "Schede e liste più strette, più contenuti visibili",
                    checked = settings.denseMode,
                    onCheckedChange = { upd(settings.copy(denseMode = it)) }
                )
                GroupLabel("Spazio nelle schede")
                ChoiceChips(
                    options = listOf("compatta" to "Compatta", "normale" to "Normale", "ampia" to "Ampia"),
                    selected = settings.cardPadding,
                    onSelect = { upd(settings.copy(cardPadding = it)) }
                )
                GroupLabel("Barra in basso")
                ChoiceChips(
                    options = listOf("entrambi" to "Icona + testo", "icone" to "Solo icone", "testo" to "Solo testo"),
                    selected = settings.navLabels,
                    onSelect = { upd(settings.copy(navLabels = it)) }
                )
                SettingsSwitchRow(
                    title = "Tocchi grandi",
                    subtitle = "Icone della barra più grandi, più facili da premere",
                    checked = settings.largeTouchTargets,
                    onCheckedChange = { upd(settings.copy(largeTouchTargets = it)) }
                )

                HorizontalDivider()
                // ── Animazioni ──────────────────────────────────────────
                SectionTitle("⚡️ Animazioni")
                SettingsSwitchRow(
                    title = "Animazioni attive",
                    subtitle = "Sfondo in movimento, transizioni e micro-animazioni",
                    checked = settings.animations,
                    onCheckedChange = { upd(settings.copy(animations = it)) }
                )
                if (settings.animations) {
                    GroupLabel("Velocità")
                    ChoiceChips(
                        options = listOf("lenta" to "Lente", "media" to "Medie", "veloce" to "Rapide"),
                        selected = settings.animationSpeed,
                        onSelect = { upd(settings.copy(animationSpeed = it)) }
                    )
                    GroupLabel("Passaggio tra schede")
                    ChoiceChips(
                        options = listOf(
                            "dissolvenza" to "Dissolvenza",
                            "scorrimento" to "Scorrimento",
                            "nessuna" to "Nessuno"
                        ),
                        selected = settings.tabTransition,
                        onSelect = { upd(settings.copy(tabTransition = it)) }
                    )
                }

                HorizontalDivider()
                // ── Vetro ───────────────────────────────────────────────
                SectionTitle("🪟 Vetro")
                SettingsSwitchRow(
                    title = "Liquid Glass",
                    subtitle = "Superfici traslucide con bordo luminoso, stile Apple",
                    checked = settings.glass,
                    onCheckedChange = { upd(settings.copy(glass = it)) }
                )
                SettingsSwitchRow(
                    title = "Riduci trasparenza",
                    subtitle = "Vetro più opaco: più contrasto, meno effetto sfondo",
                    checked = settings.reduceTransparency,
                    enabled = settings.glass,
                    onCheckedChange = { upd(settings.copy(reduceTransparency = it)) }
                )

                HorizontalDivider()
                // ── Avvio ───────────────────────────────────────────────
                SectionTitle("🚀 All'avvio")
                GroupLabel("Scheda iniziale")
                ChoiceChips(
                    options = listOf(
                        "studio" to "Studio",
                        "glossario" to "Glossario",
                        "flashcard" to "Flashcard",
                        "quiz" to "Quiz",
                        "prof" to "Prof"
                    ),
                    selected = settings.startTab,
                    onSelect = { upd(settings.copy(startTab = it)) }
                )
            }
        },
        dismissButton = {
            TextButton(onClick = { upd(AppSettings()) }) { Text("Ripristina") }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Fatto") }
        }
    )
}
