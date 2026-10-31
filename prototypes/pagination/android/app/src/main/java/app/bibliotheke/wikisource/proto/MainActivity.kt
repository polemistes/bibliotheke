// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.proto

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.webkit.WebViewAssetLoader
import kotlin.math.abs

/**
 * WP0 pagination prototype (PLAN.md 8.1): one WebView shows a part paginated
 * with CSS columns; a transparent overlay takes every touch, so the WebView
 * never sees horizontal gestures, and turns pages through JS.
 *
 * Intent extras: `content` (part name, default sult-02), `mode` ("test" runs
 * the locator tests and logs the result under tag WsrProto).
 */
class MainActivity : Activity() {
    private lateinit var web: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val assets = WebViewAssetLoader.Builder()
            .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(this))
            .build()
        web = WebView(this).apply {
            settings.javaScriptEnabled = true
            settings.allowFileAccess = false
            settings.allowContentAccess = false
            isVerticalScrollBarEnabled = false
            isHorizontalScrollBarEnabled = false
            overScrollMode = View.OVER_SCROLL_NEVER
            webViewClient = object : WebViewClient() {
                override fun shouldInterceptRequest(
                    view: WebView,
                    request: WebResourceRequest,
                ): WebResourceResponse? = assets.shouldInterceptRequest(request.url)
            }
            addJavascriptInterface(Bridge(), "WsrBridge")
        }
        val overlay = TouchOverlay(this, onTurn = ::turn, onCentre = { Log.i(TAG, "centre tap") })
        setContentView(
            FrameLayout(this).apply {
                addView(web, MATCH_PARENT, MATCH_PARENT)
                addView(overlay, MATCH_PARENT, MATCH_PARENT)
            },
        )
        val content = intent.getStringExtra("content") ?: "sult-02"
        val test = if (intent.getStringExtra("mode") == "test") "&test=1&font2=26" else ""
        web.loadUrl("https://appassets.androidplatform.net/assets/harness.html?content=$content$test")
    }

    private fun turn(direction: Int) {
        web.evaluateJavascript("WsrReader.turn($direction)", null)
    }

    override fun onDestroy() {
        web.destroy()
        super.onDestroy()
    }

    private class Bridge {
        @JavascriptInterface
        fun onEvent(event: String, json: String) {
            if (event == "test") {
                // logcat truncates long lines; split the JSON into numbered chunks.
                val chunks = json.chunked(3000)
                chunks.forEachIndexed { i, chunk -> Log.i(TAG, "test ${i + 1}/${chunks.size} $chunk") }
            } else {
                Log.i(TAG, "$event $json")
            }
        }
    }

    private class TouchOverlay(
        context: Context,
        private val onTurn: (Int) -> Unit,
        private val onCentre: () -> Unit,
    ) : View(context) {
        private val detector = GestureDetector(
            context,
            object : GestureDetector.SimpleOnGestureListener() {
                override fun onDown(e: MotionEvent) = true

                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    val third = width / 3f
                    when {
                        e.x < third -> onTurn(-1)
                        e.x > 2 * third -> onTurn(1)
                        else -> onCentre()
                    }
                    return true
                }

                override fun onFling(e1: MotionEvent?, e2: MotionEvent, vx: Float, vy: Float): Boolean {
                    if (abs(vx) <= abs(vy)) return false
                    onTurn(if (vx < 0) 1 else -1)
                    return true
                }
            },
        )

        @SuppressLint("ClickableViewAccessibility")
        override fun onTouchEvent(event: MotionEvent): Boolean {
            detector.onTouchEvent(event)
            return true
        }
    }

    private companion object {
        const val TAG = "WsrProto"
    }
}
