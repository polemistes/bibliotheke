// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.library

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import app.bibliotheke.wikisource.R
import app.bibliotheke.wikisource.data.library.DownloadedWork
import app.bibliotheke.wikisource.data.library.LibraryRepository
import app.bibliotheke.wikisource.data.library.WorkProgress
import app.bibliotheke.wikisource.ui.common.formatBytes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

data class LibraryScreenUi(
    val works: List<DownloadedWork> = emptyList(),
    val progress: Map<String, WorkProgress> = emptyMap(),
    val totalBytes: Long = 0,
)

@HiltViewModel
class LibraryViewModel @Inject constructor(private val library: LibraryRepository) : ViewModel() {
    val ui: StateFlow<LibraryScreenUi> = combine(library.works, library.progress, library.totalBytes) { w, p, b ->
        LibraryScreenUi(w, p, b)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), LibraryScreenUi())

    fun delete(id: String) = viewModelScope.launch { library.delete(id) }
    fun retry(id: String) = library.retry(id)
}

/** Downloaded works and the download queue, with their size on the device (R4, PLAN.md 6). */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    onBack: () -> Unit,
    onOpen: (DownloadedWork) -> Unit,
    vm: LibraryViewModel = hiltViewModel(),
) {
    val ui by vm.ui.collectAsStateWithLifecycle()
    var deleting by remember { mutableStateOf<DownloadedWork?>(null) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.library_title)) },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.action_back)) } },
            )
        },
    ) { padding ->
        if (ui.works.isEmpty()) {
            Text(stringResource(R.string.library_empty), Modifier.padding(padding).padding(16.dp))
            return@Scaffold
        }
        LazyColumn(Modifier.padding(padding).fillMaxSize()) {
            item {
                Text(
                    stringResource(R.string.library_storage, formatBytes(ui.totalBytes)),
                    style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(16.dp),
                )
            }
            items(ui.works, key = { it.id }) { w ->
                WorkRow(w, ui.progress[w.id], onOpen = { onOpen(w) }, onRetry = { vm.retry(w.id) }, onDelete = { deleting = w })
                HorizontalDivider()
            }
        }
    }
    deleting?.let { w ->
        AlertDialog(
            onDismissRequest = { deleting = null },
            text = { Text(stringResource(R.string.delete_confirm, w.displayTitle)) },
            confirmButton = { TextButton(onClick = { deleting = null; vm.delete(w.id) }) { Text(stringResource(R.string.action_delete)) } },
            dismissButton = { TextButton(onClick = { deleting = null }) { Text(stringResource(R.string.action_cancel)) } },
        )
    }
}

@Composable
private fun WorkRow(w: DownloadedWork, progress: WorkProgress?, onOpen: () -> Unit, onRetry: () -> Unit, onDelete: () -> Unit) {
    Row(Modifier.fillMaxWidth().clickable(onClick = onOpen).padding(start = 16.dp, top = 8.dp, bottom = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f)) {
            Text(w.displayTitle, style = MaterialTheme.typography.titleMedium, maxLines = 2, overflow = TextOverflow.Ellipsis)
            val authors = runCatching { Json.decodeFromString<List<String>>(w.authors) }.getOrDefault(emptyList())
            if (authors.isNotEmpty()) Text(authors.joinToString(", "), style = MaterialTheme.typography.bodyMedium)
            when {
                progress != null -> {
                    Text(stringResource(R.string.download_progress, progress.partsDone, progress.partsTotal), style = MaterialTheme.typography.bodySmall)
                    LinearProgressIndicator(
                        progress = { if (progress.partsTotal == 0) 0f else progress.partsDone.toFloat() / progress.partsTotal },
                        modifier = Modifier.fillMaxWidth().padding(end = 16.dp, top = 4.dp),
                    )
                }
                w.state == DownloadedWork.QUEUED || w.state == DownloadedWork.DOWNLOADING ->
                    Text(stringResource(R.string.download_queued), style = MaterialTheme.typography.bodySmall)
                w.state == DownloadedWork.FAILED -> {
                    Text(stringResource(R.string.work_download_failed, w.error ?: ""), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
                    TextButton(onClick = onRetry) { Text(stringResource(R.string.action_retry)) }
                }
                else -> Text(stringResource(R.string.download_ready, formatBytes(w.bytesOnDisk)), style = MaterialTheme.typography.bodySmall)
            }
        }
        IconButton(onClick = onDelete) { Icon(Icons.Default.Delete, stringResource(R.string.action_delete)) }
    }
}
