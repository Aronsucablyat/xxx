package com.brahma.bioquaderno.ui.lessons

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brahma.bioquaderno.data.Lesson
import com.brahma.bioquaderno.ui.components.GlassCard

/** Un incrocio preimpostato con cui giocare. */
private data class PunnettPreset(
    val name: String,
    val description: String,
    val parentA: String,
    val parentB: String,
    val gametesA: List<String>, // gameti del genitore A (colonne della griglia)
    val gametesB: List<String>, // gameti del genitore B (righe della griglia)
    val phenotypeOf: (String) -> String
)

private val presets = listOf(
    PunnettPreset(
        name = "RR × rr",
        description = "Due linee pure: seme rosso × seme bianco. Tutta la F1 è eterozigote (Rr).",
        parentA = "RR",
        parentB = "rr",
        gametesA = listOf("R"),
        gametesB = listOf("r"),
        phenotypeOf = { g ->
            when (g) {
                "RR" -> "Rosso"
                "Rr" -> "Rosso"
                "rr" -> "Bianco"
                else -> "?"
            }
        }
    ),
    PunnettPreset(
        name = "Rr × Rr",
        description = "Due eterozigoti: il classico rapporto fenotipico 3:1 di Mendel.",
        parentA = "Rr",
        parentB = "Rr",
        gametesA = listOf("R", "r"),
        gametesB = listOf("R", "r"),
        phenotypeOf = { g ->
            when (g) {
                "RR" -> "Rosso"
                "Rr" -> "Rosso"
                "rr" -> "Bianco"
                else -> "?"
            }
        }
    ),
    PunnettPreset(
        name = "Rr × rr",
        description = "Il reincrocio (test cross): fenotipi in rapporto 1:1.",
        parentA = "Rr",
        parentB = "rr",
        gametesA = listOf("R", "r"),
        gametesB = listOf("r"),
        phenotypeOf = { g ->
            when (g) {
                "RR" -> "Rosso"
                "Rr" -> "Rosso"
                "rr" -> "Bianco"
                else -> "?"
            }
        }
    ),
    PunnettPreset(
        name = "AaBb × AaBb",
        description = "Incrocio diibrido: due geni indipendenti, 16 combinazioni, rapporto 9:3:3:1.",
        parentA = "AaBb",
        parentB = "AaBb",
        gametesA = listOf("AB", "Ab", "aB", "ab"),
        gametesB = listOf("AB", "Ab", "aB", "ab"),
        phenotypeOf = { g ->
            val hasA = g.contains('A')
            val hasB = g.contains('B')
            when {
                hasA && hasB -> "Liscio, giallo"
                hasA && !hasB -> "Liscio, verde"
                !hasA && hasB -> "Rugoso, giallo"
                else -> "Rugoso, verde"
            }
        }
    )
)

private val barColors = listOf(
    Color(0xFF2E7D32),
    Color(0xFFEF6C00),
    Color(0xFF0288D1),
    Color(0xFF8E24AA)
)

