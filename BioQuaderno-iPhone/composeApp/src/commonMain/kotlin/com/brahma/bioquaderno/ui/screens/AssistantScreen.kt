package com.brahma.bioquaderno.ui.screens

import com.brahma.bioquaderno.platform.Prefs
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.brahma.bioquaderno.data.Assistant
import com.brahma.bioquaderno.data.GeminiClient
import com.brahma.bioquaderno.data.Lesson
import com.brahma.bioquaderno.data.PhotoNoteStore
import com.brahma.bioquaderno.data.Subject
import com.brahma.bioquaderno.platform.Platform
import com.brahma.bioquaderno.platform.platformImage
import com.brahma.bioquaderno.platform.rememberPhotoPicker
import com.brahma.bioquaderno.ui.components.GlassCard
import com.brahma.bioquaderno.ui.components.ScreenHeader
import com.brahma.bioquaderno.ui.components.SubjectPillsRow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val KEY_GEMINI = "gemini_key"
const val KEY_MODEL = "gemini_model"
private const val DEFAULT_MODEL = "gemini-2.5-flash"

/** Un messaggio della chat: testo semplice oppure invito ad aprire una lezione interattiva. */
sealed interface ChatItem {
    val fromUser: Boolean

    data class ChatText(
        override val fromUser: Boolean,
        val text: String,
        val photoPath: String? = null  // foto degli appunti allegata al messaggio
    ) : ChatItem
    data class ChatLesson(override val fromUser: Boolean, val text: String, val lesson: Lesson) : ChatItem
}

val introMessage: ChatItem = ChatItem.ChatText(
    fromUser = false,
    text = "Ciao! 👋 Sono Prof, la mini-copia dell'assistente dentro l'app. 📚\n\n" +
        "Uso gli appunti di tutte le tue materie dell'istituto tecnico (biologia, chimica, economia, informatica, meccanica…) e funziona offline, senza internet. " +
        "Se importi il tuo libro di testo digitale (pulsante 📚 in alto a destra nella home), rispondo cercando anche nelle pagine vere del libro. " +
        "Se vuoi risposte ancora più libere, inserisci una chiave API di Gemini nelle impostazioni ⚙️ in alto a destra.\n\n" +
        "Prova a scrivermi \"Cos'è la fotosintesi?\", \"Fammi una domanda\" oppure \"Lezione: quadrato di Punnet\"."
)

