package com.brahma.bioquaderno.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brahma.bioquaderno.data.Block
import com.brahma.bioquaderno.data.Chapter
import com.brahma.bioquaderno.data.Section
import com.brahma.bioquaderno.data.Subject
import com.brahma.bioquaderno.data.Subjects
import com.brahma.bioquaderno.ui.components.GlassCard
import com.brahma.bioquaderno.ui.components.ScreenHeader
import com.brahma.bioquaderno.ui.components.SearchField
import com.brahma.bioquaderno.ui.components.SubjectPillsRow

sealed interface StudyPath {
    data object Home : StudyPath
    data class Chapter(val id: Int) : StudyPath
    data class Section(val id: Int, val index: Int) : StudyPath
}

@Composable
fun StudyTab(
    subjects: List<Subject>,
    currentSubjectId: String?,
    onSubject: (String?) -> Unit,
    allChapters: List<Chapter>,
    studiedIds: List<Int>,
    onToggleStudied: (Int) -> Unit,
    onSettings: () -> Unit,
    onAddPhotos: () -> Unit,
    onManageSubjects: () -> Unit,
    onManageBooks: () -> Unit,
    modifier: Modifier = Modifier
) {
    var path by remember { mutableStateOf<StudyPath>(StudyPath.Home) }
    var query by rememberSaveable { mutableStateOf("") }

    BackHandler(enabled = path != StudyPath.Home) {
        path = when (val p = path) {
            is StudyPath.Section -> StudyPath.Chapter(p.id)
            else -> StudyPath.Home
        }
    }

    Column(modifier.fillMaxSize()) {
        when (val p = path) {
            StudyPath.Home -> StudyHome(
                subjects = subjects,
                currentSubjectId = currentSubjectId,
                onSubject = onSubject,
                allChapters = allChapters,
                query = query,
                onQuery = { query = it },
                studiedIds = studiedIds,
                onToggleStudied = onToggleStudied,
                onOpen = { id -> path = StudyPath.Chapter(id) },
                onSettings = onSettings,
                onAddPhotos = onAddPhotos,
                onManageSubjects = onManageSubjects,
                onManageBooks = onManageBooks,
                modifier = Modifier.weight(1f)
            )

            is StudyPath.Chapter -> {
                val ch = allChapters.first { it.id == p.id }
                ScreenHeader(
                    title = ch.title,
                    subtitle = "${ch.icon}  ${ch.sections.size} sezioni · ${if (p.id in studiedIds) "studiato" else "da studiare"}",
                    onBack = { path = StudyPath.Home },
                    actions = {
                        IconButton(onClick = { onToggleStudied(p.id) }) {
                            Icon(
                                imageVector = if (p.id in studiedIds) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                                contentDescription = "Segna come studiato",
                                tint = if (p.id in studiedIds) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                )
                ChapterBody(ch, onOpen = { i -> path = StudyPath.Section(p.id, i) }, modifier = Modifier.weight(1f))
            }

            is StudyPath.Section -> {
                val ch = allChapters.first { it.id == p.id }
                val section = ch.sections[p.index]
                ScreenHeader(title = section.title, subtitle = "${ch.icon} ${ch.title}", onBack = { path = StudyPath.Chapter(p.id) })
                SectionBody(section, modifier = Modifier.weight(1f))
            }
        }
    }
}

// ---------------------------------------------------------------------------
// Home: pillole materia + elenco dei capitoli con ricerca
// ---------------------------------------------------------------------------

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun StudyHome(
    subjects: List<Subject>,
    currentSubjectId: String?,
    onSubject: (String?) -> Unit,
    allChapters: List<Chapter>,
    query: String,
    onQuery: (String) -> Unit,
    studiedIds: List<Int>,
    onToggleStudied: (Int) -> Unit,
    onOpen: (Int) -> Unit,
    onSettings: () -> Unit,
    onAddPhotos: () -> Unit,
    onManageSubjects: () -> Unit,
    onManageBooks: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scoped = remember(subjects, currentSubjectId) {
        if (currentSubjectId != null) subjects.filter { it.id == currentSubjectId } else subjects
    }
    val totalChapters = remember(scoped) { scoped.sumOf { it.chapters.size } }
    val visibleChapters = remember(scoped, query) { Subjects.searchChapters(scoped, query) }

    val scopeLabel = remember(scoped) {
        scoped.firstOrNull()?.let { "${it.emoji} ${it.name}" } ?: "Tutte le materie"
    }

    Column(modifier.fillMaxSize()) {
        ScreenHeader(
            title = "BioQuaderno",
            subtitle = "$scopeLabel · $totalChapters capitoli" +
                if (query.isNotEmpty()) " · filtro \"$query\"" else "",
            actions = {
                IconButton(onClick = onManageBooks) {
                    Icon(
                        Icons.AutoMirrored.Filled.MenuBook,
                        contentDescription = "I miei libri digitali"
                    )
                }
                IconButton(onClick = onAddPhotos) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Aggiungi foto con testo"
                    )
                }
                IconButton(onClick = onManageSubjects) {
                    Icon(
                        Icons.Filled.Checklist,
                        contentDescription = "Le mie materie"
                    )
                }
                IconButton(onClick = onSettings) {
                    Icon(
                        Icons.Filled.Settings,
                        contentDescription = "Personalizza l'interfaccia"
                    )
                }
            }
        )
        SubjectPillsRow(
            subjects = subjects,
            currentSubjectId = currentSubjectId,
            onSelect = onSubject,
            modifier = Modifier.padding(top = 4.dp)
        )
        Spacer(Modifier.height(6.dp))
        SearchField(
            value = query,
            onValueChange = onQuery,
            placeholder = "Cerca un argomento…",
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        if (totalChapters == 0) {
            Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    "Nessuna materia attiva: apri \"Le mie materie\" (icona lista ✓ in alto a destra) e attivane almeno una.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(24.dp)
                )
            }
        } else if (visibleChapters.isEmpty()) {
            Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    "Nessun risultato per \"$query\"",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(visibleChapters, key = { it.id }) { ch ->
                    ChapterCard(
                        ch = ch,
                        studied = ch.id in studiedIds,
                        onOpen = { onOpen(ch.id) },
                        onToggle = { onToggleStudied(ch.id) },
                        modifier = Modifier.animateItem()
                    )
                }
            }
        }
    }
}

