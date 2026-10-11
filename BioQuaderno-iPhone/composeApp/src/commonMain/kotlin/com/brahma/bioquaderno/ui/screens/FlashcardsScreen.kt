package com.brahma.bioquaderno.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brahma.bioquaderno.data.Subject
import com.brahma.bioquaderno.ui.components.GlassCard
import com.brahma.bioquaderno.ui.components.ScreenHeader
import com.brahma.bioquaderno.ui.components.SubjectPillsRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashcardsTab(
    subjects: List<Subject>,
    currentSubjectId: String?,
    onSubject: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    val scoped = remember(subjects, currentSubjectId) {
        if (currentSubjectId != null) subjects.filter { it.id == currentSubjectId } else subjects
    }
    val allCards = remember(scoped) { scoped.flatMap { it.flashcards } }
    val topics = remember(allCards) { allCards.map { it.topic }.distinct() }
    val scopeLabel = remember(scoped) {
        scoped.firstOrNull()?.let { "${it.emoji} ${it.name}" } ?: "Tutte le materie"
    }

    var topic by rememberSaveable { mutableStateOf("Tutte") }
    var deck by remember { mutableStateOf(allCards.shuffled()) }
    var index by remember { mutableIntStateOf(0) }
    var flipped by remember { mutableStateOf(false) }
    var finished by remember { mutableStateOf(false) }

    fun reset(newTopic: String) {
        topic = newTopic
        val filtered = allCards.filter { newTopic == "Tutte" || it.topic == newTopic }
        deck = filtered.shuffled()
        index = 0
        flipped = false
        finished = deck.isEmpty()
    }

    // Quando cambia la materia, si ricomincia dal mazzo completo.
    LaunchedEffect(allCards) { reset("Tutte") }

    Column(modifier.fillMaxSize()) {
        ScreenHeader(
            title = "Flashcard",
            subtitle = "$scopeLabel · indovina, gira la carta e valuta se la sapevi"
        )
        SubjectPillsRow(
            subjects = subjects,
            currentSubjectId = currentSubjectId,
            onSelect = onSubject,
            modifier = Modifier.padding(top = 4.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listOf("Tutte") + topics) { t ->
                FilterChip(
                    selected = topic == t,
                    onClick = { reset(t) },
                    label = { Text(t) }
                )
            }
        }
        Spacer(Modifier.height(8.dp))

        if (deck.isEmpty()) {
            Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    if (allCards.isEmpty()) "Nessuna flashcard nelle materie attive: attivane qualcuna dal menu \"Le mie materie\"."
                    else "Nessuna carta in questo argomento",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(24.dp)
                )
            }
            return@Column
        }

        if (finished) {
            Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                GlassCard(Modifier.fillMaxWidth().padding(24.dp)) {
                    Column(
                        Modifier.fillMaxWidth().padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text("🎓", fontSize = 52.sp)
                        Text("Mazzo completato!", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Text("Hai ripassato ${deck.size} carte su \"$topic\".", style = MaterialTheme.typography.bodyMedium)
                        Spacer(Modifier.height(4.dp))
                        Button(onClick = { reset(topic) }, modifier = Modifier.fillMaxWidth()) {
                            Text("Ricomincia")
                        }
                    }
                }
            }
            return@Column
        }

        val current = deck[index]
        Column(Modifier.weight(1f).fillMaxWidth().padding(16.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    "Carta ${index + 1} di ${deck.size}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    if (!flipped) "Premi per girare" else "Ecco la risposta",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            LinearProgressIndicator(
                progress = { index / deck.size.toFloat() },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )

            // Carta con flip 3D
            val rotation by animateFloatAsState(
                targetValue = if (flipped) 180f else 0f,
                animationSpec = tween(500),
                label = "flip"
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .graphicsLayer {
                        rotationY = rotation
                        cameraDistance = 12f * density
                    }
            ) {
                if (rotation <= 90f) {
                    // Fronte
                    GlassCard(
                        modifier = Modifier.fillMaxSize(),
                        onClick = { flipped = !flipped }
                    ) {
                        Box(Modifier.fillMaxSize().padding(20.dp), contentAlignment = Alignment.Center) {
                            Column(
                                Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(current.topic, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
                                Spacer(Modifier.height(12.dp))
                                Text(
                                    current.question,
                                    style = MaterialTheme.typography.titleMedium,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                } else {
                    // Retro: controruotato di 180° per non risultare specchiato
                    GlassCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer {
                                rotationY = 180f
                                cameraDistance = 12f * density
                            },
                        onClick = { flipped = !flipped }
                    ) {
                        Box(Modifier.fillMaxSize().padding(20.dp), contentAlignment = Alignment.Center) {
                            Column(
                                Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text("💡 Risposta", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.tertiary)
                                Spacer(Modifier.height(12.dp))
                                Text(
                                    current.answer,
                                    style = MaterialTheme.typography.titleMedium,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(
                    onClick = {
                        if (index < deck.size - 1) {
                            val reordered = deck.toMutableList()
                            val c = reordered.removeAt(index)
                            reordered.add(c)
                            deck = reordered
                        }
                        flipped = false
                    },
                    modifier = Modifier.weight(1f),
                    enabled = flipped
                ) {
                    Icon(Icons.Filled.ThumbDown, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Ripasso")
                }
                Button(
                    onClick = {
                        index++
                        if (index >= deck.size) finished = true else flipped = false
                    },
                    modifier = Modifier.weight(1f),
                    enabled = flipped
                ) {
                    Icon(Icons.Filled.ThumbUp, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("La sapevo")
                }
            }
        }
    }
}