private const val GEMINI_SYSTEM =
    "Sei Prof, un assistente amichevole che insegna a uno studente di un istituto tecnico superiore italiano le " +
        "sue materie di studio (biologia, chimica, economia aziendale, informatica, meccanica, elettrotecnica, …). " +
        "Rispondi sempre in italiano, in modo chiaro, semplice e con esempi concreti. " +
        "Se la domanda non riguarda le materie scolastiche, rispondi con gentilezza e riporta il discorso allo studio. " +
        "Sii sintetico ma completo (non più di ~500 parole)."

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssistantTab(
    prefs: Prefs,
    messagesState: MutableState<List<ChatItem>>,
    keyState: MutableState<String>,
    modelState: MutableState<String>,
    allSubjects: List<Subject>,
    currentSubject: Subject?,
    onSubject: (String?) -> Unit,
    onOpenLesson: (Lesson) -> Unit,
    bookPages: List<String> = emptyList(),
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    var input by remember { mutableStateOf("") }
    var busy by remember { mutableStateOf(false) }
    var showSettings by remember { mutableStateOf(false) }
    var showClearConfirm by remember { mutableStateOf(false) }
    var lastQuestion by remember { mutableStateOf<String?>(null) }

    val listState = rememberLazyListState()
    LaunchedEffect(messagesState.value.size) {
        if (messagesState.value.isNotEmpty()) listState.animateScrollToItem(messagesState.value.size - 1)
    }

    // Suggerimenti dinamici: dipendono dalla materia selezionata e dal suo glossario.
    val suggestions = remember(allSubjects, currentSubject?.id) {
        val sc = if (currentSubject != null) listOf(currentSubject) else allSubjects
        val terms = sc.flatMap { it.glossary }.map { it.term }.distinct().shuffled().take(3)
        buildList {
            add("Fammi una domanda")
            add("Lezione: quadrato di Punnet")
            if (currentSubject != null) add("Cosa devo sapere per ${currentSubject.name}?")
            terms.forEach { add("Cos'è $it?") }
        }.distinct().take(5)
    }

    fun ask(raw: String, photoPath: String? = null) {
        val question = raw.trim()
        if (question.isEmpty() || busy) return
        input = ""
        val previous = lastQuestion
        lastQuestion = question
        messagesState.value = messagesState.value + ChatItem.ChatText(true, question, photoPath)
        busy = true
        val gKey = keyState.value.trim()
        val currentId = currentSubject?.id
        scope.launch {
            if (gKey.isEmpty()) delay(350) // piccolo ritardo per un effetto più naturale

            // Prima cosa: la domanda merita una lezione interattiva?
            val lesson = Assistant.detectLesson(question)
            val reply: ChatItem = if (lesson != null) {
                ChatItem.ChatLesson(
                    fromUser = false,
                    text = Assistant.lessonIntro(lesson),
                    lesson = lesson
                )
            } else if (gKey.isNotEmpty()) {
                val res = GeminiClient.ask(
                    apiKey = gKey,
                    model = modelState.value,
                    system = GEMINI_SYSTEM + Assistant.geminiContext(question, allSubjects, currentId, bookPages),
                    question = question
                )
                val text = res.getOrElse { err ->
                    "Ops, Gemini non ha risposto (${err.message}).\n\nTi rispondo io con i miei appunti: 👇\n\n" +
                        Assistant.offlineReply(question, allSubjects, currentId, previous, bookPages)
                }
                ChatItem.ChatText(false, text)
            } else {
                ChatItem.ChatText(false, Assistant.offlineReply(question, allSubjects, currentId, previous, bookPages))
            }
            messagesState.value = messagesState.value + reply
            busy = false
        }
    }

    fun submit(q: String) { ask(q) }

    // Foto degli appunti da mandare a Prof: la lettura è fatta in locale (OCR, offline)
    var sendingPhoto by remember { mutableStateOf(false) }
    val photoPicker = rememberPhotoPicker { picked ->
        if (picked.isNotEmpty() && !busy) {
            scope.launch {
                sendingPhoto = true
                try {
                    val parts = mutableListOf<String>()
                    var firstPath: String? = null
                    picked.forEach { id ->
                        val path = PhotoNoteStore.importImage(id)
                        val text = if (path != null) {
                            val bytes = Platform.readBytes(path)
                            if (bytes != null) Platform.ocr(bytes) else ""
                        } else ""
                        if (text.isNotBlank()) parts.add(text)
                        if (firstPath == null) firstPath = path
                    }
                    val question = buildString {
                        append("📷 Ti mando una foto con i miei appunti: leggila e aiutami a capirla.")
                        if (parts.isEmpty()) {
                            append("\n\nLa foto non conteneva testo leggibile, dimmi tu cosa vuoi sapere.")
                        } else {
                            append("\n\nTesto estratto dalla foto:\n")
                            parts.forEachIndexed { i, t -> append("\n[Foto ${i + 1}]\n$t") }
                        }
                    }
                    ask(question, firstPath)
                } finally {
                    sendingPhoto = false
                }
            }
        }
    }

    val statusText: String = if (keyState.value.isNotBlank()) {
        "📡 Gemini attivo (${modelState.value})"
    } else {
        val subj = currentSubject?.let { "${it.emoji} ${it.name}" } ?: "tutte le materie"
        "📚 Offline · $subj"
    }

    Column(modifier.fillMaxSize()) {
        ScreenHeader(
            title = "Prof",
            subtitle = statusText,
            actions = {
                IconButton(onClick = { showClearConfirm = true }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Cancella la chat")
                }
                IconButton(onClick = { showSettings = true }) {
                    Icon(Icons.Filled.Settings, contentDescription = "Impostazioni")
                }
            }
        )
        SubjectPillsRow(
            subjects = allSubjects,
            currentSubjectId = currentSubject?.id,
            onSelect = onSubject,
            modifier = Modifier.padding(top = 4.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(suggestions) { s ->
                AssistChip(onClick = { submit(s) }, label = { Text(s) })
            }
        }

        Text(
            text = if (sendingPhoto) "Leggo le foto…" else if (busy) "Prof sta scrivendo…" else "Scrivi una domanda…",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 4.dp)
        )

        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f).fillMaxWidth(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(messagesState.value) { _, m ->
                MessageBubble(m, onOpenLesson = onOpenLesson)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 10.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Chiedi a Prof…") },
                shape = RoundedCornerShape(24.dp),
                maxLines = 4
            )
            Spacer(Modifier.width(8.dp))
            IconButton(
                onClick = { photoPicker.launch() },
                enabled = !busy && !sendingPhoto,
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = CircleShape
                    )
            ) {
                Icon(
                    Icons.Filled.AddAPhoto,
                    contentDescription = "Allega una foto dei tuoi appunti",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(Modifier.width(6.dp))
            IconButton(
                onClick = { submit(input) },
                enabled = input.isNotBlank() && !busy,
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = if (input.isNotBlank() && !busy) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.surfaceVariant,
                        shape = CircleShape
                    )
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Invia",
                    tint = if (input.isNotBlank() && !busy) MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }

    if (showSettings) {
        AlertDialog(
            onDismissRequest = {
                showSettings = false
                resetFromPrefs(prefs, keyState, modelState)
            },
            title = { Text("Impostazioni Prof", fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        "Senza chiave uso gli appunti dell'app (offline, gratis). " +
                            "Con una chiave API di Gemini rispondo con l'AI vera: la crei gratuitamente su aistudio.google.com.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    OutlinedTextField(
                        value = keyState.value,
                        onValueChange = { keyState.value = it },
                        label = { Text("Chiave API Gemini") },
                        placeholder = { Text("AIza...") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = modelState.value,
                        onValueChange = { modelState.value = it },
                        label = { Text("Modello (es. gemini-2.5-flash)") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = if (keyState.value.isBlank()) "● Modalità offline attiva"
                        else "● Gemini attivo (${modelState.value})",
                        style = MaterialTheme.typography.labelLarge,
                        color = if (keyState.value.isBlank()) MaterialTheme.colorScheme.onSurfaceVariant
                        else MaterialTheme.colorScheme.primary
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    val m = modelState.value.trim().ifBlank { DEFAULT_MODEL }
                    modelState.value = m
                    prefs.put(KEY_GEMINI, keyState.value.trim())
                    prefs.put(KEY_MODEL, m)
                    showSettings = false
                }) { Text("Salva") }
            },
            dismissButton = {
                TextButton(onClick = {
                    showSettings = false
                    resetFromPrefs(prefs, keyState, modelState)
                }) { Text("Annulla") }
            }
        )
    }

    if (showClearConfirm) {
        AlertDialog(
            onDismissRequest = { showClearConfirm = false },
            title = { Text("Cancellare la chat?") },
            text = { Text("La conversazione verrà azzerata.") },
            confirmButton = {
                TextButton(onClick = {
                    messagesState.value = listOf(introMessage)
                    showClearConfirm = false
                }) { Text("Sì") }
            },
            dismissButton = {
                TextButton(onClick = { showClearConfirm = false }) { Text("No") }
            }
        )
    }
}

private fun resetFromPrefs(
    prefs: Prefs,
    keyState: MutableState<String>,
    modelState: MutableState<String>
) {
    keyState.value = prefs.get(KEY_GEMINI) ?: ""
    modelState.value = prefs.get(KEY_MODEL) ?: DEFAULT_MODEL
}

@Composable
private fun MessageBubble(m: ChatItem, onOpenLesson: (Lesson) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (m.fromUser) Arrangement.End else Arrangement.Start
    ) {
        when (m) {
            is ChatItem.ChatLesson -> GlassCard(
                onClick = { onOpenLesson(m.lesson) },
                modifier = Modifier.widthIn(max = 340.dp),
                contentPadding = PaddingValues(14.dp)
            ) {
                Text(m.text, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.width(1.dp))
                Button(
                    onClick = { onOpenLesson(m.lesson) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Text("${m.lesson.emoji}  Apri la lezione")
                }
            }

            is ChatItem.ChatText -> GlassCard(
                modifier = Modifier.widthIn(max = 340.dp),
                contentPadding = PaddingValues(12.dp),
                containerColor = if (m.fromUser) MaterialTheme.colorScheme.primaryContainer
                else null
            ) {
                m.photoPath?.let { path ->
                    val bytes = remember(path) { Platform.readBytes(path) }
                    val bmp = if (bytes != null) platformImage(bytes) else null
                    if (bmp != null) {
                        Image(
                            bitmap = bmp,
                            contentDescription = "Foto degli appunti",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )
                        Spacer(Modifier.height(8.dp))
                    }
                }
                Text(
                    m.text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (m.fromUser) MaterialTheme.colorScheme.onPrimaryContainer
                    else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}