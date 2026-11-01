// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.reader

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Slider
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.bibliotheke.wikisource.R
import app.bibliotheke.wikisource.data.library.Locator
import app.bibliotheke.wikisource.data.library.Part
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlin.math.abs
import kotlin.math.roundToInt

/** What is open over the reader. */
sealed interface ReaderSheet {
    data object Appearance : ReaderSheet
    data object Search : ReaderSheet
    data object GoToPage : ReaderSheet
    data class Note(val hit: Hit) : ReaderSheet
    data class Mark(val hit: Hit) : ReaderSheet
    data class External(val url: String) : ReaderSheet
    data class Detail(val html: String) : ReaderSheet
}

/**
 * The reader (PLAN.md 8): paginated parts in a WebView, turned by swipes and taps on a
 * layer over it (8.1), notes in a sheet (8.4), appearance (8.5), contents, bookmarks,
 * progress and search (8.6). The position is kept on every turn (7.4).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReaderScreen(onBack: () -> Unit, vm: ReaderViewModel = hiltViewModel()) {
    val ui by vm.ui.collectAsStateWithLifecycle()
    val settings by vm.settings.collectAsStateWithLifecycle()
    val jump by vm.jump.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val resources = LocalResources.current
    val fontScale = LocalDensity.current.fontScale
    val pal = palette(settings?.theme ?: app.bibliotheke.wikisource.data.settings.ReaderTheme.SYSTEM, isSystemInDarkTheme())
    val tableHint = stringResource(R.string.reader_table_hint)
    val scope = rememberCoroutineScope()

    var chrome by rememberSaveable { mutableStateOf(false) }
    var sheet by remember { mutableStateOf<ReaderSheet?>(null) }
    val drawer = rememberDrawerState(DrawerValue.Closed)

    val controller = remember {
        ReaderController(
            context,
            neighbour = { k, d -> vm.neighbour(k, d)?.let { p -> refOf(vm, p) } },
            onState = vm::onState,
            onError = { _, _ -> },
        )
    }
    // A new page (first open, rotation, process death) goes to the kept position; a jump
    // made for an earlier page is not repeated.
    var handled by remember { mutableStateOf(vm.jump.value?.seq ?: -1L) }
    DisposableEffect(controller) {
        vm.attach()
        onDispose { controller.destroy() }
    }
    LifecycleEventEffect(Lifecycle.Event.ON_STOP) { vm.flush() }
    StatusBarIcons(pal.dark)

    // Settings apply live, 150 ms after the last change (8.5); the first at once.
    var applied by remember { mutableStateOf(false) }
    val js = settings?.toJs(pal, fontScale, tableHint)
    LaunchedEffect(js) {
        if (js == null) return@LaunchedEffect
        if (applied) delay(150)
        controller.applySettings(js, pal.bg.toArgb())
        applied = true
    }
    LaunchedEffect(jump?.seq, applied) {
        val j = jump ?: return@LaunchedEffect
        if (!applied || j.seq <= handled) return@LaunchedEffect
        handled = j.seq
        refOf(vm, j.part)?.let { controller.open(it, j.target.toJson()) }
    }

    fun onTap(o: Offset, width: Int) {
        if (chrome) { chrome = false; return }
        controller.hit(o.x, o.y) { h ->
            when (h.kind) {
                "note" -> sheet = ReaderSheet.Note(h)
                "pagemark" -> sheet = ReaderSheet.Mark(h)
                "external" -> sheet = ReaderSheet.External(h.href)
                "part" -> vm.openLink(h.index, h.id)
                "anchor" -> vm.ui.value.current?.let { vm.openLink(it.index, h.id) }
                "table" -> sheet = ReaderSheet.Detail(h.html)
                "image" -> sheet = ReaderSheet.Detail("<img src=\"${h.src}\" alt=\"\">")
                else -> when {
                    o.x < width / 3f -> controller.turn(-1)
                    o.x > width * 2 / 3f -> controller.turn(+1)
                    else -> chrome = true
                }
            }
        }
    }

    MaterialTheme(colorScheme = pal.colorScheme(MaterialTheme.colorScheme)) {
        ModalNavigationDrawer(
            drawerState = drawer,
            gesturesEnabled = drawer.isOpen,
            drawerContent = {
                ModalDrawerSheet {
                    ReaderDrawer(
                        ui = ui,
                        onPart = { p -> scope.launch { drawer.close() }; vm.openPart(p) },
                        onBookmark = { b -> scope.launch { drawer.close() }; vm.openBookmark(b) },
                        onDeleteBookmark = vm::deleteBookmark,
                    )
                }
            },
        ) {
            Box(Modifier.fillMaxSize().background(pal.bg)) {
                Column(Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing)) {
                    Box(Modifier.weight(1f).fillMaxWidth()) {
                        AndroidView(
                            factory = { controller.view.also { (it.parent as? ViewGroup)?.removeView(it) } },
                            modifier = Modifier.fillMaxSize(),
                        )
                        // Every touch lands here, so the WebView never scrolls; taps are
                        // asked of the page first (links, notes), then turn or show the bars.
                        Box(
                            Modifier.fillMaxSize()
                                .pointerInput(controller) { detectTapGestures { o -> onTap(o, size.width) } }
                                .pointerInput(controller) {
                                    val threshold = 40.dp.toPx()
                                    var dx = 0f
                                    detectHorizontalDragGestures(
                                        onDragStart = { dx = 0f },
                                        onDragEnd = { if (abs(dx) > threshold) controller.turn(if (dx < 0) +1 else -1) },
                                        onDragCancel = { dx = 0f },
                                    ) { change, d -> change.consume(); dx += d }
                                },
                        )
                    }
                    Footer(ui)
                }

                AnimatedVisibility(chrome, enter = fadeIn() + slideInVertically(), exit = fadeOut() + slideOutVertically()) {
                    TopBar(
                        ui = ui,
                        onBack = onBack,
                        onContents = { chrome = false; scope.launch { drawer.open() } },
                        onSearch = { chrome = false; sheet = ReaderSheet.Search },
                        onAppearance = { chrome = false; sheet = ReaderSheet.Appearance },
                        onGoToPage = { chrome = false; sheet = ReaderSheet.GoToPage },
                        onBookmark = { here ->
                            if (here != null) vm.deleteBookmark(here)
                            else ui.page?.let { p -> controller.excerpt(p.anchor, 200) { vm.addBookmark(it) } }
                        },
                    )
                }
                AnimatedVisibility(
                    chrome, modifier = Modifier.align(Alignment.BottomCenter),
                    enter = fadeIn() + slideInVertically { it }, exit = fadeOut() + slideOutVertically { it },
                ) {
                    ProgressBar(ui.progress, onSeek = { vm.openFraction(it) })
                }
                if (ui.page?.away == true) {
                    SuggestionChip(
                        onClick = { controller.back() },
                        label = { Text(stringResource(R.string.reader_back_to_text)) },
                        modifier = Modifier.align(Alignment.BottomCenter).navigationBarsPadding().padding(bottom = 32.dp),
                    )
                }
            }
        }

        when (val s = sheet) {
            null -> Unit
            ReaderSheet.Appearance -> settings?.let { AppearanceSheet(it, onChange = vm::setSettings, onDismiss = { sheet = null }) }
            ReaderSheet.Search -> SearchSheet(vm, onDismiss = { sheet = null })
            ReaderSheet.GoToPage -> GoToPageDialog(
                onGo = { num ->
                    sheet = null
                    vm.openPageMark(num) {
                        Toast.makeText(context, resources.getString(R.string.reader_page_not_found, num), Toast.LENGTH_SHORT).show()
                    }
                },
                onDismiss = { sheet = null },
            )
            is ReaderSheet.Note -> NoteSheet(
                s.hit,
                onGoToNotes = { sheet = null; controller.goToElement(s.hit.id, navigation = false) },
                onDismiss = { sheet = null },
            )
            is ReaderSheet.Mark -> PageMarkSheet(s.hit, ui.work?.domain, onDismiss = { sheet = null })
            is ReaderSheet.External -> ExternalLinkDialog(s.url, onDismiss = { sheet = null })
            is ReaderSheet.Detail -> DetailDialog(s.html, ui.work?.language, pal, onDismiss = { sheet = null })
        }
    }
}

private fun refOf(vm: ReaderViewModel, p: Part): PartRef? =
    vm.partUrl(p)?.let { PartRef(PartKey(p.index, p.sub), it, vm.ui.value.work?.language) }

private fun Target.toJson(): JsonObject = when (this) {
    is Target.At -> buildJsonObject {
        put("offset", loc.charOffset)
        put("el", loc.anchorEl)
        put("skip", loc.anchorSkip)
        if (flash) put("flash", true)
        if (flashLength > 0) put("flashTo", loc.charOffset + flashLength)
    }
    Target.Start -> JsonObject(emptyMap())
    is Target.Element -> buildJsonObject { put("element", id) }
    is Target.Mark -> buildJsonObject { put("mark", num) }
}

/** The current place as a locator, to match bookmarks. */
private fun ReaderUi.here(): Locator? {
    val c = current ?: return null
    val p = page ?: return null
    return Locator(c.index, c.sub, p.anchor, p.anchorEl, p.anchorSkip)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    ui: ReaderUi,
    onBack: () -> Unit,
    onContents: () -> Unit,
    onSearch: () -> Unit,
    onAppearance: () -> Unit,
    onGoToPage: () -> Unit,
    onBookmark: (here: app.bibliotheke.wikisource.data.library.Bookmark?) -> Unit,
) {
    val here = ui.here()
    val mark = here?.let { h -> ui.bookmarks.firstOrNull { it.at == h } }
    var menu by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(ui.work?.displayTitle.orEmpty(), maxLines = 1, overflow = TextOverflow.Ellipsis) },
        navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.action_back)) } },
        actions = {
            IconButton(onClick = onContents) { Icon(Icons.AutoMirrored.Filled.List, stringResource(R.string.detail_contents)) }
            IconButton(onClick = { onBookmark(mark) }) {
                Icon(
                    painterResource(if (mark != null) R.drawable.ic_bookmark else R.drawable.ic_bookmark_border),
                    stringResource(if (mark != null) R.string.reader_bookmark_remove else R.string.reader_bookmark_add),
                )
            }
            IconButton(onClick = onSearch) { Icon(Icons.Default.Search, stringResource(R.string.reader_search)) }
            IconButton(onClick = onAppearance) { Icon(painterResource(R.drawable.ic_text_format), stringResource(R.string.reader_appearance)) }
            Box {
                IconButton(onClick = { menu = true }) { Icon(Icons.Default.MoreVert, null) }
                DropdownMenu(expanded = menu, onDismissRequest = { menu = false }) {
                    DropdownMenuItem(text = { Text(stringResource(R.string.reader_go_to_page)) }, onClick = { menu = false; onGoToPage() })
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    )
}

/** Chapter title and page in chapter (8.6). */
@Composable
private fun Footer(ui: ReaderUi) {
    val part = ui.current
    val page = ui.page
    Row(Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(
            part?.let { partTitle(it) }.orEmpty(), style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.outline, maxLines = 1, overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f),
        )
        if (page != null && page.loaded) {
            Text(
                stringResource(R.string.reader_page_of, page.page + 1, page.pages),
                style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(start = 12.dp),
            )
        }
    }
}

