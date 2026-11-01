// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.reader

import android.annotation.SuppressLint
import android.net.Uri
import android.webkit.RenderProcessGoneDetail
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewClientCompat
import app.bibliotheke.wikisource.R
import app.bibliotheke.wikisource.data.library.Part
import app.bibliotheke.wikisource.ui.common.openInBrowser
import java.io.File

/**
 * A plain, scrolling view of one downloaded part, until the paginated reader of WP4: it
 * shows that everything needed to read is on the device (R4), images and footnotes
 * included, with previous/next and the list of parts.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartViewerScreen(onBack: () -> Unit, vm: PartViewerViewModel = hiltViewModel()) {
    val ui by vm.ui.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var contents by remember { mutableStateOf(false) }
    var external by remember { mutableStateOf<String?>(null) }
    val part = ui.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(part?.let { partTitle(it) } ?: ui.work?.displayTitle.orEmpty(), maxLines = 1, overflow = TextOverflow.Ellipsis) },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.action_back)) } },
                actions = {
                    if (ui.parts.size > 1) {
                        IconButton(onClick = { contents = true }) { Icon(Icons.AutoMirrored.Filled.List, stringResource(R.string.detail_contents)) }
                    }
                },
            )
        },
        bottomBar = {
            if (ui.parts.size > 1) {
                BottomAppBar {
                    Row(Modifier.fillMaxWidth()) {
                        TextButton(onClick = vm::previous, enabled = ui.hasPrevious) { Text(stringResource(R.string.reader_previous)) }
                        Box(Modifier.weight(1f))
                        TextButton(onClick = vm::next, enabled = ui.hasNext) { Text(stringResource(R.string.reader_next)) }
                    }
                }
            }
        },
    ) { padding ->
        val work = ui.work
        when {
            work == null || part == null -> Unit
            ui.html == null -> Text(stringResource(R.string.reader_not_downloaded), Modifier.padding(padding).padding(16.dp))
            else -> PartWebView(
                html = ui.html!!, lang = work.language, baseUrl = ui.baseUrl, key = part.id,
                modifier = Modifier.padding(padding).fillMaxSize(),
                onPart = vm::openIndex,
                onExternal = { external = it },
            )
        }
    }

    if (contents) {
        ModalBottomSheet(onDismissRequest = { contents = false }) {
            LazyColumn {
                items(ui.parts, key = { it.id }) { p ->
                    Text(
                        partTitle(p),
                        style = if (p.id == part?.id) MaterialTheme.typography.titleSmall else MaterialTheme.typography.bodyLarge,
                        color = if (p.state == Part.READY) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.outline,
                        modifier = Modifier.fillMaxWidth().clickable(enabled = p.state == Part.READY) { contents = false; vm.open(p) }
                            .padding(start = 16.dp + 16.dp * p.depth, end = 16.dp, top = 10.dp, bottom = 10.dp),
                    )
                }
            }
        }
    }
    external?.let { url ->
        // Links out of the work open in the browser after a confirmation (PLAN.md 7.2 rule 3).
        AlertDialog(
            onDismissRequest = { external = null },
            title = { Text(stringResource(R.string.reader_open_link)) },
            text = { Text(Uri.decode(url)) },
            confirmButton = { TextButton(onClick = { external = null; context.openInBrowser(url) }) { Text(stringResource(R.string.action_open)) } },
            dismissButton = { TextButton(onClick = { external = null }) { Text(stringResource(R.string.action_cancel)) } },
        )
    }
}

@Composable
private fun partTitle(p: Part): String =
    if (p.pagesFrom != null && p.pagesTo != null && p.sub > 0 || p.kind == "synthetic" && p.pagesFrom != null) {
        stringResource(R.string.reader_pages, p.pagesFrom!!, p.pagesTo ?: p.pagesFrom!!)
    } else p.title

@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun PartWebView(
    html: String,
    lang: String?,
    baseUrl: String,
    key: Long,
    modifier: Modifier,
    onPart: (Int) -> Unit,
    onExternal: (String) -> Unit,
) {
    val text = MaterialTheme.colorScheme.onSurface.toArgb()
    val bg = MaterialTheme.colorScheme.surface.toArgb()
    val muted = MaterialTheme.colorScheme.outline.toArgb()
    val page = remember(key, html, text, bg) { wrap(html, lang, css(text, bg, muted)) }
    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            val loader = WebViewAssetLoader.Builder()
                .addPathHandler("/works/", WebViewAssetLoader.InternalStoragePathHandler(ctx, File(ctx.filesDir, "works")))
                .build()
            WebView(ctx).apply {
                settings.javaScriptEnabled = false
                settings.allowFileAccess = false
                settings.allowContentAccess = false
                setBackgroundColor(bg)
                webViewClient = object : WebViewClientCompat() {
                    override fun shouldInterceptRequest(view: WebView, request: WebResourceRequest): WebResourceResponse? =
                        loader.shouldInterceptRequest(request.url)

                    // A renderer that dies takes this WebView with it, not the app; the
                    // screen shows it blank until the part is opened again.
                    override fun onRenderProcessGone(view: WebView, detail: RenderProcessGoneDetail): Boolean = true

                    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                        val url = request.url
                        when {
                            url.scheme == "app" && url.host == "part" -> url.pathSegments.firstOrNull()?.toIntOrNull()?.let(onPart)
                            url.host == "appassets.androidplatform.net" -> return false // anchors in the page
                            url.scheme == "https" || url.scheme == "http" -> onExternal(url.toString())
                        }
                        return true
                    }
                }
            }
        },
        update = { wv ->
            if (wv.tag != page) {
                wv.tag = page
                wv.loadDataWithBaseURL(baseUrl, page, "text/html", "utf-8", null)
            }
        },
    )
}

private fun hex(argb: Int) = String.format("#%06X", argb and 0xFFFFFF)

private fun css(text: Int, bg: Int, muted: Int) = """
    body { color: ${hex(text)}; background: ${hex(bg)}; font-family: serif; font-size: 18px; line-height: 1.5;
           margin: 16px; overflow-wrap: break-word; }
    img { max-width: 100%; height: auto; }
    table { max-width: 100%; display: block; overflow-x: auto; }
    a { color: inherit; }
    .wsr-line { display: block; padding-left: 2em; text-indent: -2em; }
    .wsr-stanza { margin-bottom: 1em; }
    .wsr-page::before { content: "[" attr(data-num) "]"; color: ${hex(muted)}; font-size: 0.7em; padding: 0 0.2em; }
    .wsr-ln-src { float: right; color: ${hex(muted)}; font-size: 0.7em; }
    .wsr-sc { font-variant: small-caps; }
    .wsr-sp { letter-spacing: 0.15em; }
    .wsr-big { font-size: 1.3em; }
    .wsr-dropcap { font-size: 2em; }
    .wsr-fnref { font-size: 0.7em; }
    .wsr-footnotes { font-size: 0.85em; border-top: 1px solid ${hex(muted)}; margin-top: 2em; }
    .wsr-speaker { font-variant: small-caps; }
    .wsr-stage { font-style: italic; }
""".trimIndent()

private fun wrap(fragment: String, lang: String?, css: String): String =
    """<!DOCTYPE html><html lang="${lang ?: ""}"><head><meta charset="utf-8"><meta name="viewport" content="width=device-width, initial-scale=1"><style>$css</style></head><body>$fragment</body></html>"""
