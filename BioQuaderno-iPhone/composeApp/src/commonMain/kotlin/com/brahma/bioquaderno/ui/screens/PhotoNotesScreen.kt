package com.brahma.bioquaderno.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brahma.bioquaderno.data.PhotoNote
import com.brahma.bioquaderno.data.PhotoNoteStore
import com.brahma.bioquaderno.platform.Platform
import com.brahma.bioquaderno.platform.platformImage
import com.brahma.bioquaderno.platform.rememberPhotoPicker
import com.brahma.bioquaderno.ui.components.GlassCard
import com.brahma.bioquaderno.ui.components.ScreenHeader
import com.brahma.bioquaderno.util.formatDate
import kotlinx.coroutines.launch

/**
 * Schermata "Appunti dalle foto": scegli più foto (anche senza internet),
 * il modello OCR dell'app estrae il testo (ML Kit su Android, Vision su iPhone),
 * e le note restano salvate. Il codice è identico sulle due piattaforme.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoNotesScreen(
    notes: List<PhotoNote>,
    onAdd: (PhotoNote) -> Unit,
    onUpdate: (PhotoNote) -> Unit,
    onDelete: (PhotoNote) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()

    var processing by remember { mutableStateOf(false) }
    var progressText by remember { mutableStateOf("") }
    var expandedId by remember { mutableStateOf<Long?>(null) }
    var editing by remember { mutableStateOf<PhotoNote?>(null) }
    var deleting by remember { mutableStateOf<PhotoNote?>(null) }

    val pickLauncher = rememberPhotoPicker { picked ->
        if (picked.isNotEmpty()) {
            scope.launch {
                processing = true
                try {
                    picked.forEachIndexed { i, id ->
                        progressText = "Analisi foto ${i + 1} di ${picked.size}: estraggo il testo…"
                        val path = PhotoNoteStore.importImage(id)
                        if (path != null) {
                            val bytes = Platform.readBytes(path)
                            val text = if (bytes != null) Platform.ocr(bytes) else ""
                            onAdd(PhotoNote(System.currentTimeMillis(), path, text))
                        }
                    }
                } finally {
                    processing = false
                }
            }
        }
    }

    Column(modifier.fillMaxSize()) {
        ScreenHeader(
            title = "📸 Appunti dalle foto",
            subtitle = "${notes.size} note · testo estratto dal modello OCR, anche offline",
            onBack = onBack,
            actions = {
                if (!processing) {
                    IconButton(onClick = { pickLauncher.launch() }) {
                        Icon(Icons.Filled.Add, contentDescription = "Aggiungi foto")
                    }
                }
            }
        )

        when {
            processing -> {
                GlassCard(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(28.dp))
                        Column {
                            Text(
                                "Elaborazione in corso…",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                progressText,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            notes.isEmpty() -> {
                Box(
                    Modifier.weight(1f).fillMaxWidth().padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    GlassCard(Modifier.fillMaxWidth()) {
                        Column(
                            Modifier.fillMaxWidth().padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("🖼️", fontSize = 40.sp)
                            Text(
                                "Nessuna foto ancora",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                "Fotografa pagine di libri, appunti o schemi: " +
                                    "il modello di riconoscimento testo leggerà le foto " +
                                    "e salverà il testo nell'app, tutto offline.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(Modifier.height(6.dp))
                            Button(
                                onClick = { pickLauncher.launch() },
                                modifier = Modifier.fillMaxWidth().height(48.dp)
                            ) {
                                Icon(Icons.Filled.Add, contentDescription = null, modifier = Modifier.size(20.dp))
                                Spacer(Modifier.width(8.dp))
                                Text("Aggiungi foto")
                            }
                        }
                    }
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(notes, key = { it.id }) { n ->
                        val open = expandedId == n.id
                        GlassCard(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(0.dp),
                            onClick = { expandedId = if (open) null else n.id }
                        ) {
                            Column {
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    NoteThumbnail(n.imagePath, 56.dp)
                                    Spacer(Modifier.width(12.dp))
                                    Column(Modifier.weight(1f)) {
                                        Text(
                                            noteDate(n.date),
                                            style = MaterialTheme.typography.labelMedium,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                        Spacer(Modifier.height(2.dp))
                                        Text(
                                            if (n.text.isBlank()) "Nessun testo riconosciuto — tocca per modificare."
                                            else n.text,
                                            style = MaterialTheme.typography.bodyMedium,
                                            maxLines = if (open) Int.MAX_VALUE else 2,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                    IconButton(onClick = { deleting = n }) {
                                        Icon(
                                            Icons.Filled.Delete,
                                            contentDescription = "Elimina",
                                            tint = MaterialTheme.colorScheme.error
                                        )
                                    }
                                }
                                AnimatedVisibility(
                                    visible = open,
                                    enter = expandVertically() + fadeIn(),
                                    exit = shrinkVertically() + fadeOut()
                                ) {
                                    Row(Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp)) {
                                        TextButton(onClick = { editing = n }) {
                                            Icon(Icons.Filled.Edit, contentDescription = null, modifier = Modifier.size(18.dp))
                                            Spacer(Modifier.width(6.dp))
                                            Text("Modifica testo")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    editing?.let { note ->
        EditTextDialog(
            note = note,
            onSave = { newText ->
                onUpdate(note.copy(text = newText))
                editing = null
            },
            onDismiss = { editing = null }
        )
    }

    deleting?.let { note ->
        AlertDialog(
            onDismissRequest = { deleting = null },
            title = { Text("Eliminare questa nota?") },
            text = { Text("La foto e il testo verranno rimossi dall'app.") },
            confirmButton = {
                TextButton(onClick = {
                    onDelete(note)
                    deleting = null
                }) { Text("Sì, elimina") }
            },
            dismissButton = {
                TextButton(onClick = { deleting = null }) { Text("Annulla") }
            }
        )
    }
}

/** Anteprima dell'immagine, ridimensionata per non caricare foto giganti. */
@Composable
private fun NoteThumbnail(path: String, size: Dp) {
    val bytes = remember(path) { Platform.readBytes(path) }
    // platformImage è @Composable: va chiamato nel corpo composable, non dentro remember
    val image = if (bytes != null) platformImage(bytes) else null
    if (image != null) {
        Image(
            bitmap = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(size)
                .clip(RoundedCornerShape(12.dp))
        )
    } else {
        Box(
            modifier = Modifier
                .size(size)
                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("🖼️", fontSize = 22.sp)
        }
    }
}

@Composable
private fun EditTextDialog(
    note: PhotoNote,
    onSave: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var text by remember(note.id) { mutableStateOf(note.text) }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Modifica il testo estratto", fontWeight = FontWeight.Bold) },
        text = {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.fillMaxWidth(),
                minLines = 4,
                maxLines = 10,
                placeholder = { Text("Il testo letto è qui: correggilo se serve…") }
            )
        },
        confirmButton = {
            TextButton(onClick = { onSave(text.trim()) }) { Text("Salva") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Annulla") }
        }
    )
}

private fun noteDate(ts: Long): String = formatDate(ts)