/** The progress slider jumps by locator over the whole work (8.6). */
@Composable
private fun ProgressBar(progress: Float, onSeek: (Float) -> Unit) {
    var dragging by remember { mutableStateOf<Float?>(null) }
    Surface(color = MaterialTheme.colorScheme.surfaceContainer, modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.navigationBarsPadding().padding(horizontal = 16.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
            Slider(
                value = dragging ?: progress,
                onValueChange = { dragging = it },
                onValueChangeFinished = { dragging?.let(onSeek); dragging = null },
                modifier = Modifier.weight(1f),
            )
            Text(
                stringResource(R.string.reader_progress, ((dragging ?: progress) * 100).roundToInt()),
                style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(start = 12.dp),
            )
        }
    }
}

@Composable
fun partTitle(p: Part): String =
    if (p.pagesFrom != null && p.pagesTo != null && p.sub > 0 || p.kind == "synthetic" && p.pagesFrom != null) {
        stringResource(R.string.reader_pages, p.pagesFrom!!, p.pagesTo ?: p.pagesFrom!!)
    } else p.title

/** Status bar icons readable on the reader's background. */
@Composable
private fun StatusBarIcons(dark: Boolean) {
    val view = LocalView.current
    DisposableEffect(dark) {
        val window = view.context.findActivity()?.window
        val c = window?.let { WindowCompat.getInsetsController(it, view) }
        val before = c?.isAppearanceLightStatusBars
        c?.isAppearanceLightStatusBars = !dark
        c?.isAppearanceLightNavigationBars = !dark
        onDispose {
            if (c != null && before != null) {
                c.isAppearanceLightStatusBars = before
                c.isAppearanceLightNavigationBars = before
            }
        }
    }
}

private tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}
