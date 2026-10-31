// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import app.bibliotheke.wikisource.R
import app.bibliotheke.wikisource.catalog.WikiLinks
import app.bibliotheke.wikisource.data.catalog.LinkResolver
import app.bibliotheke.wikisource.data.catalog.LinkResult
import app.bibliotheke.wikisource.ui.common.openInBrowser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LinkViewModel @Inject constructor(val resolver: LinkResolver) : ViewModel()

/** Open by URL: goes on to the work when the catalog has it, else says why not. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LinkScreen(url: String, onBack: () -> Unit, onFound: (wiki: String, id: Long, ord: Int) -> Unit, vm: LinkViewModel = hiltViewModel()) {
    val result by produceState<LinkResult?>(null, url) { value = vm.resolver.resolve(url) }
    val r = result
    LaunchedEffect(r) { if (r is LinkResult.Found) onFound(r.wiki, r.target.workId, r.target.ord ?: -1) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.link_title)) },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.action_back)) } },
            )
        },
    ) { padding ->
        Box(Modifier.padding(padding).fillMaxSize().padding(24.dp), contentAlignment = Alignment.Center) {
            when (r) {
                null, is LinkResult.Found -> CircularProgressIndicator()
                LinkResult.NotWikisource -> Text(stringResource(R.string.link_not_wikisource))
                is LinkResult.NotInCatalog -> Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(r.title, style = MaterialTheme.typography.titleLarge)
                    Text(
                        if (r.wiki == null) stringResource(R.string.link_no_catalog, r.domain)
                        else stringResource(R.string.link_not_found, r.domain),
                    )
                    Button(onClick = { context.openInBrowser(WikiLinks.pageUrl(r.domain, r.title)) }) {
                        Text(stringResource(R.string.action_open_on_wikisource))
                    }
                }
            }
        }
    }
}
