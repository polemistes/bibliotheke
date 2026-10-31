// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.browse

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import app.bibliotheke.wikisource.R
import app.bibliotheke.wikisource.catalog.WorkFilter
import app.bibliotheke.wikisource.ui.common.authorDates
import app.bibliotheke.wikisource.ui.common.centuryName
import app.bibliotheke.wikisource.ui.common.formatCount
import app.bibliotheke.wikisource.ui.common.genreName
import app.bibliotheke.wikisource.ui.common.languageName
import app.bibliotheke.wikisource.ui.common.typeName
import kotlinx.coroutines.delay

/** One facet's choices with their counts under the rest of the filter; choices apply at once. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacetSheetHost(sheet: FacetSheet, ui: BrowseUi, vm: BrowseViewModel, onDismiss: () -> Unit) {
    ModalBottomSheet(onDismissRequest = onDismiss, sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)) {
        val f = ui.filter
        val title = stringResource(
            when (sheet) {
                FacetSheet.GENRE -> R.string.facet_genre
                FacetSheet.PERIOD -> R.string.facet_period
                FacetSheet.AUTHOR -> R.string.facet_author
                FacetSheet.TYPE -> R.string.facet_type
                FacetSheet.LANGUAGE -> R.string.facet_language
            },
        )
        Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(title, style = MaterialTheme.typography.titleLarge, modifier = Modifier.weight(1f))
            TextButton(onClick = {
                vm.updateFacets {
                    when (sheet) {
                        FacetSheet.GENRE -> it.copy(genres = emptySet())
                        FacetSheet.PERIOD -> it.copy(centuries = emptySet(), yearFrom = null, yearTo = null)
                        FacetSheet.AUTHOR -> it.copy(authorIds = emptySet())
                        FacetSheet.TYPE -> it.copy(types = emptySet())
                        FacetSheet.LANGUAGE -> it.copy(languages = emptySet())
                    }
                }
            }) { Text(stringResource(R.string.action_clear)) }
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.action_done)) }
        }
        when (sheet) {
            FacetSheet.GENRE -> {
                val values by produceState<List<Pair<String, Int>>?>(null, f) { value = vm.genreCounts().map { it.key to it.count } }
                Choices(values, f.genres, { genreName(it) }) { k -> vm.updateFacets { it.copy(genres = it.genres.toggle(k)) } }
            }
            FacetSheet.TYPE -> {
                val values by produceState<List<Pair<String, Int>>?>(null, f) { value = vm.typeCounts().map { it.key to it.count } }
                Choices(values, f.types, { typeName(it) }) { k -> vm.updateFacets { it.copy(types = it.types.toggle(k)) } }
            }
            FacetSheet.LANGUAGE -> {
                val values by produceState<List<Pair<String, Int>>?>(null, f) { value = vm.languageCounts().map { it.key to it.count } }
                Choices(values, f.languages, { "${languageName(it)} ($it)" }) { k -> vm.updateFacets { it.copy(languages = it.languages.toggle(k)) } }
            }
            FacetSheet.PERIOD -> {
                YearRange(f.yearFrom, f.yearTo) { from, to -> vm.updateFacets { it.copy(yearFrom = from, yearTo = to) } }
                val values by produceState<List<Pair<Int, Int>>?>(null, f) { value = vm.centuryCounts().map { it.key to it.count } }
                Choices(values, f.centuries, { centuryName(it) }) { k -> vm.updateFacets { it.copy(centuries = it.centuries.toggle(k)) } }
            }
            FacetSheet.AUTHOR -> AuthorChoices(f, vm)
        }
    }
}

private fun <T> Set<T>.toggle(v: T) = if (v in this) this - v else this + v

@Composable
private fun <K> Choices(values: List<Pair<K, Int>>?, selected: Set<K>, label: @Composable (K) -> String, onToggle: (K) -> Unit) {
    when {
        values == null -> Text(stringResource(R.string.loading), Modifier.padding(16.dp))
        values.isEmpty() -> Text(stringResource(R.string.facet_no_values), Modifier.padding(16.dp))
        else -> LazyColumn(Modifier.fillMaxWidth().heightIn(max = 520.dp)) {
            items(values, key = { it.first.toString() }) { (k, n) ->
                CheckRow(k in selected, label(k), formatCount(n)) { onToggle(k) }
            }
        }
    }
}

@Composable
private fun CheckRow(checked: Boolean, label: String, trailing: String, sub: String? = null, onClick: () -> Unit) {
    Row(Modifier.fillMaxWidth().clickable(onClick = onClick).padding(horizontal = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checked, onCheckedChange = { onClick() })
        Column(Modifier.weight(1f)) {
            Text(label, style = MaterialTheme.typography.bodyLarge)
            if (sub != null) Text(sub, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Text(trailing, style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(end = 8.dp))
    }
}

@Composable
private fun YearRange(from: Int?, to: Int?, onChange: (Int?, Int?) -> Unit) {
    var a by remember { mutableStateOf(from?.toString() ?: "") }
    var b by remember { mutableStateOf(to?.toString() ?: "") }
    fun parse(s: String) = s.trim().toIntOrNull()
    Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedTextField(
            value = a, onValueChange = { a = it.filter { c -> c.isDigit() || c == '-' }.take(5); onChange(parse(a), parse(b)) },
            label = { Text(stringResource(R.string.facet_year_from)) }, singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.weight(1f),
        )
        OutlinedTextField(
            value = b, onValueChange = { b = it.filter { c -> c.isDigit() || c == '-' }.take(5); onChange(parse(a), parse(b)) },
            label = { Text(stringResource(R.string.facet_year_to)) }, singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun AuthorChoices(f: WorkFilter, vm: BrowseViewModel) {
    var q by remember { mutableStateOf("") }
    OutlinedTextField(
        value = q, onValueChange = { q = it }, singleLine = true,
        label = { Text(stringResource(R.string.facet_author_search)) },
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
    )
    val values by produceState<List<app.bibliotheke.wikisource.catalog.AuthorFacet>?>(null, f, q) {
        delay(200)
        value = vm.authorCounts(q)
    }
    val list = values
    when {
        list == null -> Text(stringResource(R.string.loading), Modifier.padding(16.dp))
        list.isEmpty() -> Text(stringResource(R.string.facet_no_values), Modifier.padding(16.dp))
        else -> LazyColumn(Modifier.fillMaxWidth().heightIn(max = 480.dp)) {
            items(list, key = { it.author.id }) { a ->
                CheckRow(a.author.id in f.authorIds, a.author.name, formatCount(a.count), authorDates(a.author)) {
                    vm.updateFacets { it.copy(authorIds = it.authorIds.toggle(a.author.id)) }
                }
            }
        }
    }
}
