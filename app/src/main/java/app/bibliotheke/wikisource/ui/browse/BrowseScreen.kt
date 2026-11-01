// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.browse

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.bibliotheke.wikisource.R
import app.bibliotheke.wikisource.catalog.Quality
import app.bibliotheke.wikisource.catalog.SortOrder
import app.bibliotheke.wikisource.catalog.WorkSummary
import app.bibliotheke.wikisource.ui.common.DownloadStatus
import app.bibliotheke.wikisource.ui.common.formatCount
import app.bibliotheke.wikisource.ui.common.qualityBadge
import app.bibliotheke.wikisource.ui.common.typeName
import app.bibliotheke.wikisource.ui.common.wikiName

enum class FacetSheet { GENRE, PERIOD, AUTHOR, TYPE, LANGUAGE }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowseScreen(
    onOpenWork: (wiki: String, id: Long) -> Unit,
    onSearch: () -> Unit,
    onSettings: () -> Unit,
    onLibrary: () -> Unit,
    onManageCatalogs: () -> Unit,
    vm: BrowseViewModel = hiltViewModel(),
) {
    val ui by vm.ui.collectAsStateWithLifecycle()
    val list by vm.list.collectAsStateWithLifecycle()
    var sheet by rememberSaveable { mutableStateOf<FacetSheet?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { WikiSelector(ui, vm::selectWiki, onManageCatalogs) },
                actions = {
                    IconButton(onClick = onSearch) { Icon(Icons.Default.Search, stringResource(R.string.action_search)) }
                    IconButton(onClick = onLibrary) { Icon(painterResource(R.drawable.ic_library), stringResource(R.string.library_title)) }
                    IconButton(onClick = onSettings) { Icon(Icons.Default.Settings, stringResource(R.string.action_settings)) }
                },
            )
        },
    ) { padding ->
        val active = ui.active
        when {
            !ui.ready -> Unit
            active == null -> NoCatalog(padding, ui, onManageCatalogs)
            else -> Column(Modifier.padding(padding).fillMaxSize()) {
                ui.downloads.forEach { (code, state) ->
                    val entry = ui.installed.firstOrNull { it.code == code }
                    if (entry == null || code == active.code) DownloadStatus(state, Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp))
                }
                FilterRow(ui, vm, onOpenSheet = { sheet = it })
                HorizontalDivider()
                CountAndSort(list.count, ui.sort, vm::setSort)
                WorkList(list, onOpen = { onOpenWork(active.code, it.id) }, onLoadMore = vm::loadMore)
            }
        }
    }

    sheet?.let { FacetSheetHost(it, ui, vm, onDismiss = { sheet = null }) }
}

@Composable
private fun WikiSelector(ui: BrowseUi, onSelect: (String) -> Unit, onManage: () -> Unit) {
    var open by remember { mutableStateOf(false) }
    Box {
        Row(
            Modifier.clickable { open = true }.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(ui.active?.let { wikiName(it) } ?: stringResource(R.string.app_name), maxLines = 1, overflow = TextOverflow.Ellipsis)
            Icon(Icons.Default.ArrowDropDown, stringResource(R.string.browse_choose_wiki))
        }
        DropdownMenu(expanded = open, onDismissRequest = { open = false }) {
            ui.installed.forEach { w ->
                DropdownMenuItem(
                    text = { Text("${wikiName(w)} (${w.code})") },
                    onClick = { open = false; onSelect(w.code) },
                    leadingIcon = { if (w.code == ui.active?.code) Icon(Icons.Default.Check, null) },
                )
            }
            HorizontalDivider()
            DropdownMenuItem(text = { Text(stringResource(R.string.browse_manage_catalogs)) }, onClick = { open = false; onManage() })
        }
    }
}

@Composable
private fun NoCatalog(padding: PaddingValues, ui: BrowseUi, onManage: () -> Unit) {
    Column(
        Modifier.padding(padding).fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (ui.downloads.isNotEmpty()) {
            Text(stringResource(R.string.browse_catalog_downloading))
            ui.downloads.forEach { (code, state) ->
                Text(code, style = MaterialTheme.typography.labelLarge)
                DownloadStatus(state, Modifier.fillMaxWidth())
            }
        } else {
            Text(stringResource(R.string.browse_no_catalog))
            Button(onClick = onManage) { Text(stringResource(R.string.browse_get_catalogs)) }
        }
    }
}

