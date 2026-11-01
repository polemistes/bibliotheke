// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.reader

import android.annotation.SuppressLint
import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewClientCompat
import app.bibliotheke.wikisource.R
import app.bibliotheke.wikisource.catalog.WikiLinks
import app.bibliotheke.wikisource.data.library.Bookmark
import app.bibliotheke.wikisource.data.library.Part
import app.bibliotheke.wikisource.data.settings.ReaderFont
import app.bibliotheke.wikisource.data.settings.ReaderMargins
import app.bibliotheke.wikisource.data.settings.ReaderSettings
import app.bibliotheke.wikisource.data.settings.ReaderTheme
import app.bibliotheke.wikisource.ui.common.openInBrowser
import kotlinx.coroutines.delay
import java.io.ByteArrayInputStream
import java.io.File
import kotlin.math.roundToInt

/** Contents (the part tree, volumes collapsible) and bookmarks (8.6). */
@Composable
fun ReaderDrawer(ui: ReaderUi, onPart: (Part) -> Unit, onBookmark: (Bookmark) -> Unit, onDeleteBookmark: (Bookmark) -> Unit) {
    var tab by rememberSaveable { mutableStateOf(0) }
    Column(Modifier.fillMaxHeight()) {
        PrimaryTabRow(selectedTabIndex = tab) {
            Tab(tab == 0, onClick = { tab = 0 }, text = { Text(stringResource(R.string.detail_contents)) })
            Tab(tab == 1, onClick = { tab = 1 }, text = { Text(stringResource(R.string.reader_bookmarks)) })
        }
        if (tab == 0) Contents(ui, onPart) else Bookmarks(ui, onBookmark, onDeleteBookmark)
    }
}

