// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.bibliotheke.wikisource.R
import app.bibliotheke.wikisource.data.catalog.DownloadState

@Composable
fun DownloadStatus(state: DownloadState, modifier: Modifier = Modifier) {
    Column(modifier) {
        when (state) {
            is DownloadState.Running -> {
                val f = if (state.total > 0) state.done.toFloat() / state.total else 0f
                Text(stringResource(R.string.download_running, (f * 100).toInt()), style = MaterialTheme.typography.bodySmall)
                LinearProgressIndicator(progress = { f }, modifier = Modifier.fillMaxWidth())
            }
            DownloadState.Queued -> {
                Text(stringResource(R.string.download_queued), style = MaterialTheme.typography.bodySmall)
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            is DownloadState.Failed -> Text(
                stringResource(R.string.download_failed),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
            )
        }
    }
}
