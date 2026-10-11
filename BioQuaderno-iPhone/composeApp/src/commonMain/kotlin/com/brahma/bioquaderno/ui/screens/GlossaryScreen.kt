package com.brahma.bioquaderno.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.brahma.bioquaderno.data.Subject
import com.brahma.bioquaderno.data.Subjects
import com.brahma.bioquaderno.ui.components.GlassCard
import com.brahma.bioquaderno.ui.components.ScreenHeader
import com.brahma.bioquaderno.ui.components.SearchField
import com.brahma.bioquaderno.ui.components.SubjectPillsRow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GlossaryTab(
    subjects: List<Subject>,
    currentSubjectId: String?,
    onSubject: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    var query by rememberSaveable { mutableStateOf("") }
    var expanded by remember { mutableStateOf<String?>(null) }

    val scoped = remember(subjects, currentSubjectId) {
        if (currentSubjectId != null) subjects.filter { it.id == currentSubjectId } else subjects
    }
    val totalCount = remember(scoped) { scoped.sumOf { it.glossary.size } }
    val terms = remember(scoped, query) { Subjects.searchGlossary(scoped, query) }

    val scopeLabel = remember(scoped) {
        scoped.firstOrNull()?.let { "${it.emoji} ${it.name}" } ?: "Tutte le materie"
    }

    Column(modifier.fillMaxSize()) {
        ScreenHeader(
            title = "Glossario",
            subtitle = "$scopeLabel · $totalCount voci in ordine alfabetico"
        )
        SubjectPillsRow(
            subjects = subjects,
            currentSubjectId = currentSubjectId,
            onSelect = onSubject,
            modifier = Modifier.padding(top = 4.dp)
        )
        SearchField(
            value = query,
            onValueChange = { query = it },
            placeholder = "Cerca un termine…",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )
        if (terms.isEmpty()) {
            Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    if (totalCount == 0) "Nessuna materia attiva: aggiungine una dal menu \"Le mie materie\"."
                    else "Nessuna voce per \"$query\"",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(24.dp)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(terms, key = { i, _ -> "$i" }) { _, t ->
                    val isOpen = expanded == t.term
                    GlassCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItem(),
                        contentPadding = PaddingValues(14.dp),
                        onClick = { expanded = if (isOpen) null else t.term }
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                t.term,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.weight(1f)
                            )
                            Icon(
                                imageVector = if (isOpen) Icons.Filled.KeyboardArrowDown else Icons.Filled.ChevronRight,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        AnimatedVisibility(
                            visible = isOpen,
                            enter = expandVertically() + fadeIn(),
                            exit = shrinkVertically() + fadeOut()
                        ) {
                            Column {
                                Spacer(Modifier.height(8.dp))
                                Text(
                                    t.def,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}