/** Unisce due gameti allele per allele, mettendo sempre prima la lettera maiuscola. */
private fun combine(a: String, b: String): String {
    if (a.length != b.length) return a + b
    val sb = StringBuilder(a.length)
    for (i in a.indices) {
        val ca = a[i]
        val cb = b[i]
        val upper = ca.isUpperCase() || cb.isUpperCase()
        if (upper && (ca.isLowerCase() || cb.isLowerCase())) {
            sb.append(ca.uppercaseChar()).append(cb.lowercaseChar())
        } else {
            sb.append(ca).append(cb)
        }
    }
    return sb.toString()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PunnettLessonScreen(
    lesson: Lesson,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var presetIndex by remember { mutableStateOf(0) }
    var selected by remember { mutableStateOf<Pair<Int, Int>?>(null) }
    val preset = presets[presetIndex]

    // Cifre che si animano quando cambia l'incrocio
    val gridCells = preset.gametesB.size * preset.gametesA.size

    val genotypeCounts: Map<String, Int> = buildMap {
        preset.gametesB.forEachIndexed { r, gb ->
            preset.gametesA.forEachIndexed { c, ga ->
                val g = combine(gb, ga)
                put(g, (this[g] ?: 0) + 1)
            }
        }
    }

    val phenotypeCounts: Map<String, Int> = buildMap {
        preset.gametesB.forEach { gb ->
            preset.gametesA.forEach { ga ->
                val p = preset.phenotypeOf(combine(gb, ga))
                put(p, (this[p] ?: 0) + 1)
            }
        }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Indietro")
                }
                Column(Modifier.weight(1f)) {
                    Text(
                        "${lesson.emoji} ${lesson.title}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        lesson.subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(presets) { p ->
                    val i = presets.indexOf(p)
                    FilterChip(
                        selected = presetIndex == i,
                        onClick = {
                            presetIndex = i
                            selected = null
                        },
                        label = { Text(p.name) }
                    )
                }
            }
        }

        item {
            GlassCard(contentPadding = PaddingValues(14.dp)) {
                Text(
                    preset.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                    Column(Modifier.weight(1f)) {
                        Text(
                            "Genitore A: ${preset.parentA}",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            "Gameti: ${preset.gametesA.joinToString(", ")}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Column(Modifier.weight(1f)) {
                        Text(
                            "Genitore B: ${preset.parentB}",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            "Gameti: ${preset.gametesB.joinToString(", ")}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        item {
            PunnettGrid(
                preset = preset,
                selected = selected,
                onSelect = { selected = it }
            )
        }

        item {
            // Dettaglio della cella selezionata
            val sel = selected
            if (sel != null && sel.first < preset.gametesB.size && sel.second < preset.gametesA.size) {
                val g = combine(preset.gametesB[sel.first], preset.gametesA[sel.second])
                GlassCard(contentPadding = PaddingValues(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text(
                                "Genotipo  $g",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                "Fenotipo: ${preset.phenotypeOf(g)}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Text(
                            "${preset.phenotypeOf(g)}",
                            fontSize = 14.sp,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.End
                        )
                    }
                }
            } else {
                Text(
                    "👆 Tocca una cella della griglia per vedere il genotipo e il fenotipo.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        item {
            // Conteggi dei genotipi
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Genotipi della prole",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    "$gridCells celle",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(Modifier.height(6.dp))
            genotypeCounts.entries
                .sortedByDescending { it.value }
                .forEach { (g, n) ->
                    GlassCard(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                g,
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.width(72.dp)
                            )
                            Text(
                                "${(n * 100f / gridCells).toInt()}%",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.width(44.dp)
                            )
                            Text(
                                "× $n",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
        }

        item {
            // Barre dei fenotipi animate
            Text(
                "Fenotipi della prole (${genotypeCounts.values.sum()} combinazioni)",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(6.dp))
            phenotypeCounts.entries
                .sortedByDescending { it.value }
                .forEachIndexed { i, (ph, n) ->
                    val fraction = n.toFloat() / gridCells
                    val barFraction by animateFloatAsState(
                        targetValue = fraction,
                        animationSpec = tween(600),
                        label = "bar"
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            ph,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.width(116.dp)
                        )
                        Box(
                            Modifier
                                .weight(1f)
                                .height(14.dp)
                                .clip(RoundedCornerShape(7.dp))
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Box(
                                Modifier
                                    .fillMaxWidth(barFraction)
                                    .height(14.dp)
                                    .clip(RoundedCornerShape(7.dp))
                                    .background(barColors[i % barColors.size])
                            )
                        }
                        Spacer(Modifier.width(8.dp))
                        Text(
                            "${(n * 100f / gridCells).toInt()}%",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.width(38.dp),
                            textAlign = TextAlign.End
                        )
                    }
                }
        }

        item {
            Spacer(Modifier.height(4.dp))
            Text(
                "💡 Ricorda: nel quadrato, ogni casella è una possibile combinazione dei gameti " +
                    "dei due genitori. Se il fenotipo ti sembra strano, prova a toccare più celle!",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun PunnettGrid(
    preset: PunnettPreset,
    selected: Pair<Int, Int>?,
    onSelect: (Pair<Int, Int>) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        // Intestazione: gameti del genitore A (colonne)
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Spacer(Modifier.width(34.dp))
            preset.gametesA.forEach { ga ->
                GameteLabel(ga, Modifier.weight(1f))
            }
        }
        preset.gametesB.forEachIndexed { r, gb ->
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                GameteLabel(gb, Modifier.width(34.dp))
                preset.gametesA.forEachIndexed { c, ga ->
                    val isSel = selected == (r to c)
                    val targetColor = if (isSel) MaterialTheme.colorScheme.primaryContainer
                    else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.8f)
                    val bg by animateColorAsState(
                        targetValue = targetColor,
                        animationSpec = tween(250),
                        label = "cell"
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1.35f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(bg)
                            .clickable { onSelect(r to c) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            combine(gb, ga),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = if (isSel) FontWeight.Bold else FontWeight.SemiBold,
                            color = if (isSel) MaterialTheme.colorScheme.onPrimaryContainer
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun GameteLabel(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .aspectRatio(1.35f)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.14f)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}