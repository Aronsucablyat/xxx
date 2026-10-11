package com.brahma.bioquaderno.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brahma.bioquaderno.data.Subject
import com.brahma.bioquaderno.ui.components.GlassCard
import com.brahma.bioquaderno.ui.components.ScreenHeader

/**
 * "Le mie materie": attiva/disattiva le materie dell'istituto tecnico, raggruppate
 * per indirizzo. Quelle attive appaiono nelle pillole della home e nel Prof.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectManagementScreen(
    subjects: List<Subject>,
    enabledIds: Set<String>,
    onToggle: (String) -> Unit,
    onSetAll: (Boolean) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val grouped = remember(subjects) { subjects.groupBy { it.indirizzo }.toList() }
    val activeCount = enabledIds.size

    Column(modifier.fillMaxSize()) {
        ScreenHeader(
            title = "Le mie materie",
            subtitle = "${activeCount} di ${subjects.size} attive · le attive compaiono nella home e nel Prof",
            onBack = onBack,
            actions = {
                if (activeCount < subjects.size) {
                    TextButton(onClick = { onSetAll(true) }) { Text("Tutte", fontWeight = FontWeight.Bold) }
                }
                if (activeCount > 0) {
                    TextButton(onClick = { onSetAll(false) }) { Text("Nessuna", fontWeight = FontWeight.Bold) }
                }
            }
        )

        if (activeCount == 0) {
            Box(Modifier.weight(1f).fillMaxWidth().padding(24.dp), contentAlignment = Alignment.Center) {
                Text(
                    "Attiva almeno una materia: la home, il glossario, le flashcard, i quiz e il Prof " +
                        "si riempiranno con i suoi contenuti.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                grouped.forEach { (indirizzo, list) ->
                    item(key = "hdr_$indirizzo") {
                        Text(
                            indirizzo,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 8.dp, start = 4.dp, bottom = 2.dp)
                        )
                    }
                    items(list, key = { it.id }) { s ->
                        SubjectRow(
                            subject = s,
                            enabled = s.id in enabledIds,
                            onToggle = { onToggle(s.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SubjectRow(subject: Subject, enabled: Boolean, onToggle: () -> Unit) {
    GlassCard(Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(Color(subject.colorHex), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(subject.emoji, fontSize = 22.sp)
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    subject.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    "${subject.chapters.size} capitoli · ${subject.glossary.size} voci · " +
                        "${subject.flashcards.size} flashcard · ${subject.questions.size} quiz",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(Modifier.width(8.dp))
            Switch(checked = enabled, onCheckedChange = { onToggle() })
        }
    }
}