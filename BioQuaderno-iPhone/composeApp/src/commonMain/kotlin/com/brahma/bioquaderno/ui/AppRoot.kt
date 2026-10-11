package com.brahma.bioquaderno.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Style
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.brahma.bioquaderno.data.BookStatus
import com.brahma.bioquaderno.data.DigitalBook
import com.brahma.bioquaderno.data.DigitalBookStore
import com.brahma.bioquaderno.data.Lesson
import com.brahma.bioquaderno.data.PhotoNote
import com.brahma.bioquaderno.data.PhotoNoteStore
import com.brahma.bioquaderno.data.Subject
import com.brahma.bioquaderno.data.Subjects
import com.brahma.bioquaderno.platform.Platform
import com.brahma.bioquaderno.ui.components.AppearanceDialog
import com.brahma.bioquaderno.ui.components.AuroraBackground
import com.brahma.bioquaderno.ui.lessons.PunnettLessonScreen
import com.brahma.bioquaderno.ui.screens.AssistantTab
import com.brahma.bioquaderno.ui.screens.BooksScreen
import com.brahma.bioquaderno.ui.screens.ChatItem
import com.brahma.bioquaderno.ui.screens.FlashcardsTab
import com.brahma.bioquaderno.ui.screens.GlossaryTab
import com.brahma.bioquaderno.ui.screens.KEY_GEMINI
import com.brahma.bioquaderno.ui.screens.KEY_MODEL
import com.brahma.bioquaderno.ui.screens.PhotoNotesScreen
import com.brahma.bioquaderno.ui.screens.QuizTab
import com.brahma.bioquaderno.ui.screens.StudyTab
import com.brahma.bioquaderno.ui.screens.SubjectManagementScreen
import com.brahma.bioquaderno.ui.screens.introMessage
import com.brahma.bioquaderno.ui.theme.AppSettings
import com.brahma.bioquaderno.ui.theme.AppSettingsStore
import com.brahma.bioquaderno.ui.theme.BioQuadernoTheme
import com.brahma.bioquaderno.ui.theme.LocalAppSettings
import com.brahma.bioquaderno.ui.theme.palettes
import kotlinx.coroutines.launch

private data class Dest(val label: String, val icon: ImageVector)

private val destinations = listOf(
    Dest("Studio", Icons.Filled.Book),
    Dest("Glossario", Icons.AutoMirrored.Filled.List),
    Dest("Flashcard", Icons.Filled.Style),
    Dest("Quiz", Icons.Filled.Quiz),
    Dest("Prof", Icons.AutoMirrored.Filled.Chat)
)

/** Sezioni che si aprono sopra le schede. Una sola alla volta: mai sovrapposte. */
private enum class Overlay { Subjects, PhotoNotes, Books, Appearance }

/**
 * Radice dell'app: stessa identica UI su Android e iPhone.
 * Tutto ciò che cambia tra le piattaforme è dietro [Platform].
 */
