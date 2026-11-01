// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.ui.reader

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.PathInterpolator
import android.webkit.JavascriptInterface
import android.webkit.RenderProcessGoneDetail
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewClientCompat
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import java.io.ByteArrayInputStream
import java.io.File

/** A part by its place in the work's tree (PLAN.md 7.1 step 6). */
data class PartKey(val index: Int, val sub: Int)

/** A part the reader page can load: [url] is served by `WebViewAssetLoader`. */
data class PartRef(val key: PartKey, val url: String, val lang: String?)

/** The reader page's state (`WsrReader.state()` in reader.js). */
@Serializable
data class PageState(
    val page: Int = 0,
    val pages: Int = 1,
    val anchor: Int = 0,
    val anchorEl: Int = -1,
    val anchorSkip: Int = 0,
    val total: Int = 0,
    val away: Boolean = false,
    val loaded: Boolean = false,
    /** +1 or -1 when a turn ran past the end or the start of the part. */
    val edge: Int = 0,
)

/** What a tap hit (`WsrReader.hit` in reader.js). */
@Serializable
data class Hit(
    val kind: String = "none",
    val id: String = "",
    val label: String = "",
    val html: String = "",
    val index: Int = -1,
    val href: String = "",
    val num: String = "",
    val title: String = "",
    val src: String = "",
    val alt: String = "",
)

/**
 * The reader's WebViews (PLAN.md 8.1). Three reader pages are kept: the current part,
 * and the parts before and after it, laid out at their last and first pages and
 * stacked under the current one. A turn past the end of a part slides the current
 * WebView away and uncovers the next part, which is already laid out, so the boundary
 * has no gap. In-part turns are CSS transitions inside the page (WP0 F§4.5).
 *
 * Everything runs on the main thread; [neighbour] tells the controller which part
 * comes before or after another, [onState] reports the current part's position.
 */
