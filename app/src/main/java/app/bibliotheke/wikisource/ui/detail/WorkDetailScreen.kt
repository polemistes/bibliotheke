// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.detail

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Checkbox
import androidx.compose.material3.AssistChip
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.bibliotheke.wikisource.R
import app.bibliotheke.wikisource.catalog.Author
import app.bibliotheke.wikisource.catalog.CatalogPart
import app.bibliotheke.wikisource.catalog.WikiLinks
import app.bibliotheke.wikisource.catalog.WorkDetail
import app.bibliotheke.wikisource.data.library.Part
import app.bibliotheke.wikisource.ui.common.authorDates
import app.bibliotheke.wikisource.ui.common.formatBytes
import app.bibliotheke.wikisource.ui.common.genreName
import app.bibliotheke.wikisource.ui.common.incompleteLabel
import app.bibliotheke.wikisource.ui.common.languageName
import app.bibliotheke.wikisource.ui.common.openInBrowser
import app.bibliotheke.wikisource.ui.common.qualityBadge
import app.bibliotheke.wikisource.ui.common.qualityLabel
import app.bibliotheke.wikisource.ui.common.shareText
import app.bibliotheke.wikisource.ui.common.typeName
import androidx.core.content.ContextCompat
import coil3.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkDetailScreen(
    onBack: () -> Unit,
    onAuthor: (wiki: String, authorId: Long) -> Unit,
    onRead: (workId: String, index: Int) -> Unit,
    vm: WorkDetailViewModel = hiltViewModel(),
) {
    val ui by vm.ui.collectAsStateWithLifecycle()
    val lib by vm.libraryUi.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val work = ui.work
    val domain = ui.wiki?.domain
    var menu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(work?.displayTitle ?: "", maxLines = 1, overflow = TextOverflow.Ellipsis) },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.action_back)) } },
                actions = {
                    if (work != null && domain != null) {
                        val url = WikiLinks.pageUrl(domain, work.entryTitle)
                        Box {
                            IconButton(onClick = { menu = true }) { Icon(Icons.Default.MoreVert, stringResource(R.string.action_more)) }
                            DropdownMenu(expanded = menu, onDismissRequest = { menu = false }) {
                                DropdownMenuItem(text = { Text(stringResource(R.string.action_open_on_wikisource)) }, onClick = { menu = false; context.openInBrowser(url) })
                                DropdownMenuItem(text = { Text(stringResource(R.string.action_share_link)) }, onClick = { menu = false; context.shareText(url) })
                            }
                        }
                    }
                },
            )
        },
    ) { padding ->
        when {
            ui.loading -> Box(Modifier.padding(padding).fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
            work == null || domain == null -> Text(stringResource(R.string.detail_not_found), Modifier.padding(padding).padding(16.dp))
            else -> DetailContent(
                ui, lib, work, domain, Modifier.padding(padding),
                onAuthor = { a -> onAuthor(ui.wiki!!.code, a.id) },
                onRead = { index -> lib.work?.let { w -> onRead(w.id, index) } },
                vm = vm,
            )
        }
    }
}

@Composable
private fun DetailContent(
    ui: DetailUi,
    lib: LibraryUi,
    w: WorkDetail,
    domain: String,
    modifier: Modifier,
    onAuthor: (Author) -> Unit,
    onRead: (index: Int) -> Unit,
    vm: WorkDetailViewModel,
) {
    val context = LocalContext.current
    val state = rememberLazyListState()
    // Android 13+: the download's progress notification needs the permission; the
    // download runs either way.
    val notifications = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { }
    fun askForNotifications() {
        if (Build.VERSION.SDK_INT >= 33 &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) notifications.launch(Manifest.permission.POST_NOTIFICATIONS)
    }
    // Header items come before the parts; scroll a text hit's part into view.
    val headerItems = 4
    LaunchedEffect(ui.parts, ui.focusOrd) {
        val i = ui.parts.indexOfFirst { it.ord == ui.focusOrd }
        if (i >= 0) state.scrollToItem(headerItems + i)
    }
    LazyColumn(modifier.fillMaxSize(), state = state) {
        item { Header(w, onAuthor) }
        item { Facts(w) }
        item {
            Column(Modifier.padding(horizontal = 16.dp)) {
                HorizontalDivider(Modifier.padding(vertical = 8.dp))
                Text(stringResource(R.string.detail_contents), style = MaterialTheme.typography.titleMedium)
                // A single-page work is found as a work, not as a text: its text_count is 0.
                when {
                    w.missingPartCount > 0 -> stringResource(R.string.detail_texts_transcribed, w.textCount, w.textCount + w.missingPartCount)
                    w.textCount > 0 -> pluralStringResource(R.plurals.detail_texts, w.textCount, w.textCount)
                    else -> null
                }?.let { Text(it, style = MaterialTheme.typography.bodyMedium) }
                w.bytesTotal?.let { Text(stringResource(R.string.detail_size, formatBytes(it)), style = MaterialTheme.typography.bodySmall) }
                if (!w.partsInCatalog && w.partCount > 1) {
                    Text(stringResource(R.string.detail_parts_live), style = MaterialTheme.typography.bodySmall)
                }
                Spacer(Modifier.height(4.dp))
            }
        }
        item {
            val firstReady = lib.parts.values.filter { it.state == Part.READY }.minByOrNull { it.index }?.index
            DownloadSection(
                lib, canChoose = ui.parts.size > 1, choosing = ui.choosing, chosen = ui.chosen.size,
                onDownload = { askForNotifications(); vm.download() }, onChoose = vm::setChoosing,
                onDownloadChosen = { askForNotifications(); vm.download(chosenOnly = true) },
                onRead = { firstReady?.let(onRead) }, onRetry = { vm.retry() }, onDelete = { vm.delete() },
            )
        }
        items(ui.parts, key = { it.id }) { p ->
            val stored = lib.parts[p.ord]
            PartRow(
                p, w.quality, p.ord == ui.focusOrd,
                choosing = ui.choosing && p.kind != "toc", chosen = p.ord in ui.chosen,
                ready = stored?.state == Part.READY,
                onClick = when {
                    ui.choosing && p.kind != "toc" -> ({ vm.toggle(p.ord) })
                    stored?.state == Part.READY -> ({ onRead(p.ord) })
                    else -> null
                },
            )
        }
        item {
            Attribution(w, domain) { context.openInBrowser(it) }
            PageSources(lib) { context.openInBrowser(it) }
        }
    }
}

@OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)
@Composable
private fun Header(w: WorkDetail, onAuthor: (Author) -> Unit) {
    Row(Modifier.padding(16.dp)) {
        if (w.coverUrl != null) {
            AsyncImage(
                model = w.coverUrl, contentDescription = null, contentScale = ContentScale.Fit,
                modifier = Modifier.width(96.dp).height(140.dp).padding(end = 16.dp),
            )
        }
        Column(Modifier.weight(1f)) {
            Text(w.displayTitle, style = MaterialTheme.typography.headlineSmall)
            val authors = w.authors.filter { it.role == "author" }
            val translators = w.authors.filter { it.role != "author" }
            if (authors.isNotEmpty()) AuthorLine(R.string.detail_by, authors.map { it.author }, onAuthor)
            if (translators.isNotEmpty()) AuthorLine(R.string.detail_translated_by, translators.map { it.author }, onAuthor)
            if (w.isVirtual) Text(stringResource(R.string.detail_virtual), style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun AuthorLine(prefix: Int, authors: List<Author>, onAuthor: (Author) -> Unit) {
    Column {
        authors.forEachIndexed { i, a ->
            val dates = authorDates(a)
            val name = if (dates != null) "${a.name} ($dates)" else a.name
            Text(
                if (i == 0) stringResource(prefix, name) else name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { onAuthor(a) }.padding(vertical = 2.dp),
            )
        }
    }
}

@OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)
@Composable
private fun Facts(w: WorkDetail) {
    Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        // Quality and completeness labels (PLAN.md 5.4).
        Text(qualityLabel(w.quality, w.qualityPages), style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
        if (!w.complete) {
            Text(
                incompleteLabel(w.incompleteReason, w.missingPartCount, w.textCount, w.missingPageCount, w.coverage),
                style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.secondary,
            )
        }
        if (w.ownTranslation) Text(stringResource(R.string.own_translation), style = MaterialTheme.typography.titleSmall)
        Fact(R.string.detail_year, w.year?.toString())
        Fact(R.string.detail_language, w.language?.let { languageName(it) })
        Fact(R.string.detail_type, typeName(w.type))
        if (w.genres.isNotEmpty()) {
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                w.genres.forEach { g -> AssistChip(onClick = {}, label = { Text(genreName(g)) }) }
            }
        }
        w.description?.let { Text(it, style = MaterialTheme.typography.bodyMedium) }
    }
}

@Composable
private fun Fact(label: Int, value: String?) {
    if (value == null) return
    Row {
        Text(stringResource(label) + ": ", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
private fun PartRow(
    p: CatalogPart,
    workQuality: String,
    focused: Boolean,
    choosing: Boolean,
    chosen: Boolean,
    ready: Boolean,
    onClick: (() -> Unit)?,
) {
    val bg = if (focused) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surface
    Row(
        Modifier.fillMaxWidth().background(bg).let { if (onClick != null) it.clickable(onClick = onClick) else it }
            .padding(start = 16.dp + 16.dp * p.depth, end = 16.dp, top = 6.dp, bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (choosing) Checkbox(checked = chosen, onCheckedChange = { onClick?.invoke() })
        Column(Modifier.weight(1f)) { PartText(p, workQuality) }
        if (ready) Icon(Icons.Default.CheckCircle, stringResource(R.string.part_downloaded), tint = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun PartText(p: CatalogPart, workQuality: String) {
    Column {
        Text(
            p.title,
            style = if (p.kind == "volume") MaterialTheme.typography.titleSmall else MaterialTheme.typography.bodyLarge,
            maxLines = 2, overflow = TextOverflow.Ellipsis,
        )
        // A part's own standing, when it differs from the work's (PLAN.md 5.4).
        val q = p.quality
        if (q != null && q != workQuality && p.kind != "toc") {
            Text(qualityBadge(q), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.secondary)
        }
    }
}

@Composable
private fun Attribution(w: WorkDetail, domain: String, open: (String) -> Unit) {
    // R11: source link, licence, and the contributors notice CC BY-SA asks for.
    val url = WikiLinks.pageUrl(domain, w.entryTitle)
    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
        HorizontalDivider()
        Text(stringResource(R.string.detail_attribution), style = MaterialTheme.typography.titleMedium)
        Text(
            stringResource(R.string.detail_source, url), style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary, modifier = Modifier.clickable { open(url) },
        )
        Text(stringResource(R.string.detail_licence), style = MaterialTheme.typography.bodySmall)
        Text(stringResource(R.string.detail_contributors), style = MaterialTheme.typography.bodySmall)
        TextButton(onClick = { open(WikiLinks.historyUrl(domain, w.entryTitle)) }) { Text(stringResource(R.string.detail_history)) }
        w.qid?.let { q ->
            Text(
                stringResource(R.string.detail_wikidata, q), style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary, modifier = Modifier.clickable { open("https://www.wikidata.org/wiki/$q") },
            )
        }
    }
}
