// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.picker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.bibliotheke.wikisource.R
import app.bibliotheke.wikisource.catalog.WikiEntry
import app.bibliotheke.wikisource.ui.common.DownloadStatus
import app.bibliotheke.wikisource.ui.common.formatBytes
import app.bibliotheke.wikisource.ui.common.formatCount
import app.bibliotheke.wikisource.ui.common.wikiName

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickerScreen(onBack: (() -> Unit)?, onContinue: () -> Unit, vm: PickerViewModel = hiltViewModel()) {
    val ui by vm.ui.collectAsStateWithLifecycle()
    var confirmRemove by remember { mutableStateOf<WikiEntry?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(if (vm.firstRun) R.string.picker_welcome else R.string.picker_title)) },
                navigationIcon = {
                    if (onBack != null) IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.action_back))
                    }
                },
            )
        },
        bottomBar = {
            Surface(tonalElevation = 3.dp) {
                val bytes = ui.toDownload.sumOf { it.size }
                Button(
                    onClick = { vm.download(onContinue) },
                    enabled = ui.toDownload.isNotEmpty() || (ui.installed.isNotEmpty() || ui.downloads.isNotEmpty()),
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                ) {
                    Text(
                        if (ui.toDownload.isEmpty()) stringResource(R.string.picker_continue)
                        else stringResource(R.string.picker_download_button, formatBytes(bytes)),
                    )
                }
            }
        },
    ) { padding ->
        when {
            ui.loading && ui.manifest == null -> Box(padding) { CircularProgressIndicator() }
            ui.manifest == null -> Box(padding) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(stringResource(R.string.picker_manifest_error))
                    TextButton(onClick = vm::refresh) { Text(stringResource(R.string.action_retry)) }
                }
            }
            else -> LazyColumn(contentPadding = padding) {
                item {
                    Text(
                        stringResource(R.string.picker_intro),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(16.dp),
                    )
                }
                items(ui.wikis, key = { it.code }) { w ->
                    WikiRow(
                        entry = w,
                        selected = w.code in ui.selected,
                        installed = w.code in ui.installed,
                        update = ui.hasUpdate(w.code),
                        download = ui.downloads[w.code],
                        onToggle = { vm.toggle(w.code) },
                        onRemove = { confirmRemove = w },
                        onCancel = { vm.cancel(w.code) },
                    )
                }
            }
        }
    }

    confirmRemove?.let { w ->
        AlertDialog(
            onDismissRequest = { confirmRemove = null },
            title = { Text(stringResource(R.string.picker_remove_title)) },
            text = { Text(stringResource(R.string.picker_remove_text, wikiName(w))) },
            confirmButton = {
                TextButton(onClick = { vm.remove(w.code); confirmRemove = null }) { Text(stringResource(R.string.action_remove)) }
            },
            dismissButton = { TextButton(onClick = { confirmRemove = null }) { Text(stringResource(R.string.action_cancel)) } },
        )
    }
}

@Composable
private fun Box(padding: PaddingValues, content: @Composable () -> Unit) {
    androidx.compose.foundation.layout.Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) { content() }
}

@Composable
private fun WikiRow(
    entry: WikiEntry, selected: Boolean, installed: Boolean, update: Boolean,
    download: app.bibliotheke.wikisource.data.catalog.DownloadState?,
    onToggle: () -> Unit, onRemove: () -> Unit, onCancel: () -> Unit,
) {
    Column(Modifier.fillMaxWidth().clickable(enabled = !installed, onClick = onToggle).padding(horizontal = 8.dp, vertical = 4.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = selected || installed, onCheckedChange = { onToggle() }, enabled = !installed)
            Column(Modifier.weight(1f)) {
                Text(wikiName(entry), style = MaterialTheme.typography.titleMedium)
                Text(
                    listOf(
                        entry.name.takeIf { entry.code != "mul" && !it.equals(wikiName(entry), ignoreCase = true) },
                        entry.domain,
                        stringResource(R.string.picker_works, formatCount(entry.defaultViewCount)),
                        formatBytes(entry.size),
                    ).filterNotNull().joinToString(stringResource(R.string.list_separator)),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                if (installed) {
                    Text(
                        stringResource(if (update) R.string.picker_update_available else R.string.picker_installed),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
            when {
                download != null -> IconButton(onClick = onCancel) { Icon(Icons.Default.Close, stringResource(R.string.download_cancel)) }
                installed -> IconButton(onClick = onRemove) { Icon(Icons.Default.Delete, stringResource(R.string.action_remove)) }
            }
        }
        if (download != null) {
            Spacer(Modifier.height(4.dp))
            DownloadStatus(download, Modifier.padding(start = 48.dp, end = 16.dp))
        }
    }
}