@Composable
private fun Contents(ui: ReaderUi, onPart: (Part) -> Unit) {
    var collapsed by rememberSaveable { mutableStateOf(setOf<Int>()) }
    val parentOf = remember(ui.parts) { ui.parts.filter { it.sub == 0 }.associate { it.index to it.parentIndex } }
    val hasChildren = remember(ui.parts) { ui.parts.mapNotNull { it.parentIndex }.toSet() }
    fun hidden(p: Part): Boolean {
        var a = p.parentIndex
        var guard = 0
        while (a != null && guard++ < 64) {
            if (a in collapsed) return true
            a = parentOf[a]
        }
        return false
    }
    val shown = ui.parts.filter { !hidden(it) }
    val current = ui.current
    val state = rememberLazyListState()
    // The drawer is composed before a part is open: follow the current part.
    LaunchedEffect(current?.id) {
        val i = shown.indexOfFirst { it.id == current?.id }
        if (i >= 0) state.scrollToItem((i - 2).coerceAtLeast(0))
    }
    LazyColumn(state = state) {
        items(shown, key = { it.id }) { p ->
            val ready = p.state == Part.READY
            Row(
                Modifier.fillMaxWidth().clickable(enabled = ready) { onPart(p) }
                    .padding(start = 16.dp + 16.dp * (p.depth + if (p.sub > 0) 1 else 0), end = 4.dp, top = 6.dp, bottom = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(Modifier.weight(1f).padding(vertical = 4.dp)) {
                    Text(
                        partTitle(p),
                        style = if (p.id == current?.id) MaterialTheme.typography.titleSmall else MaterialTheme.typography.bodyLarge,
                        color = if (p.id == current?.id) MaterialTheme.colorScheme.primary
                        else if (ready) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.outline,
                    )
                    if (!ready) Text(stringResource(R.string.reader_part_missing), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.outline)
                }
                if (p.sub == 0 && p.index in hasChildren) {
                    val closed = p.index in collapsed
                    IconButton(onClick = { collapsed = if (closed) collapsed - p.index else collapsed + p.index }) {
                        Icon(if (closed) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp, null)
                    }
                }
            }
        }
    }
}

@Composable
private fun Bookmarks(ui: ReaderUi, onBookmark: (Bookmark) -> Unit, onDelete: (Bookmark) -> Unit) {
    if (ui.bookmarks.isEmpty()) {
        Text(stringResource(R.string.reader_bookmarks_empty), Modifier.padding(16.dp), style = MaterialTheme.typography.bodyMedium)
        return
    }
    LazyColumn {
        items(ui.bookmarks, key = { it.id }) { b ->
            Row(Modifier.fillMaxWidth().clickable { onBookmark(b) }.padding(start = 16.dp, top = 8.dp, bottom = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text(
                        b.partTitle + "  ·  " + stringResource(R.string.reader_progress, (b.progress * 100).roundToInt()),
                        style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.outline,
                        maxLines = 1, overflow = TextOverflow.Ellipsis,
                    )
                    Text(b.excerpt, style = MaterialTheme.typography.bodyMedium, maxLines = 3, overflow = TextOverflow.Ellipsis)
                }
                IconButton(onClick = { onDelete(b) }) { Icon(Icons.Default.Delete, stringResource(R.string.action_delete)) }
            }
            HorizontalDivider()
        }
    }
}

/** Appearance (8.5): every change applies at once, the page relayouts keeping the position. */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AppearanceSheet(s: ReaderSettings, onChange: (ReaderSettings) -> Unit, onDismiss: () -> Unit) {
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            Modifier.fillMaxWidth().verticalScroll(rememberScrollState()).padding(horizontal = 20.dp).padding(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Label(R.string.reader_theme)
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                for ((t, label) in listOf(
                    ReaderTheme.SYSTEM to R.string.reader_theme_system, ReaderTheme.LIGHT to R.string.reader_theme_light,
                    ReaderTheme.SEPIA to R.string.reader_theme_sepia, ReaderTheme.DARK to R.string.reader_theme_dark,
                    ReaderTheme.BLACK to R.string.reader_theme_black,
                )) FilterChip(s.theme == t, onClick = { onChange(s.copy(theme = t)) }, label = { Text(stringResource(label)) })
            }
            Label(R.string.reader_font)
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                for (f in ReaderFont.entries) {
                    FilterChip(
                        s.font == f, onClick = { onChange(s.copy(font = f)) },
                        label = { Text(if (f == ReaderFont.SYSTEM) stringResource(R.string.reader_font_system) else f.label) },
                    )
                }
            }
            Label(R.string.reader_font_size, "${s.fontSize}")
            Slider(
                value = s.fontSize.toFloat(),
                onValueChange = { onChange(s.copy(fontSize = it.roundToInt())) },
                valueRange = ReaderSettings.MIN_SIZE.toFloat()..ReaderSettings.MAX_SIZE.toFloat(),
                steps = ReaderSettings.MAX_SIZE - ReaderSettings.MIN_SIZE - 1,
            )
            Label(R.string.reader_line_height, String.format("%.1f", s.lineHeight))
            Slider(
                value = s.lineHeight,
                onValueChange = { onChange(s.copy(lineHeight = (it * 10).roundToInt() / 10f)) },
                valueRange = ReaderSettings.MIN_LINE..ReaderSettings.MAX_LINE,
                steps = 7,
            )
            Label(R.string.reader_margins)
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                for ((m, label) in listOf(
                    ReaderMargins.SMALL to R.string.reader_margins_small, ReaderMargins.MEDIUM to R.string.reader_margins_medium,
                    ReaderMargins.LARGE to R.string.reader_margins_large,
                )) FilterChip(s.margins == m, onClick = { onChange(s.copy(margins = m)) }, label = { Text(stringResource(label)) })
            }
            SwitchRow(R.string.reader_justify, s.justify) { onChange(s.copy(justify = it)) }
            SwitchRow(R.string.reader_hyphenate, s.hyphenate) { onChange(s.copy(hyphenate = it)) }
        }
    }
}

