// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.bibliotheke.wikisource.R
import app.bibliotheke.wikisource.catalog.WikiLinks
import app.bibliotheke.wikisource.data.library.DownloadedWork
import app.bibliotheke.wikisource.data.library.Part
import app.bibliotheke.wikisource.ui.common.formatBytes

/**
 * Download controls of the detail screen (R4): the whole work or chosen parts, the
 * progress, then read, download the rest, or delete.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DownloadSection(
    lib: LibraryUi,
    canChoose: Boolean,
    choosing: Boolean,
    chosen: Int,
    onDownload: () -> Unit,
    onChoose: (Boolean) -> Unit,
    onDownloadChosen: () -> Unit,
    onRead: () -> Unit,
    onRetry: () -> Unit,
    onDelete: () -> Unit,
) {
    val w = lib.work
    var confirmDelete by remember { mutableStateOf(false) }
    Column(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
        val wanted = lib.parts.values.filter { it.kind != "toc" }
        val ready = wanted.count { it.state == Part.READY }
        when {
            w == null -> Unit
            lib.progress != null -> {
                Text(stringResource(R.string.download_progress, lib.progress.partsDone, lib.progress.partsTotal), style = MaterialTheme.typography.bodyMedium)
                LinearProgressIndicator(
                    progress = { if (lib.progress.partsTotal == 0) 0f else lib.progress.partsDone.toFloat() / lib.progress.partsTotal },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            w.state == DownloadedWork.QUEUED || w.state == DownloadedWork.DOWNLOADING ->
                Text(stringResource(R.string.download_queued), style = MaterialTheme.typography.bodyMedium)
            w.state == DownloadedWork.FAILED ->
                Text(stringResource(R.string.work_download_failed, w.error ?: ""), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.error)
            w.state == DownloadedWork.PARTIAL ->
                Text(stringResource(R.string.download_partial, ready, wanted.size, formatBytes(w.bytesOnDisk)), style = MaterialTheme.typography.bodyMedium)
            else -> Text(stringResource(R.string.download_ready, formatBytes(w.bytesOnDisk)), style = MaterialTheme.typography.bodyMedium)
        }
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            when {
                choosing -> {
                    Button(onClick = onDownloadChosen, enabled = chosen > 0) { Text(stringResource(R.string.download_selected, chosen)) }
                    TextButton(onClick = { onChoose(false) }) { Text(stringResource(R.string.action_cancel)) }
                }
                w == null -> {
                    Button(onClick = onDownload) { Text(stringResource(R.string.download_action)) }
                    if (canChoose) OutlinedButton(onClick = { onChoose(true) }) { Text(stringResource(R.string.download_choose)) }
                }
                else -> {
                    if (ready > 0) Button(onClick = onRead) { Text(stringResource(R.string.action_read)) }
                    if (w.state == DownloadedWork.FAILED) OutlinedButton(onClick = onRetry) { Text(stringResource(R.string.action_retry)) }
                    if (w.state == DownloadedWork.PARTIAL) {
                        OutlinedButton(onClick = onDownload) { Text(stringResource(R.string.download_rest)) }
                        if (canChoose) OutlinedButton(onClick = { onChoose(true) }) { Text(stringResource(R.string.download_choose)) }
                    }
                    TextButton(onClick = { confirmDelete = true }) { Text(stringResource(R.string.action_delete)) }
                }
            }
        }
    }
    if (confirmDelete && w != null) {
        AlertDialog(
            onDismissRequest = { confirmDelete = false },
            text = { Text(stringResource(R.string.delete_confirm, w.displayTitle)) },
            confirmButton = { TextButton(onClick = { confirmDelete = false; onDelete() }) { Text(stringResource(R.string.action_delete)) } },
            dismissButton = { TextButton(onClick = { confirmDelete = false }) { Text(stringResource(R.string.action_cancel)) } },
        )
    }
}

/** R11 for works of several pages: every wiki page the copy consists of, with its history. */
@Composable
fun PageSources(lib: LibraryUi, open: (String) -> Unit) {
    val w = lib.work ?: return
    val pages = lib.parts.values.sortedBy { it.index }.map { it.pageTitle }.distinct()
    var expanded by remember { mutableStateOf(false) }
    Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        w.licence?.let { l ->
            Text(
                stringResource(R.string.detail_licence_of, l), style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary, modifier = Modifier.clickable { w.licenceUrl?.let(open) },
            )
        }
        if (pages.size > 1) {
            TextButton(onClick = { expanded = !expanded }) { Text(stringResource(R.string.detail_sources, pages.size)) }
            if (expanded) {
                for (t in pages) {
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(
                            t, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable { open(WikiLinks.pageUrl(w.domain, t)) },
                        )
                        Text(
                            stringResource(R.string.detail_history), style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable { open(WikiLinks.historyUrl(w.domain, t)) },
                        )
                    }
                }
            }
        }
    }
}