@SuppressLint("SetJavaScriptEnabled")
class ReaderController(
    context: Context,
    private val neighbour: (PartKey, Int) -> PartRef?,
    private val onState: (PartKey, PageState) -> Unit,
    private val onError: (PartKey, String) -> Unit,
) {
    val view: FrameLayout = FrameLayout(context)
    private val main = Handler(Looper.getMainLooper())
    private val json = Json { ignoreUnknownKeys = true }
    private val loader = WebViewAssetLoader.Builder()
        .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(context))
        .addPathHandler("/works/", WebViewAssetLoader.InternalStoragePathHandler(context, File(context.filesDir, "works")))
        .build()
    private val slots = List(3) { Slot(context) }
    private var cur = slots[0]
    private var next = slots[1]
    private var prev = slots[2]
    private var settingsJson = "{}"
    private var background = 0
    private var animating = false

    /** The current part, once one is open. */
    val current: PartKey? get() = cur.key

    init {
        for (s in slots) view.addView(s.web, FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        restack()
    }

    private inner class Slot(context: Context) {
        val web = WebView(context)
        var key: PartKey? = null
        var ready = false
        var state = PageState()
        /** For a neighbour: where it must stand, "start" or "last". */
        var role = ""
        private var pageLoaded = false
        private val queue = mutableListOf<String>()

        init {
            with(web.settings) {
                javaScriptEnabled = true
                allowFileAccess = false
                allowContentAccess = false
                textZoom = 100
                setSupportZoom(false)
                builtInZoomControls = false
            }
            web.isVerticalScrollBarEnabled = false
            web.isHorizontalScrollBarEnabled = false
            web.overScrollMode = View.OVER_SCROLL_NEVER
            web.isLongClickable = false
            web.setOnLongClickListener { true }
            web.addJavascriptInterface(Bridge(this), "WsrBridge")
            web.webViewClient = object : WebViewClientCompat() {
                override fun shouldInterceptRequest(view: WebView, request: WebResourceRequest): WebResourceResponse? =
                    // Only the app's own files: the reader never touches the network.
                    if (request.url.host == "appassets.androidplatform.net") loader.shouldInterceptRequest(request.url)
                    else WebResourceResponse("text/plain", "utf-8", 403, "Blocked", emptyMap(), ByteArrayInputStream(ByteArray(0)))

                override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean = true

                override fun onPageFinished(view: WebView, url: String) {
                    if (pageLoaded) return
                    pageLoaded = true
                    val pending = queue.toList()
                    queue.clear()
                    pending.forEach { web.evaluateJavascript(it, null) }
                }

                // A renderer that dies takes this WebView's page with it, not the app.
                override fun onRenderProcessGone(view: WebView, detail: RenderProcessGoneDetail): Boolean {
                    Log.w(TAG, "reader renderer gone (crash=${detail.didCrash()})")
                    key?.let { k -> main.post { onError(k, "renderer") } }
                    return true
                }
            }
            web.loadUrl(READER_URL)
        }

        fun js(code: String, result: ((String?) -> Unit)? = null) {
            if (!pageLoaded) {
                if (result == null) queue += code else main.postDelayed({ js(code, result) }, 50)
                return
            }
            web.evaluateJavascript(code) { result?.invoke(it) }
        }

        fun load(ref: PartRef, target: JsonObject, role: String) {
            key = ref.key
            ready = false
            this.role = role
            state = PageState()
            js("WsrReader.apply($settingsJson);WsrReader.open(${q(ref.url)},${q(ref.lang ?: "")},$target)")
        }
    }

    private inner class Bridge(private val slot: Slot) {
        @JavascriptInterface
        fun post(message: String) {
            main.post { event(slot, message) }
        }
    }

    private fun event(slot: Slot, message: String) {
        val o = runCatching { json.decodeFromString<JsonObject>(message) }.getOrNull() ?: return
        val type = o["type"]?.toString()?.trim('"')
        val key = slot.key ?: return
        when (type) {
            "ready", "layout" -> {
                slot.state = json.decodeFromJsonElement(PageState.serializer(), o)
                val first = !slot.ready
                slot.ready = true
                if (slot === cur) {
                    onState(key, slot.state)
                    if (first) prefetch()
                } else if (type == "layout") {
                    // A neighbour keeps standing at its boundary after a relayout.
                    standAtRole(slot)
                }
            }
            "error" -> if (slot === cur) onError(key, o["message"]?.toString() ?: "")
        }
    }

    private fun standAtRole(slot: Slot) {
        when (slot.role) {
            "last" -> slot.js("JSON.stringify(WsrReader.goToPage(1e9,false))") { r -> parse(r)?.let { slot.state = it } }
            "start" -> slot.js("JSON.stringify(WsrReader.goToPage(0,false))") { r -> parse(r)?.let { slot.state = it } }
        }
    }

    /** Reader settings for every page (the `WsrReader.apply` keys); relayouts keep the position. */
    fun applySettings(settings: JsonObject, bg: Int) {
        settingsJson = settings.toString()
        background = bg
        for (s in slots) {
            s.web.setBackgroundColor(bg)
            s.js("WsrReader.apply($settingsJson)")
        }
    }

    /**
     * Opens [ref] at [target] (`{offset, el, skip}`, `{last: true}`, `{element}`, `{mark}`,
     * optionally `flash`), reusing a page that holds the part already.
     */
    fun open(ref: PartRef, target: JsonObject) {
        val have = slots.firstOrNull { it.key == ref.key && it.ready }
        if (have != null) {
            makeCurrent(have)
            have.role = ""
            have.js("JSON.stringify((WsrReader.go($target), WsrReader.state()))") { r ->
                parse(r)?.let { have.state = it; if (have === cur) onState(ref.key, it) }
            }
            prefetch()
        } else {
            cur.load(ref, target, "")
        }
    }

    private fun makeCurrent(s: Slot) {
        if (s === cur) return
        if (s === next) { next = cur } else if (s === prev) { prev = cur }
        cur = s
        restack()
    }

    /** Loads the parts before and after the current one into the other two pages. */
    private fun prefetch() {
        val k = cur.key ?: return
        val after = neighbour(k, +1)
        val before = neighbour(k, -1)
        // Keep a page that holds the wanted part already, wherever it is.
        if (after != null && prev.key == after.key) { val t = prev; prev = next; next = t }
        if (before != null && next.key == before.key && next.key != after?.key) { val t = next; next = prev; prev = t }
        place(next, after, "start")
        place(prev, before, "last")
        restack()
    }

    private fun place(slot: Slot, ref: PartRef?, role: String) {
        if (ref == null) { slot.role = ""; return }
        if (slot.key == ref.key && slot.ready) {
            if (slot.role != role) { slot.role = role; standAtRole(slot) }
            return
        }
        slot.load(ref, if (role == "last") buildJsonObject { put("last", true) } else JsonObject(emptyMap()), role)
    }

    /** The current page on top; the neighbours under it, untransformed. */
    private fun restack() {
        cur.web.z = 2f
        next.web.z = 1f
        prev.web.z = 0f
        for (s in slots) if (s !== cur || !animating) s.web.translationX = 0f
    }

    /** One page on (+1) or back (-1), into the next or previous part at the ends. */
    fun turn(dir: Int) {
        if (animating || !cur.ready) return
        cur.js("JSON.stringify(WsrReader.turn($dir))") { r ->
            val st = parse(r) ?: return@js
            if (st.edge == 0) {
                cur.state = st
                cur.key?.let { onState(it, st) }
            } else crossTo(dir)
        }
    }

    private fun crossTo(dir: Int) {
        val k = cur.key ?: return
        val ref = neighbour(k, dir) ?: return
        val target = if (dir > 0) next else prev
        if (target.key != ref.key || !target.ready) {
            // Not laid out yet (a fast reader, a slow phone): open it in place.
            open(ref, if (dir > 0) JsonObject(emptyMap()) else buildJsonObject { put("last", true) })
            return
        }
        // Uncover the neighbour: it goes directly under the current page.
        if (dir > 0) { next.web.z = 1f; prev.web.z = 0f } else { prev.web.z = 1f; next.web.z = 0f }
        animating = true
        val old = cur
        val w = view.width.toFloat()
        old.web.animate()
            .translationX(if (dir > 0) -w else w)
            .setDuration(TURN_MS)
            .setInterpolator(PathInterpolator(0.2f, 0.7f, 0.3f, 1f))
            .withEndAction {
                if (dir > 0) { prev = next.also { next = prev }; cur = prev.also { prev = cur } } else { next = prev.also { prev = next }; cur = next.also { next = cur } }
                animating = false
                cur.role = ""
                restack()
                cur.key?.let { onState(it, cur.state) }
                // The old page holds the part just left, at the right boundary already.
                prefetch()
            }
            .start()
    }

    fun goToPage(page: Int) = call("WsrReader.goToPage($page,false)")
    fun goToLocator(offset: Int, el: Int = -1, skip: Int = 0) = call("WsrReader.goToLocator($offset,false,$el,$skip)")

    /** An element of the current part: a link target (navigation) or a footnote ("away"). */
    fun goToElement(id: String, navigation: Boolean) = call("WsrReader.goToElement(${q(id)},$navigation)")

    /** Back from a footnote section to the reading position. */
    fun back() = call("WsrReader.back()")

    fun flashSentence(offset: Int) = cur.js("WsrReader.flashSentence($offset)")
    fun flash(from: Int, to: Int) = cur.js("WsrReader.flash($from,$to)")

    private fun call(expr: String) {
        val s = cur
        s.js("JSON.stringify($expr)") { r ->
            val st = parse(r) ?: return@js
            s.state = st
            if (s === cur) s.key?.let { onState(it, st) }
        }
    }

    /** What is under a tap at ([xPx], [yPx]) in pixels of the reader view. */
    fun hit(xPx: Float, yPx: Float, result: (Hit) -> Unit) {
        val d = view.resources.displayMetrics.density
        cur.js("JSON.stringify(WsrReader.hit(${xPx / d},${yPx / d}))") { r ->
            result(parseAs(r, Hit.serializer()) ?: Hit())
        }
    }

    /** About [chars] characters of the text at [offset] of the current part, whitespace squeezed. */
    fun excerpt(offset: Int, chars: Int, result: (String) -> Unit) {
        cur.js("WsrReader.excerpt($offset,$chars)") { r -> result(runCatching { json.decodeFromString<String>(r ?: "\"\"") }.getOrDefault("")) }
    }

    fun destroy() {
        for (s in slots) {
            s.web.stopLoading()
            s.web.destroy()
        }
    }

    private fun parse(r: String?): PageState? = parseAs(r, PageState.serializer())

    /** evaluateJavascript gives the JSON of a JS string holding JSON. */
    private fun <T> parseAs(r: String?, ser: kotlinx.serialization.KSerializer<T>): T? = runCatching {
        val inner = json.decodeFromString<String>(r ?: return null)
        json.decodeFromString(ser, inner)
    }.getOrNull()

    private fun q(s: String) = Json.encodeToString(s)

    companion object {
        private const val TAG = "BibliothekeReader"
        const val READER_URL = "https://appassets.androidplatform.net/assets/reader/reader.html"
        const val TURN_MS = 260L
    }
}
