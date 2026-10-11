package com.brahma.bioquaderno.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.brahma.bioquaderno.data.Subject

@Composable
fun ScreenHeader(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    onBack: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onBack != null) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Indietro")
            }
        }
        Column(Modifier.weight(1f)) {
            Text(
                title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            if (subtitle != null) {
                Text(
                    subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        actions()
    }
}

@Composable
fun SearchField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text(placeholder) },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        trailingIcon = {
            if (value.isNotEmpty()) {
                IconButton(onClick = { onValueChange("") }) {
                    Icon(Icons.Filled.Close, contentDescription = "Cancella")
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(14.dp)
    )
}

/**
 * Fila di "pillole" per scegliere la materia visibile: "Tutte" più una pillola
 * per ogni materia attiva. [null] significa "Tutte".
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectPillsRow(
    subjects: List<Subject>,
    currentSubjectId: String?,
    onSelect: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item(key = "__all") {
            FilterChip(
                selected = currentSubjectId == null,
                onClick = { onSelect(null) },
                label = { Text("🗂️ Tutte") }
            )
        }
        items(subjects, key = { it.id }) { s ->
            FilterChip(
                selected = currentSubjectId == s.id,
                onClick = { onSelect(s.id) },
                label = { Text("${s.emoji} ${subjectLabel(s.name)}") }
            )
        }
    }
}

/** Nome scorciato per le pillole: se troppo lungo, tronca alle prime parole. */
private fun subjectLabel(name: String): String {
    if (name.length <= 26) return name
    val sb = StringBuilder()
    name.split(" ").forEach { w ->
        val next = if (sb.isEmpty()) w else "$sb $w"
        if (next.length > 26) return@forEach
        sb.setLength(0)
        sb.append(next)
    }
    return "${sb}…"
}