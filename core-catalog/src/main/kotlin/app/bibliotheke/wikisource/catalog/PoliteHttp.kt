// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.catalog

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.delay
import kotlinx.coroutines.runInterruptible
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.ConcurrentHashMap

/**
 * Every request of the app goes through here (N6, section 0): the Wikimedia User-Agent,
 * one request in flight per host (held until the body is read), and exponential backoff
 * on 429, 5xx and I/O errors, honouring `Retry-After`.
 */
class PoliteHttp(
    private val client: OkHttpClient,
    val userAgent: String,
    private val maxAttempts: Int = 4,
    private val baseDelayMs: Long = 1_000,
) {
    private val hostLocks = ConcurrentHashMap<String, Mutex>()

    class HttpException(val code: Int, message: String) : IOException(message)

    /**
     * Runs [block] on a successful (2xx) response, inside the host's lock. Other
     * statuses throw [HttpException] after the retries.
     */
    suspend fun <T> get(url: String, headers: Map<String, String> = emptyMap(), block: (Response) -> T): T {
        val request = Request.Builder().url(url).header("User-Agent", userAgent)
            .apply { headers.forEach { (k, v) -> header(k, v) } }.build()
        val lock = hostLocks.getOrPut(request.url.host) { Mutex() }
        var attempt = 0
        while (true) {
            attempt++
            val outcome: Outcome<T> = lock.withLock {
                try {
                    runInterruptible(Dispatchers.IO) {
                        client.newCall(request).execute().use { resp ->
                            when {
                                resp.isSuccessful -> Outcome.Done(block(resp))
                                resp.code == 429 || resp.code >= 500 ->
                                    Outcome.Retry(resp.code, resp.header("Retry-After")?.toLongOrNull())
                                else -> throw HttpException(resp.code, "HTTP ${resp.code} for ${request.url}")
                            }
                        }
                    }
                } catch (e: HttpException) {
                    throw e
                } catch (e: IOException) {
                    currentCoroutineContext().ensureActive() // an interrupt from cancellation is not a network error
                    if (attempt >= maxAttempts) throw e
                    Outcome.Retry(null, null)
                }
            }
            when (outcome) {
                is Outcome.Done -> return outcome.value
                is Outcome.Retry -> {
                    if (attempt >= maxAttempts) throw HttpException(outcome.code ?: 0, "HTTP ${outcome.code} for ${request.url}")
                    val backoff = baseDelayMs shl (attempt - 1)
                    delay(outcome.retryAfterSeconds?.let { (it * 1000).coerceAtMost(60_000) } ?: backoff)
                }
            }
        }
    }

    private sealed interface Outcome<out T> {
        class Done<T>(val value: T) : Outcome<T>
        class Retry(val code: Int?, val retryAfterSeconds: Long?) : Outcome<Nothing>
    }

    suspend fun getString(url: String): String = get(url) { it.body.string() }

    /** For clients that do not go through [get] (the image loader): the User-Agent on every request. */
    class UserAgentInterceptor(private val userAgent: String) : okhttp3.Interceptor {
        override fun intercept(chain: okhttp3.Interceptor.Chain): Response =
            chain.proceed(chain.request().newBuilder().header("User-Agent", userAgent).build())
    }

    companion object {
        const val REPO_URL = "https://github.com/polemistes/bibliotheke"
        const val CONTACT = "develop@robertemilberge.no"

        /** `Bibliotheke/<version> (<repo URL>; <contact>) okhttp/<version>` (section 0). */
        fun userAgent(appVersion: String): String =
            "Bibliotheke/$appVersion ($REPO_URL; $CONTACT) okhttp/${okhttp3.OkHttp.VERSION}"
    }
}