@Composable
private fun Label(res: Int, value: String? = null) {
    Row(Modifier.fillMaxWidth().padding(top = 8.dp)) {
        Text(stringResource(res), style = MaterialTheme.typography.titleSmall, modifier = Modifier.weight(1f))
        if (value != null) Text(value, style = MaterialTheme.typography.titleSmall)
    }
}

@Composable
private fun SwitchRow(res: Int, checked: Boolean, onChange: (Boolean) -> Unit) {
    Row(Modifier.fillMaxWidth().clickable { onChange(!checked) }.padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(stringResource(res), style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
        Switch(checked, onCheckedChange = onChange)
    }
}

/** Search within the work (8.6) over the locator text of the downloaded parts. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSheet(vm: ReaderViewModel, onDismiss: () -> Unit) {
    val hits by vm.search.collectAsStateWithLifecycle()
    var query by rememberSaveable { mutableStateOf("") }
    val focus = remember { FocusRequester() }
    LaunchedEffect(Unit) { focus.requestFocus() }
    LaunchedEffect(query) {
        delay(300)
        vm.search(if (query.trim().length >= 2) query else "")
    }
    ModalBottomSheet(onDismissRequest = onDismiss, sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)) {
        Column(Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
            OutlinedTextField(
                query, onValueChange = { query = it }, singleLine = true,
                label = { Text(stringResource(R.string.reader_search)) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { vm.search(query) }),
                modifier = Modifier.fillMaxWidth().focusRequester(focus),
            )
            val list = hits
            if (list != null) {
                Text(
                    if (list.isEmpty()) stringResource(R.string.reader_search_none) else pluralStringResource(R.plurals.reader_search_hits, list.size, list.size),
                    style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.padding(vertical = 8.dp),
                )
                LazyColumn(Modifier.fillMaxWidth().height(480.dp)) {
                    items(list) { h ->
                        Column(Modifier.fillMaxWidth().clickable { vm.openHit(h); onDismiss() }.padding(vertical = 8.dp)) {
                            Text(partTitle(h.part), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.outline)
                            Text(
                                buildAnnotatedString {
                                    append("…" + h.hit.before)
                                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append(h.hit.match) }
                                    append(h.hit.after + "…")
                                },
                                style = MaterialTheme.typography.bodyMedium, maxLines = 3, overflow = TextOverflow.Ellipsis,
                            )
                        }
                        HorizontalDivider()
                    }
                }
            } else Spacer(Modifier.height(240.dp))
        }
    }
}

@Composable
fun GoToPageDialog(onGo: (String) -> Unit, onDismiss: () -> Unit) {
    var num by rememberSaveable { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.reader_go_to_page)) },
        text = {
            OutlinedTextField(
                num, onValueChange = { num = it }, singleLine = true,
                label = { Text(stringResource(R.string.reader_go_to_page_hint)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii, imeAction = ImeAction.Go),
                keyboardActions = KeyboardActions(onGo = { if (num.isNotBlank()) onGo(num.trim()) }),
            )
        },
        confirmButton = { TextButton(onClick = { onGo(num.trim()) }, enabled = num.isNotBlank()) { Text(stringResource(R.string.action_go)) } },
        dismissButton = { TextButton(onClick = onDismiss) { Text(stringResource(R.string.action_cancel)) } },
    )
}

/** A footnote (8.4): its text, and the notes section of the part. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteSheet(hit: Hit, onGoToNotes: () -> Unit, onDismiss: () -> Unit) {
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(Modifier.fillMaxWidth().padding(horizontal = 20.dp).padding(bottom = 16.dp)) {
            Text(stringResource(R.string.reader_note, hit.label), style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.outline)
            Box(Modifier.padding(vertical = 8.dp)) {
                Text(AnnotatedString.fromHtml(hit.html), style = MaterialTheme.typography.bodyLarge)
            }
            Row {
                Spacer(Modifier.weight(1f))
                TextButton(onClick = onGoToNotes) { Text(stringResource(R.string.reader_note_go)) }
            }
        }
    }
}

/** A page mark of the original (8.2): its number, and the scan on Wikisource. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageMarkSheet(hit: Hit, domain: String?, onDismiss: () -> Unit) {
    val context = LocalContext.current
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(Modifier.fillMaxWidth().padding(horizontal = 20.dp).padding(bottom = 16.dp)) {
            Text(stringResource(R.string.reader_page_mark, hit.num), style = MaterialTheme.typography.titleMedium)
            if (hit.title.isNotBlank() && domain != null) {
                TextButton(onClick = { onDismiss(); context.openInBrowser(WikiLinks.pageUrl(domain, hit.title)) }) {
                    Text(stringResource(R.string.reader_open_scan))
                }
            }
        }
    }
}

/** Links out of the work open in the browser after a confirmation (PLAN.md 7.2 rule 3). */
@Composable
fun ExternalLinkDialog(url: String, onDismiss: () -> Unit) {
    val context = LocalContext.current
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.reader_open_link)) },
        text = { Text(Uri.decode(url)) },
        confirmButton = { TextButton(onClick = { onDismiss(); context.openInBrowser(url) }) { Text(stringResource(R.string.action_open)) } },
        dismissButton = { TextButton(onClick = onDismiss) { Text(stringResource(R.string.action_cancel)) } },
    )
}