@Composable
fun AppRoot() {
    val prefs = remember { Platform.prefs() }

    // Impostazioni di aspetto: palette, tema chiaro/scuro, liquid glass, animazioni
    var appSettings by remember { mutableStateOf(AppSettingsStore.load(prefs)) }
    fun updateSettings(s: AppSettings) {
        appSettings = s
        AppSettingsStore.save(prefs, s)
    }

    val dark = when (appSettings.darkMode) {
        "dark" -> true
        "light" -> false
        else -> isSystemInDarkTheme()
    }
    val palette = palettes[appSettings.paletteIndex.coerceIn(0, palettes.lastIndex)]

    // Durata delle transizioni in base all'impostazione "velocità animazioni"
    val animMs = remember(appSettings.animationSpeed) {
        when (appSettings.animationSpeed) {
            "veloce" -> 130
            "lenta" -> 420
            else -> 260
        }
    }
    val animMsOut = (animMs * 0.6).toInt()

    // Tipo di transizione tra le schede
    val tabEnter: EnterTransition = when (appSettings.tabTransition) {
        "scorrimento" -> slideInHorizontally(tween(animMs)) { it } + fadeIn(tween(animMs))
        "nessuna" -> EnterTransition.None
        else -> fadeIn(tween(animMs))
    }
    val tabExit: ExitTransition = when (appSettings.tabTransition) {
        "scorrimento" -> slideOutHorizontally(tween(animMsOut)) { -it / 3 } + fadeOut(tween(animMsOut))
        "nessuna" -> ExitTransition.None
        else -> fadeOut(tween(animMsOut))
    }

    // Scheda iniziale scelta dall'utente
    val startIndex = when (appSettings.startTab) {
        "glossario" -> 1
        "flashcard" -> 2
        "quiz" -> 3
        "prof" -> 4
        else -> 0
    }
    var selected by rememberSaveable { mutableIntStateOf(startIndex) }
    // Una sola sezione aperta alla volta (foto, libri, materie, aspetto):
    // così "+" e "libri" non possono più sovrapporsi.
    var overlay by rememberSaveable { mutableStateOf<Overlay?>(null) }
    var activeLesson by remember { mutableStateOf<Lesson?>(null) }

    // -----------------------------------------------------------------
    // Materie attive e materia corrente (persistite nelle preferenze)
    // -----------------------------------------------------------------
    val enabledIds = remember {
        mutableStateOf(
            prefs.get("subjects_on")
                ?.split(",")
                ?.mapNotNull { it.trim().takeIf(String::isNotEmpty) }
                ?.toSet()
                ?.takeIf { it.isNotEmpty() }
                ?: Subjects.all.map { it.id }.toSet()
        )
    }
    val currentSubjectId = remember {
        mutableStateOf(prefs.get("current_subject")?.takeIf { it.isNotEmpty() })
    }

    fun persistSubjects() {
        prefs.put("subjects_on", enabledIds.value.sorted().joinToString(","))
        prefs.put("current_subject", currentSubjectId.value ?: "")
    }

    fun toggleSubject(id: String) {
        val cur = enabledIds.value.toMutableSet()
        if (!cur.remove(id)) cur.add(id)
        enabledIds.value = cur
        currentSubjectId.value = currentSubjectId.value?.takeIf { it in cur }
        persistSubjects()
    }

    fun setAllSubjects(enabled: Boolean) {
        enabledIds.value = if (enabled) Subjects.all.map { it.id }.toSet() else emptySet()
        currentSubjectId.value = null
        persistSubjects()
    }

    fun setSubject(id: String?) {
        currentSubjectId.value = id?.takeIf { it in enabledIds.value }
        persistSubjects()
    }

    val enabledSubjects = remember(enabledIds.value) {
        Subjects.enabled(Subjects.all, enabledIds.value)
    }
    val activeSubjectId = remember(enabledIds.value, currentSubjectId.value) {
        currentSubjectId.value?.takeIf { it in enabledIds.value }
    }
    val allChapters = remember { Subjects.all.flatMap { it.chapters } }

    // -----------------------------------------------------------------
    // Appunti dalle foto (OCR dell'app: ML Kit su Android, Vision su iPhone)
    // -----------------------------------------------------------------
    val photoNotes = remember {
        mutableStateListOf<PhotoNote>().apply { addAll(PhotoNoteStore.load(prefs)) }
    }
    fun addPhotoNote(n: PhotoNote) {
        photoNotes.add(0, n)
        PhotoNoteStore.save(prefs, photoNotes.toList())
    }
    fun updatePhotoNote(n: PhotoNote) {
        val i = photoNotes.indexOfFirst { it.id == n.id }
        if (i >= 0) {
            photoNotes[i] = n
            PhotoNoteStore.save(prefs, photoNotes.toList())
        }
    }
    fun deletePhotoNote(n: PhotoNote) {
        PhotoNoteStore.deleteFile(n)
        photoNotes.removeAll { it.id == n.id }
        PhotoNoteStore.save(prefs, photoNotes.toList())
    }

    // -----------------------------------------------------------------
    // Libri digitali: PDF importati e letti pagina per pagina (OCR offline)
    // -----------------------------------------------------------------
    val books = remember {
        mutableStateListOf<DigitalBook>().apply { addAll(DigitalBookStore.load(prefs)) }
    }
    val bookScope = rememberCoroutineScope()
    fun saveBooks() = DigitalBookStore.save(prefs, books.toList())
    fun updateBook(b: DigitalBook) {
        val i = books.indexOfFirst { it.id == b.id }
        if (i >= 0) books[i] = b
        saveBooks()
    }
    fun importBook(picked: String) {
        bookScope.launch {
            val preview = picked.substringAfterLast('/').substringAfterLast(':')
                .takeIf { it.isNotBlank() && it.contains(".") }
                ?: "Libro digitale"
            val book = DigitalBookStore.importPdf(picked)
                ?: DigitalBook(
                    id = "book${System.currentTimeMillis()}",
                    title = preview.replace('_', ' ').trim().ifBlank { "Libro digitale" },
                    fileName = "libro.pdf",
                    pageCount = 0,
                    processed = 0,
                    status = BookStatus.FAILED
                )
            books.add(0, book)
            saveBooks()
            if (book.status == BookStatus.PROCESSING) {
                try {
                    val total = Platform.pdfExtract(
                        pdfPath = DigitalBookStore.pdfPath(book),
                        outDir = DigitalBookStore.pagesDir(book)
                    ) { done, tot ->
                        updateBook(book.copy(processed = done, pageCount = tot))
                    }
                    updateBook(book.copy(status = BookStatus.READY, pageCount = total, processed = total))
                } catch (_: Exception) {
                    updateBook(book.copy(status = BookStatus.FAILED))
                }
            }
        }
    }
    fun deleteBook(b: DigitalBook) {
        DigitalBookStore.delete(b)
        books.removeAll { it.id == b.id }
        saveBooks()
    }

    // Pagine del primo libro pronto: il Prof le consulta per rispondere
    val activeBookPages = remember(books.toList()) {
        books.firstOrNull { it.status == BookStatus.READY }
            ?.let { DigitalBookStore.readPages(it) }
            ?: emptyList()
    }

    // -----------------------------------------------------------------
    // Stato dell'assistente: tenuto qui per sopravvivere al cambio di scheda
    // -----------------------------------------------------------------
    val studied = remember {
        mutableStateListOf<Int>().apply {
            val s = prefs.get("studied") ?: ""
            if (s.isNotEmpty()) s.split(",").mapNotNull { it.toIntOrNull() }.forEach { add(it) }
        }
    }
    val assistantMessages = remember { mutableStateOf<List<ChatItem>>(listOf(introMessage)) }
    val geminiKey = remember { mutableStateOf(prefs.get(KEY_GEMINI) ?: "") }
    val geminiModel = remember {
        mutableStateOf(prefs.get(KEY_MODEL) ?: "gemini-2.5-flash")
    }

    fun toggleStudied(id: Int) {
        if (id in studied) studied.remove(id) else studied.add(id)
        prefs.put("studied", studied.joinToString(","))
    }

    BioQuadernoTheme(palette = palette, darkTheme = dark, settings = appSettings) {
        CompositionLocalProvider(LocalAppSettings provides appSettings) {
            Box(Modifier.fillMaxSize()) {
                AuroraBackground(palette = palette, dark = dark)

                val lesson = activeLesson
                if (lesson != null) {
                    PunnettLessonScreen(
                        lesson = lesson,
                        onBack = { activeLesson = null }
                    )
                } else {
                    Scaffold(
                        containerColor = Color.Transparent,
                        bottomBar = {
                            NavigationBar(
                                containerColor = if (appSettings.glass)
                                    MaterialTheme.colorScheme.surface.copy(alpha = 0.55f)
                                else MaterialTheme.colorScheme.surface,
                                tonalElevation = 0.dp
                            ) {
                                val showIcon = appSettings.navLabels != "testo"
                                val showLabel = appSettings.navLabels != "icone"
                                val iconSize = if (appSettings.largeTouchTargets) 30.dp else 24.dp
                                destinations.forEachIndexed { i, d ->
                                    NavigationBarItem(
                                        selected = selected == i,
                                        onClick = { selected = i },
                                        icon = {
                                            if (showIcon) {
                                                Icon(
                                                    d.icon,
                                                    contentDescription = d.label,
                                                    modifier = Modifier.size(iconSize)
                                                )
                                            }
                                        },
                                        label = if (showLabel) {
                                            { Text(d.label) }
                                        } else null
                                    )
                                }
                            }
                        }
                    ) { inner ->
                        AnimatedContent(
                            targetState = selected,
                            transitionSpec = { tabEnter togetherWith tabExit },
                            label = "tab"
                        ) { sel ->
                            Box(
                                Modifier
                                    .padding(inner)
                                    .fillMaxSize()
                            ) {
                                when (sel) {
                                    0 -> StudyTab(
                                        subjects = enabledSubjects,
                                        currentSubjectId = activeSubjectId,
                                        onSubject = ::setSubject,
                                        allChapters = allChapters,
                                        studiedIds = studied,
                                        onToggleStudied = ::toggleStudied,
                                        onSettings = { overlay = Overlay.Appearance },
                                        onAddPhotos = { overlay = Overlay.PhotoNotes },
                                        onManageSubjects = { overlay = Overlay.Subjects },
                                        onManageBooks = { overlay = Overlay.Books }
                                    )

                                    1 -> GlossaryTab(
                                        subjects = enabledSubjects,
                                        currentSubjectId = activeSubjectId,
                                        onSubject = ::setSubject
                                    )

                                    2 -> FlashcardsTab(
                                        subjects = enabledSubjects,
                                        currentSubjectId = activeSubjectId,
                                        onSubject = ::setSubject
                                    )

                                    3 -> QuizTab(
                                        prefs = prefs,
                                        subjects = enabledSubjects,
                                        currentSubjectId = activeSubjectId,
                                        onSubject = ::setSubject
                                    )

                                    else -> AssistantTab(
                                        prefs = prefs,
                                        messagesState = assistantMessages,
                                        keyState = geminiKey,
                                        modelState = geminiModel,
                                        allSubjects = enabledSubjects,
                                        currentSubject = Subjects.byId(activeSubjectId),
                                        onSubject = ::setSubject,
                                        onOpenLesson = { overlay = null; activeLesson = it },
                                        bookPages = activeBookPages
                                    )
                                }
                            }
                        }
                    }
                }

                // Sezioni aperte da "Studio" (+ foto, libri, materie, aspetto): una sola
                // alla volta, su un fondo solido. Niente più sovrapposizioni.
                AnimatedContent(
                    targetState = overlay,
                    transitionSpec = {
                        fadeIn(tween(animMs)) togetherWith fadeOut(tween((animMs * 0.6).toInt()))
                    },
                    label = "overlay"
                ) { ov ->
                    when (ov) {
                        Overlay.Subjects -> OverlayHost {
                            SubjectManagementScreen(
                                subjects = Subjects.all,
                                enabledIds = enabledIds.value,
                                onToggle = ::toggleSubject,
                                onSetAll = ::setAllSubjects,
                                onBack = { overlay = null }
                            )
                        }
                        Overlay.PhotoNotes -> OverlayHost {
                            PhotoNotesScreen(
                                notes = photoNotes,
                                onAdd = ::addPhotoNote,
                                onUpdate = ::updatePhotoNote,
                                onDelete = ::deletePhotoNote,
                                onBack = { overlay = null }
                            )
                        }
                        Overlay.Books -> OverlayHost {
                            BooksScreen(
                                books = books,
                                onImport = ::importBook,
                                onDelete = ::deleteBook,
                                onBack = { overlay = null }
                            )
                        }
                        Overlay.Appearance -> OverlayHost(transparent = true) {
                            AppearanceDialog(
                                settings = appSettings,
                                onSettings = ::updateSettings,
                                onDismiss = { overlay = null }
                            )
                        }
                        null -> {}
                    }
                }
            }
        }
    }
}

/** Contenitore a schermo pieno per le sezioni sovrapposte, con sfondo solido. */
@Composable
private fun OverlayHost(
    transparent: Boolean = false,
    content: @Composable () -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .then(
                if (transparent) Modifier
                else Modifier.background(MaterialTheme.colorScheme.background)
            )
    ) { content() }
}