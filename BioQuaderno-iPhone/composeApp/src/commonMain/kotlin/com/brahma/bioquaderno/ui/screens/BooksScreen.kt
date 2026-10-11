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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brahma.bioquaderno.data.BookPlatform
import com.brahma.bioquaderno.data.BookPlatforms
import com.brahma.bioquaderno.data.BookStatus
import com.brahma.bioquaderno.data.DigitalBook
import com.brahma.bioquaderno.data.DigitalBookStore
import com.brahma.bioquaderno.platform.WebViewHost
import com.brahma.bioquaderno.platform.rememberPdfPicker
import com.brahma.bioquaderno.ui.components.GlassCard
import com.brahma.bioquaderno.ui.components.ScreenHeader
import com.brahma.bioquaderno.ui.components.SearchField
import com.brahma.bioquaderno.util.formatDate

/**
 * "I miei libri": importa il tuo libro digitale (PDF), l'app lo legge pagina
 * per pagina con OCR (offline), e il Prof consulta quelle pagine vere quando
 * gli fai una domanda. Qui sotto anche l'elenco delle piattaforme italiane
 * di libri digitali, apribili direttamente. Stesso codice su Android e iPhone.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksScreen(
    books: List<DigitalBook>,
    onImport: (String) -> Unit,
    onDelete: (DigitalBook) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var reader by remember { mutableStateOf<DigitalBook?>(null) }
    var deleting by remember { mutableStateOf<DigitalBook?>(null) }
    var openPlatform by remember { mutableStateOf<BookPlatform?>(null) }

    val pdfPicker = rememberPdfPicker { picked ->
        if (picked != null) onImport(picked)
    }

    val readyCount = books.count { it.status == BookStatus.READY }
    val processing = books.any { it.status == BookStatus.PROCESSING }

    Box(modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            ScreenHeader(
                title = "📚 I miei libri",
                subtitle = if (processing)
                    "Sto leggendo i PDF importati (resta sull'app, ci vuole un po')…"
                else
                    "$readyCount libri pronti · il Prof risponde usando le pagine vere del libro",
                actions = {
                    IconButton(onClick = { pdfPicker.launch() }) {
                        Icon(Icons.Filled.Add, contentDescription = "Importa un libro PDF")
                    }
                }
            )

            if (books.isEmpty()) {
                Box(Modifier.weight(1f).fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
                    GlassCard(Modifier.fillMaxWidth()) {
                        Column(
                            Modifier.fillMaxWidth().padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("📖", fontSize = 44.sp)
                            Text(
                                "Importa il tuo libro digitale",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                "Scegli il PDF del tuo libro (per esempio quello che scarichi da Scuolabook, " +
                                    "bSmart o myLIM): l'app lo legge pagina per pagina, anche offline, " +
                                    "e il Prof risponderà cercando proprio nelle pagine del libro vero.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                "⚠️ I libri protetti da DRM non si possono leggere da fuori: per quelli usa l'app della piattaforma.",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.error,
                                textAlign = TextAlign.Center
                            )
                            Button(
                                onClick = { pdfPicker.launch() },
                                modifier = Modifier.fillMaxWidth().height(48.dp)
                            ) {
                                Icon(Icons.Filled.Add, contentDescription = null, modifier = Modifier.size(20.dp))
                                Spacer(Modifier.width(8.dp))
                                Text("Importa un libro (PDF)")
                            }
                        }
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(books, key = { it.id }) { b ->
                        BookRow(
                            book = b,
                            onOpen = { reader = b },
                            onDelete = { deleting = b }
                        )
                    }
                    item(key = "platforms_hdr") {
                        Text(
                            "Oppure apri la tua libreria su una piattaforma",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 10.dp, start = 4.dp)
                        )
                    }
                    item(key = "platforms") {
                        PlatformChips(onOpen = { openPlatform = it })
                    }
                    item(key = "platforms_note") {
                        Text(
                            "Serve il login della tua scuola o della casa editrice, come nello studio " +
                                "da casa. I contenuti restano protetti dalle regole di ogni piattaforma.",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // Lettura del libro selezionato
        reader?.let { b ->
            BookReader(book = b, onBack = { reader = null })
        }

        // Piattaforma aperta nel WebView (Android) / WKWebView (iPhone)
        openPlatform?.let { p ->
            WebViewHost(
                url = p.url,
                onBack = { openPlatform = null },
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    deleting?.let { b ->
        AlertDialog(
            onDismissRequest = { deleting = null },
            title = { Text("Eliminare questo libro?") },
            text = { Text("Verranno rimossi il PDF e tutte le pagine lette. Per usare di nuovo il libro dovrai reimportarlo.") },
            confirmButton = {
                TextButton(onClick = {
                    onDelete(b)
                    deleting = null
                }) { Text("Sì, elimina") }
            },
            dismissButton = {
                TextButton(onClick = { deleting = null }) { Text("Annulla") }
            }
        )
    }
}

@Composable
private fun BookRow(
    book: DigitalBook,
    onOpen: () -> Unit,
    onDelete: () -> Unit
) {
    val canOpen = book.status == BookStatus.READY
    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(0.dp),
        onClick = if (canOpen) onOpen else null
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth().padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(
                            if (canOpen) MaterialTheme.colorScheme.primaryContainer
                            else MaterialTheme.colorScheme.surfaceVariant,
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(if (canOpen) "📕" else "⏳", fontSize = 20.sp)
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(
                        book.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        statusText(book),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Filled.Delete, contentDescription = "Elimina", tint = MaterialTheme.colorScheme.error)
                }
            }
            if (book.status == BookStatus.PROCESSING) {
                val total = book.pageCount.coerceAtLeast(1)
                LinearProgressIndicator(
                    progress = { book.processed.toFloat() / total },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
                )
                Text(
                    "Pagina ${book.processed}/${book.pageCount}…",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 12.dp, top = 4.dp, end = 12.dp, bottom = 10.dp)
                )
            }
        }
    }
}

@Composable
private fun PlatformChips(onOpen: (BookPlatform) -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(end = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(BookPlatforms.all) { p ->
            AssistChip(
                onClick = { onOpen(p) },
                label = { Text(p.name) },
                leadingIcon = {
                    Icon(
                        Icons.AutoMirrored.Filled.OpenInNew,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            )
        }
    }
}

private fun statusText(b: DigitalBook): String = when (b.status) {
    BookStatus.PROCESSING -> "Analisi in corso… tocca Elimina per fermarla"
    BookStatus.READY -> "${b.pageCount} pagine analizzate · tocca per leggere"
    BookStatus.FAILED -> "Non ho potuto leggerlo (DRM o PDF non leggibile) · elimina e riprova"
}

/** Lettore del libro: cerca nel testo di tutte le pagine. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BookReader(book: DigitalBook, onBack: () -> Unit) {
    val pages = remember(book.id) { DigitalBookStore.readPages(book) }
    var query by rememberSaveable { mutableStateOf("") }
    var expandedPage by remember { mutableStateOf<Int?>(null) }

    val filtered = remember(pages, query) {
        if (query.isBlank()) pages.mapIndexed { i, t -> (i + 1) to t }
        else pages.mapIndexedNotNull { i, t ->
            if (t.contains(query, ignoreCase = true)) (i + 1) to t else null
        }
    }

    Column(Modifier.fillMaxSize()) {
        ScreenHeader(
            title = book.title,
            subtitle = "${pages.size} pagine · cerca per saltare dritto al punto del libro",
            onBack = onBack
        )
        SearchField(
            value = query,
            onValueChange = { query = it },
            placeholder = "Cerca nel libro…",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )
        if (filtered.isEmpty()) {
            Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    if (query.isBlank()) "Nessuna pagina letta per questo libro." else "Nessun risultato per \"$query\"",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(24.dp)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filtered, key = { (p, _) -> p }) { (p, t) ->
                    GlassCard(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(14.dp),
                        onClick = { expandedPage = if (expandedPage == p) null else p }
                    ) {
                        Column {
                            Text(
                                "Pagina $p",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.height(4.dp))
                            if (expandedPage == p || t.length <= 320) {
                                Text(t, style = MaterialTheme.typography.bodyMedium)
                            } else {
                                Text(
                                    t.take(320) + "…",
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