/** A wide table or an image, scrollable and zoomable (8.1), in the reader's colours. */
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun DetailDialog(html: String, lang: String?, pal: ReaderPalette, onDismiss: () -> Unit) {
    val bg = String.format("#%06X", pal.bg.toArgb() and 0xFFFFFF)
    val fg = String.format("#%06X", pal.fg.toArgb() and 0xFFFFFF)
    val page = """<!DOCTYPE html><html lang="${lang ?: ""}"><head><meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="/assets/reader/fonts/fonts.css">
        <style>body{margin:12px;background:$bg;color:$fg;font-family:'Literata',serif;font-size:16px}
        table{border-collapse:collapse}td,th{padding:0 .4em;vertical-align:top}img{max-width:100%;height:auto}a{color:inherit}</style>
        </head><body>$html</body></html>"""
    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Surface(Modifier.fillMaxSize().padding(12.dp), shape = MaterialTheme.shapes.large) {
            Column {
                Row(Modifier.fillMaxWidth()) {
                    Spacer(Modifier.weight(1f))
                    IconButton(onClick = onDismiss) { Icon(Icons.Default.Close, stringResource(R.string.action_close)) }
                }
                AndroidView(
                    factory = { ctx ->
                        val loader = WebViewAssetLoader.Builder()
                            .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(ctx))
                            .addPathHandler("/works/", WebViewAssetLoader.InternalStoragePathHandler(ctx, File(ctx.filesDir, "works")))
                            .build()
                        WebView(ctx).apply {
                            settings.javaScriptEnabled = false
                            settings.allowFileAccess = false
                            settings.allowContentAccess = false
                            settings.builtInZoomControls = true
                            settings.displayZoomControls = false
                            settings.textZoom = 100
                            setBackgroundColor(pal.bg.toArgb())
                            webViewClient = object : WebViewClientCompat() {
                                override fun shouldInterceptRequest(view: WebView, request: WebResourceRequest): WebResourceResponse? =
                                    if (request.url.host == "appassets.androidplatform.net") loader.shouldInterceptRequest(request.url)
                                    else WebResourceResponse("text/plain", "utf-8", 403, "Blocked", emptyMap(), ByteArrayInputStream(ByteArray(0)))

                                override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest) = true
                            }
                            loadDataWithBaseURL("https://appassets.androidplatform.net/assets/reader/", page, "text/html", "utf-8", null)
                        }
                    },
                    modifier = Modifier.fillMaxSize().navigationBarsPadding(),
                )
            }
        }
    }
}
