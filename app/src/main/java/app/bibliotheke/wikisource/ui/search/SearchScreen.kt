// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.bibliotheke.wikisource.R
import app.bibliotheke.wikisource.catalog.TextHit
import app.bibliotheke.wikisource.catalog.WikiLinks
import app.bibliotheke.wikisource.ui.browse.WorkRow
import app.bibliotheke.wikisource.ui.common.formatCount
import app.bibliotheke.wikisource.ui.common.qualityBadge
import app.bibliotheke.wikisource.ui.common.wikiName

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onBack: () -> Unit,
    onOpenWork: (wiki: String, id: Long, ord: Int) -> Unit,
    onOpenLink: (String) -> Unit,
    vm: SearchViewModel = hiltViewModel(),
) {
    val query by vm.query.collectAsStateWithLifecycle()
    val results by vm.results.collectAsStateWithLifecycle()
    val online by vm.online.collectAsStateWithLifecycle()
    val installed by vm.installed.collectAsStateWithLifecycle()
    val focus = remember { FocusRequester() }
    LaunchedEffect(Unit) { focus.requestFocus() }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.action_back)) } },
                title = {
                    TextField(
                        value = query,
                        onValueChange = vm::setQuery,
                        placeholder = { Text(stringResource(R.string.search_hint), maxLines = 1, overflow = TextOverflow.Ellipsis) },
                        singleLine = true,
                        trailingIcon = {
                            if (query.isNotEmpty()) IconButton(onClick = { vm.setQuery("") }) { Icon(Icons.Default.Clear, stringResource(R.string.action_clear)) }
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(onSearch = { WikiLinks.parse(query)?.let { onOpenLink(query) } }),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent, unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent,
                        ),
                        modifier = Modifier.fillMaxWidth().focusRequester(focus),
                    )
                },
            )
        },
    ) { padding ->
        LazyColumn(Modifier.padding(padding).fillMaxSize()) {
            if (WikiLinks.parse(query) != null) {
                item {
                    Row(
                        Modifier.fillMaxWidth().clickable { onOpenLink(query) }.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(Icons.Default.Search, null)
                        Text(stringResource(R.string.search_open_link), Modifier.padding(start = 16.dp), style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
            val all = results
            if (all != null) {
                val multi = all.size > 1
                if (all.all { it.results.isEmpty }) item { Text(stringResource(R.string.search_no_matches), Modifier.padding(16.dp)) }
                all.forEach { r -> wikiSection(r, multi, onOpenWork, vm::toggleHidden) }
            }
            if (query.isNotBlank() && WikiLinks.parse(query) == null) {
                item { HorizontalDivider(Modifier.padding(vertical = 8.dp)) }
                items(installed.values.toList(), key = { "online-" + it.code }) { w ->
                    OutlinedButton(onClick = { vm.searchOnline(w) }, modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp)) {
                        Text(stringResource(R.string.search_online_on, w.domain))
                    }
                }
                online?.let { o -> onlineSection(o, onOpenWork, onOpenLink) }
            }
        }
    }
}

private fun LazyListScope.wikiSection(
    r: WikiResults, multi: Boolean,
    onOpenWork: (String, Long, Int) -> Unit, onToggleHidden: (String) -> Unit,
) {
    val res = r.results
    if (res.isEmpty) return
    val code = r.wiki.code
    if (multi) item(key = "h-$code") {
        Text(wikiName(r.wiki), style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp))
    }
    if (res.works.isNotEmpty()) {
        item(key = "hw-$code") { SectionHeader(stringResource(R.string.search_works)) }
        items(res.works, key = { "w-$code-${it.id}" }) { w ->
            WorkRow(w, onClick = { onOpenWork(code, w.id, -1) }, modifier = if (w.visible) Modifier else Modifier.dimmed())
        }
        if (res.totalWorks > res.works.count { it.visible } && !r.expanded) item(key = "mw-$code") {
            Note(stringResource(R.string.search_more_works, formatCount(res.totalWorks), res.works.size))
        }
    }
    if (res.texts.isNotEmpty()) {
        item(key = "ht-$code") { SectionHeader(stringResource(R.string.search_texts)) }
        items(res.texts, key = { "t-$code-${it.partId}" }) { t -> TextRow(t) { onOpenWork(code, t.workId, t.ord) } }
        if (res.totalTexts > res.texts.count { it.visible } && !r.expanded) item(key = "mt-$code") {
            Note(stringResource(R.string.search_more_texts, formatCount(res.totalTexts), res.texts.size))
        }
    }
    if (res.hidden > 0) item(key = "hid-$code") {
        // Nothing disappears silently (PLAN.md 5.4).
        Row(
            Modifier.fillMaxWidth().clickable { onToggleHidden(code) }.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                pluralStringResource(if (r.expanded) R.plurals.search_hidden_shown else R.plurals.search_hidden, res.hidden, res.hidden),
                style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f),
            )
            TextButton(onClick = { onToggleHidden(code) }) {
                Text(stringResource(if (r.expanded) R.string.search_hidden_hide else R.string.search_hidden_show))
            }
        }
    }
}

