// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.bibliotheke.wikisource.BuildConfig
import app.bibliotheke.wikisource.R
import app.bibliotheke.wikisource.catalog.PoliteHttp
import app.bibliotheke.wikisource.ui.common.openInBrowser

/** R11 and PLAN.md 1.2: the long name, credits, non-affiliation notice and licence. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.settings_about)) },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.action_back)) } },
            )
        },
    ) { padding ->
        Column(
            Modifier.padding(padding).fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(stringResource(R.string.app_full_name), style = MaterialTheme.typography.headlineSmall)
            Text(stringResource(R.string.about_version, BuildConfig.VERSION_NAME), style = MaterialTheme.typography.bodySmall)
            Text(stringResource(R.string.about_description))
            Text(stringResource(R.string.about_credits))
            Text(stringResource(R.string.about_non_affiliation), style = MaterialTheme.typography.bodyMedium)
            Text(stringResource(R.string.about_licence))
            Text(
                stringResource(R.string.about_source_code) + ": " + PoliteHttp.REPO_URL,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { context.openInBrowser(PoliteHttp.REPO_URL) },
            )
        }
    }
}
