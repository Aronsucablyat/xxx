package com.brahma.bioquaderno.ui.screens

import com.brahma.bioquaderno.platform.Prefs
import com.brahma.bioquaderno.platform.getInt
import com.brahma.bioquaderno.platform.putInt
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brahma.bioquaderno.data.Question
import com.brahma.bioquaderno.data.Subject
import com.brahma.bioquaderno.ui.components.GlassCard
import com.brahma.bioquaderno.ui.components.ScreenHeader
import com.brahma.bioquaderno.ui.components.SubjectPillsRow
import com.brahma.bioquaderno.ui.theme.LocalAppSettings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizTab(
    prefs: Prefs,
    subjects: List<Subject>,
    currentSubjectId: String?,
    onSubject: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    val scoped = remember(subjects, currentSubjectId) {
        if (currentSubjectId != null) subjects.filter { it.id == currentSubjectId } else subjects
    }
    val pool = remember(scoped) { scoped.flatMap { it.questions } }
    val quizTopics = remember(pool) { pool.map { it.topic }.distinct() }

    var topic by rememberSaveable { mutableStateOf("Tutte") }
    var started by remember { mutableStateOf(false) }
    var finishedFlag by remember { mutableStateOf(false) }
    var questions by remember { mutableStateOf<List<Question>>(emptyList()) }
    var index by remember { mutableIntStateOf(0) }
    var selected by remember { mutableStateOf<Int?>(null) }
    var correctCount by remember { mutableIntStateOf(0) }

    // Se cambia la materia si torna al setup.
    LaunchedEffect(pool) {
        topic = "Tutte"
        started = false
    }

    fun start(newTopic: String = topic) {
        topic = newTopic
        val all = if (newTopic == "Tutte") pool else pool.filter { it.topic == newTopic }
        questions = all.shuffled().let {
            if (newTopic == "Tutte") it.take(20) else it.take(10)
        }
        index = 0
        selected = null
        correctCount = 0
        started = true
        finishedFlag = false
    }

    Column(modifier.fillMaxSize()) {
        when {
            !started -> QuizSetup(
                topic = topic,
                topics = quizTopics,
                subjects = subjects,
                currentSubjectId = currentSubjectId,
                onSubject = onSubject,
                onTopic = { topic = it },
                onStart = { start() },
                best = prefs.getInt("best_$topic", -1),
                count = if (topic == "Tutte") pool.size else pool.count { it.topic == topic },
                modifier = Modifier.weight(1f)
            )

            finishedFlag -> {
                val total = questions.size
                val percent = if (total == 0) 0 else correctCount * 100 / total
                val key = "best_$topic"
                val best = remember { maxOf(correctCount, prefs.getInt(key, -1)) }
                LaunchedEffect(Unit) { prefs.putInt(key, best) }
                QuizResult(
                    correctCount = correctCount,
                    total = total,
                    topic = topic,
                    percent = percent,
                    best = best,
                    onRetry = { start(topic) },
                    onChangeTopic = { started = false },
                    modifier = Modifier.weight(1f)
                )
            }

            else -> {
                val q = questions[index]
                QuizQuestionView(
                    question = q,
                    index = index,
                    total = questions.size,
                    selected = selected,
                    onSelect = { s ->
                        if (selected == null) {
                            selected = s
                            if (s == q.correct) correctCount++
                        }
                    },
                    onNext = {
                        if (index + 1 >= questions.size) finishedFlag = true
                        else {
                            index++
                            selected = null
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

// ---------------------------------------------------------------------------
// Setup
// ---------------------------------------------------------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun QuizSetup(
    topic: String,
    topics: List<String>,
    subjects: List<Subject>,
    currentSubjectId: String?,
    onSubject: (String?) -> Unit,
    onTopic: (String) -> Unit,
    onStart: () -> Unit,
    best: Int,
    count: Int,
    modifier: Modifier = Modifier
) {
    val scopeLabel = currentSubjectId
        ?.let { id -> subjects.firstOrNull { it.id == id } }
        ?.let { "${it.emoji} ${it.name}" }
        ?: "Tutte le materie"

    Column(modifier.fillMaxSize()) {
        ScreenHeader(title = "Quiz", subtitle = "$scopeLabel · verifica subito quello che hai imparato")
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
                    onClick = { onTopic(t) },
                    label = { Text(t) }
                )
            }
        }
        Column(
            Modifier.weight(1f).fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        "${if (topic == "Tutte") "🗂️" else "📗"} Argomento: $topic",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text("Domande disponibili: $count", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        if (topic == "Tutte") "Ti verranno proposte 20 domande a campione su tutto il programma."
                        else "Ti verranno proposte 10 domande in ordine casuale.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    if (best >= 0) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Filled.Star,
                                contentDescription = null,
                                tint = Color(0xFFF9A825),
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                "Record: $best risposte giuste",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
            Button(onClick = onStart, modifier = Modifier.fillMaxWidth().height(52.dp)) {
                Icon(Icons.Filled.School, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text("Inizia il quiz")
            }
        }
    }
}

// ---------------------------------------------------------------------------
// Domanda
// ---------------------------------------------------------------------------

private enum class OptionState { Idle, Correct, Wrong, Dimmed }

@Composable
private fun QuizQuestionView(
    question: Question,
    index: Int,
    total: Int,
    selected: Int?,
    onSelect: (Int) -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                "Domanda ${index + 1} di $total",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(question.topic, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
        }
        LinearProgressIndicator(
            progress = { (index + (if (selected != null) 1 else 0)).toFloat() / total },
            modifier = Modifier.fillMaxWidth()
        )
        GlassCard(Modifier.fillMaxWidth()) {
            Text(
                question.q,
                modifier = Modifier.padding(18.dp),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        }
        Column(
            Modifier.weight(1f).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            question.options.forEachIndexed { i, opt ->
                val state = when {
                    selected == null -> OptionState.Idle
                    i == question.correct -> OptionState.Correct
                    i == selected -> OptionState.Wrong
                    else -> OptionState.Dimmed
                }
                OptionButton(
                    text = opt,
                    state = state,
                    enabled = selected == null || state == OptionState.Correct || state == OptionState.Wrong,
                    onClick = { onSelect(i) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        if (selected != null) {
            GlassCard(
                modifier = Modifier.fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            ) {
                Row(Modifier.padding(14.dp), verticalAlignment = Alignment.Top) {
                    Text("💡  ", style = MaterialTheme.typography.bodyMedium)
                    Text(question.explanation, style = MaterialTheme.typography.bodyMedium)
                }
            }
            Button(
                onClick = onNext,
                modifier = Modifier.fillMaxWidth().height(48.dp)
            ) {
                Text(if (index + 1 >= total) "Vedi il risultato" else "Domanda successiva")
            }
        }
    }
}

@Composable
private fun OptionButton(
    text: String,
    state: OptionState,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // La risposta corretta "pulsa" quando è stata scelta
    val animEnabled = state == OptionState.Correct && LocalAppSettings.current.animations
    val scale: Float = if (animEnabled) {
        val t = rememberInfiniteTransition(label = "pulse")
        val s by t.animateFloat(
            initialValue = 1f,
            targetValue = 1.05f,
            animationSpec = infiniteRepeatable(tween(380, easing = FastOutSlowInEasing), RepeatMode.Reverse),
            label = "pulseScale"
        )
        s
    } else 1f

    val colors = when (state) {
        OptionState.Correct -> ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )

        OptionState.Wrong -> ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.errorContainer,
            contentColor = MaterialTheme.colorScheme.onErrorContainer
        )

        OptionState.Dimmed -> ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )

        OptionState.Idle -> ButtonDefaults.buttonColors()
    }
    Box(modifier.graphicsLayer {
        scaleX = scale
        scaleY = scale
    }) {
        Button(
            onClick = onClick,
            enabled = enabled,
            modifier = Modifier.fillMaxWidth(),
            colors = colors
        ) {
            Text(
                text = if (state == OptionState.Correct) "✅ $text" else text,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

// ---------------------------------------------------------------------------
// Risultato
// ---------------------------------------------------------------------------

@Composable
private fun QuizResult(
    correctCount: Int,
    total: Int,
    topic: String,
    percent: Int,
    best: Int,
    onRetry: () -> Unit,
    onChangeTopic: () -> Unit,
    modifier: Modifier = Modifier
) {
    val emoji = when {
        percent >= 90 -> "🏆"
        percent >= 70 -> "🎉"
        percent >= 50 -> "👍"
        else -> "📚"
    }

    // L'emoji "rimbalza" dolcemente
    val animEnabled = LocalAppSettings.current.animations
    val emojiScale: Float = if (animEnabled) {
        val t = rememberInfiniteTransition(label = "bounce")
        val s by t.animateFloat(
            initialValue = 0.92f,
            targetValue = 1.12f,
            animationSpec = infiniteRepeatable(tween(520, easing = FastOutSlowInEasing), RepeatMode.Reverse),
            label = "bounceScale"
        )
        s
    } else 1f

    Column(
        modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(20.dp))
        GlassCard(Modifier.fillMaxWidth()) {
            Column(
                Modifier.fillMaxWidth().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    emoji,
                    fontSize = 56.sp,
                    modifier = Modifier.graphicsLayer {
                        scaleX = emojiScale
                        scaleY = emojiScale
                    }
                )
                Text(
                    if (percent >= 60) "Quiz superato!" else "Ripassa ancora…",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "$correctCount su $total risposte giuste ($percent%)",
                    style = MaterialTheme.typography.bodyLarge
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = null,
                        tint = Color(0xFFF9A825),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        "Record su \"$topic\": $best",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
        Spacer(Modifier.weight(1f))
        Text(
            if (percent < 60) "Ritorna alle schede di teoria e riprova: ce la fai!"
            else "Ottimo lavoro, continua così!",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )
        OutlinedButton(onClick = onRetry, modifier = Modifier.fillMaxWidth().height(48.dp)) {
            Icon(Icons.Filled.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text("Rifai il quiz")
        }
        Spacer(Modifier.height(10.dp))
        Button(onClick = onChangeTopic, modifier = Modifier.fillMaxWidth().height(48.dp)) {
            Text("Cambia argomento")
        }
        Spacer(Modifier.height(12.dp))
    }
}