@Composable
private fun ChapterCard(
    ch: Chapter,
    studied: Boolean,
    onOpen: () -> Unit,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    GlassCard(
        modifier = modifier.fillMaxWidth(),
        onClick = onOpen
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(ch.icon, fontSize = 24.sp)
            }
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        ch.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f, fill = false)
                    )
                    Spacer(Modifier.width(6.dp))
                    Icon(
                        imageVector = if (studied) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                        contentDescription = if (studied) "Studiato" else "Non studiato",
                        tint = if (studied) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                        modifier = Modifier.clickable { onToggle() }
                    )
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    ch.summary,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Icon(
                Icons.Filled.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// ---------------------------------------------------------------------------
// Capitolo: elenco delle sezioni
// ---------------------------------------------------------------------------

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ChapterBody(ch: Chapter, onOpen: (Int) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            GlassCard(
                modifier = Modifier.fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text("📖 In questo capitolo", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.height(4.dp))
                    Text(ch.summary, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
        itemsIndexed(ch.sections) { i, s ->
            GlassCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItem(),
                onClick = { onOpen(i) }
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        "${i + 1}. ${s.title}",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        previewOf(s),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

private fun previewOf(s: Section): String =
    s.blocks.firstOrNull()?.let {
        when (it) {
            is Block.Text -> it.s
            is Block.Bullet -> it.s
            is Block.Key -> it.s
        }
    } ?: ""

// ---------------------------------------------------------------------------
// Sezione: il corpo della lezione
// ---------------------------------------------------------------------------

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SectionBody(section: Section, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 28.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(section.blocks) { b ->
            when (b) {
                is Block.Text -> Text(
                    b.s,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.animateItem()
                )

                is Block.Key -> Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem()
                        .background(
                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f),
                            RoundedCornerShape(12.dp)
                        )
                        .padding(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.Top) {
                        Text("💡  ", style = MaterialTheme.typography.bodyLarge)
                        Text(
                            b.s,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }

                is Block.Bullet -> Row(Modifier.fillMaxWidth().padding(start = 4.dp)) {
                    Text(
                        "•  ",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        b.s,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.animateItem()
                    )
                }
            }
        }
    }
}