/** Rows the quality filter would hide, shown after "Show". */
private fun Modifier.dimmed() = this.alpha(0.6f)

@Composable
private fun SectionHeader(text: String) {
    Text(text, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(start = 16.dp, top = 12.dp, bottom = 4.dp))
}

@Composable
private fun Note(text: String) {
    Text(text, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp))
}

@Composable
private fun TextRow(t: TextHit, onClick: () -> Unit) {
    val sep = stringResource(R.string.list_separator)
    Column(
        Modifier.fillMaxWidth().clickable(onClick = onClick).padding(horizontal = 16.dp, vertical = 10.dp)
            .then(if (t.visible) Modifier else Modifier.dimmed()),
    ) {
        Text(t.title, style = MaterialTheme.typography.titleMedium, maxLines = 2, overflow = TextOverflow.Ellipsis)
        Text(
            listOfNotNull(stringResource(R.string.search_text_in, t.workTitle), t.authors).joinToString(sep),
            style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1, overflow = TextOverflow.Ellipsis,
        )
        Text(
            listOfNotNull(qualityBadge(t.quality), if (!t.complete) stringResource(R.string.incomplete) else null).joinToString(sep),
            style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.secondary,
        )
    }
}

private fun LazyListScope.onlineSection(o: OnlineState, onOpenWork: (String, Long, Int) -> Unit, onOpenLink: (String) -> Unit) {
    item(key = "oh") { SectionHeader(stringResource(R.string.search_online_title, o.wiki.domain)) }
    when {
        o.loading -> item(key = "ol") { CircularProgressIndicator(Modifier.padding(16.dp)) }
        o.error -> item(key = "oe") { Note(stringResource(R.string.search_online_error)) }
        o.hits.isEmpty() -> item(key = "on") { Note(stringResource(R.string.search_online_none)) }
        else -> items(o.hits, key = { "o-" + it.title }) { h ->
            val t = h.target
            Column(
                Modifier.fillMaxWidth()
                    .clickable {
                        if (t != null) onOpenWork(o.wiki.code, t.workId, t.ord ?: -1)
                        else onOpenLink(WikiLinks.pageUrl(o.wiki.domain, h.title))
                    }
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .then(if (t != null) Modifier else Modifier.dimmed()),
            ) {
                Text(h.title, style = MaterialTheme.typography.titleMedium)
                if (h.snippet.isNotEmpty()) {
                    Text(h.snippet, style = MaterialTheme.typography.bodySmall, maxLines = 2, overflow = TextOverflow.Ellipsis)
                }
                if (t == null) {
                    Text(stringResource(R.string.search_online_not_in_catalog), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.secondary)
                }
            }
        }
    }
}