@Composable
private fun FilterRow(ui: BrowseUi, vm: BrowseViewModel, onOpenSheet: (FacetSheet) -> Unit) {
    val chips = ui.filter.chips
    val f = ui.filter
    LazyRow(
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            QualityChip(chips.includeUnproofread, R.string.chip_unproofread, R.string.setting_unproofread) {
                vm.setChips(ui.chipSettings.copy(includeUnproofread = !chips.includeUnproofread))
            }
        }
        item {
            QualityChip(chips.includeIncomplete, R.string.chip_incomplete, R.string.setting_incomplete) {
                vm.setChips(ui.chipSettings.copy(includeIncomplete = !chips.includeIncomplete))
            }
        }
        item { QualityChip(chips.includeUnverified, R.string.chip_unverified, R.string.setting_unverified, vm::toggleUnverified) }
        item {
            QualityChip(chips.includeDocuments, R.string.chip_documents, R.string.setting_documents) {
                vm.setChips(ui.chipSettings.copy(includeDocuments = !chips.includeDocuments))
            }
        }
        item { FacetChip(R.string.facet_genre, f.genres.size) { onOpenSheet(FacetSheet.GENRE) } }
        item {
            val n = f.centuries.size + (if (f.yearFrom != null || f.yearTo != null) 1 else 0)
            FacetChip(R.string.facet_period, n) { onOpenSheet(FacetSheet.PERIOD) }
        }
        item { FacetChip(R.string.facet_author, f.authorIds.size) { onOpenSheet(FacetSheet.AUTHOR) } }
        item { FacetChip(R.string.facet_type, f.types.size) { onOpenSheet(FacetSheet.TYPE) } }
        item { FacetChip(R.string.facet_language, f.languages.size) { onOpenSheet(FacetSheet.LANGUAGE) } }
        if (f.facetCount > 0) {
            item { TextButton(onClick = vm::clearFacets) { Text(stringResource(R.string.facet_clear_all)) } }
        }
    }
    val authors by vm.selectedAuthors.collectAsStateWithLifecycle()
    if (authors.isNotEmpty()) {
        LazyRow(contentPadding = PaddingValues(horizontal = 12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(authors, key = { it.id }) { a ->
                InputChip(
                    selected = true,
                    onClick = { vm.updateFacets { it.copy(authorIds = it.authorIds - a.id) } },
                    label = { Text(a.name) },
                    trailingIcon = { Icon(Icons.Default.Close, stringResource(R.string.action_clear)) },
                )
            }
        }
    }
}

@Composable
private fun QualityChip(selected: Boolean, label: Int, description: Int, onClick: () -> Unit) {
    val desc = stringResource(description)
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = { Text(stringResource(label)) },
        leadingIcon = if (selected) ({ Icon(Icons.Default.Check, null) }) else null,
        modifier = Modifier.semantics { contentDescription = desc },
    )
}

@Composable
private fun FacetChip(label: Int, selectedCount: Int, onClick: () -> Unit) {
    val name = stringResource(label)
    FilterChip(
        selected = selectedCount > 0,
        onClick = onClick,
        label = { Text(if (selectedCount > 0) stringResource(R.string.facet_selected, name, selectedCount) else name) },
        trailingIcon = { Icon(Icons.Default.ArrowDropDown, null) },
    )
}

@Composable
private fun CountAndSort(count: Int?, sort: SortOrder, onSort: (SortOrder) -> Unit) {
    var open by remember { mutableStateOf(false) }
    Row(Modifier.fillMaxWidth().padding(start = 16.dp, end = 4.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(
            if (count == null) stringResource(R.string.loading)
            else pluralStringResource(R.plurals.browse_count, count, formatCount(count)),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.weight(1f),
        )
        Box {
            TextButton(onClick = { open = true }) {
                Text(stringResource(sortLabel(sort)))
                Icon(Icons.Default.ArrowDropDown, stringResource(R.string.sort_label))
            }
            DropdownMenu(expanded = open, onDismissRequest = { open = false }) {
                SortOrder.entries.forEach { s ->
                    DropdownMenuItem(
                        text = { Text(stringResource(sortLabel(s))) },
                        onClick = { open = false; onSort(s) },
                        leadingIcon = { if (s == sort) Icon(Icons.Default.Check, null) },
                    )
                }
            }
        }
    }
}

private fun sortLabel(s: SortOrder) = when (s) {
    SortOrder.TITLE -> R.string.sort_title
    SortOrder.YEAR_ASC -> R.string.sort_year_asc
    SortOrder.YEAR_DESC -> R.string.sort_year_desc
}

@Composable
private fun WorkList(list: BrowseList, onOpen: (WorkSummary) -> Unit, onLoadMore: () -> Unit) {
    val state = rememberLazyListState()
    LaunchedEffect(list.query) { state.scrollToItem(0) }
    val nearEnd by remember {
        derivedStateOf { state.layoutInfo.visibleItemsInfo.lastOrNull()?.index?.let { it >= state.layoutInfo.totalItemsCount - 15 } ?: false }
    }
    LaunchedEffect(nearEnd, list.items.size) { if (nearEnd) onLoadMore() }

    when {
        list.error -> Text(stringResource(R.string.browse_catalog_error), Modifier.padding(16.dp))
        list.loading && list.items.isEmpty() -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
        list.count == 0 -> Text(stringResource(R.string.browse_no_results), Modifier.padding(16.dp))
        else -> LazyColumn(state = state, modifier = Modifier.fillMaxSize()) {
            items(list.items, key = { it.id }) { w -> WorkRow(w, onClick = { onOpen(w) }) }
        }
    }
}

@Composable
fun WorkRow(w: WorkSummary, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val sep = stringResource(R.string.list_separator)
    Column(modifier.fillMaxWidth().clickable(onClick = onClick).padding(horizontal = 16.dp, vertical = 10.dp)) {
        Text(w.displayTitle, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Medium, maxLines = 2, overflow = TextOverflow.Ellipsis)
        val line = listOfNotNull(w.authors, w.year?.toString(), typeName(w.type)).joinToString(sep)
        Text(line, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 1, overflow = TextOverflow.Ellipsis)
        val badges = listOfNotNull(
            qualityBadge(w.quality),
            if (!w.complete) stringResource(R.string.incomplete) else null,
        ).joinToString(sep)
        Text(
            badges,
            style = MaterialTheme.typography.labelSmall,
            color = if (w.quality == Quality.VALIDATED || w.quality == Quality.PROOFREAD) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.secondary,
        )
    }
}
