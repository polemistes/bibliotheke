// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.catalog

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import mockwebserver3.Dispatcher
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import mockwebserver3.RecordedRequest
import okhttp3.OkHttpClient
import okio.Buffer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import java.io.IOException
import java.util.concurrent.atomic.AtomicInteger
import java.util.zip.GZIPOutputStream

class NetworkTest {
    @get:Rule val tmp = TemporaryFolder()
    private val server = MockWebServer()
    private val ua = PoliteHttp.userAgent("0.1.0-test")
    private val http = PoliteHttp(OkHttpClient(), ua, baseDelayMs = 10)

    @Before fun start() = server.start(0)
    @After fun stop() = server.close()

    private fun ok(body: String) = MockResponse.Builder().code(200).body(body).build()

    @Test fun userAgentFollowsThePolicy() = runBlocking {
        server.enqueue(ok("x"))
        http.getString(server.url("/a").toString())
        val got = server.takeRequest().headers["User-Agent"]!!
        assertTrue(got, got.matches(Regex("""Bibliotheke/0\.1\.0-test \(https://github\.com/polemistes/bibliotheke; develop@robertemilberge\.no\) okhttp/\S+""")))
    }

    @Test fun oneRequestInFlightPerHost() = runBlocking {
        val inFlight = AtomicInteger(); val maxInFlight = AtomicInteger()
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                maxInFlight.accumulateAndGet(inFlight.incrementAndGet(), ::maxOf)
                Thread.sleep(50)
                inFlight.decrementAndGet()
                return ok("x")
            }
        }
        val client = OkHttpClient()
        val h = PoliteHttp(client, ua)
        (1..6).map { i -> async(kotlinx.coroutines.Dispatchers.IO) { h.getString(server.url("/$i").toString()) } }.awaitAll()
        assertEquals(6, server.requestCount)
        assertEquals(1, maxInFlight.get())
    }

    @Test fun retriesOn503And429ButNotOn404() = runBlocking {
        server.enqueue(MockResponse.Builder().code(503).build())
        server.enqueue(MockResponse.Builder().code(429).addHeader("Retry-After", "0").build())
        server.enqueue(ok("fine"))
        assertEquals("fine", http.getString(server.url("/r").toString()))
        assertEquals(3, server.requestCount)

        server.enqueue(MockResponse.Builder().code(404).build())
        try {
            http.getString(server.url("/missing").toString()); fail()
        } catch (e: PoliteHttp.HttpException) {
            assertEquals(404, e.code)
        }
        assertEquals(4, server.requestCount)
    }

    @Test fun givesUpAfterMaxAttempts() = runBlocking {
        repeat(4) { server.enqueue(MockResponse.Builder().code(500).build()) }
        try {
            http.getString(server.url("/down").toString()); fail()
        } catch (e: PoliteHttp.HttpException) {
            assertEquals(500, e.code)
        }
        assertEquals(4, server.requestCount)
    }

    @Test fun onlineSearchParsesHitsAndSnippets() = runBlocking {
        server.enqueue(ok("""{"batchcomplete":true,"continue":{"sroffset":2,"continue":"-||"},"query":{"searchinfo":{"totalhits":57},
            "search":[{"ns":0,"title":"Et Dukkehjem","pageid":18,"size":661,"snippet":"<span class=\"searchmatch\">Ibsen</span> &amp; co &#039;x&#039;"},
            {"ns":0,"title":"Gengangere","pageid":19,"size":100,"snippet":""}]}}"""))
        val api = WikiSearchApi(http, scheme = "http")
        val page = api.search("${server.hostName}:${server.port}", "Ibsen", limit = 2)
        assertEquals(listOf("Et Dukkehjem", "Gengangere"), page.hits.map { it.title })
        assertEquals("Ibsen & co 'x'", page.hits[0].snippet)
        assertEquals(57, page.totalHits); assertEquals(2, page.nextOffset)
        val q = server.takeRequest().url
        assertEquals("/w/api.php", q.encodedPath)
        assertEquals("Ibsen", q.queryParameter("srsearch")); assertEquals("0", q.queryParameter("srnamespace"))
    }

    // --- catalog download ---------------------------------------------------------

    private fun gz(f: File): ByteArray = Buffer().also { b -> GZIPOutputStream(b.outputStream()).use { it.write(f.readBytes()) } }.readByteArray()

    private fun entry(bytes: ByteArray, sha: String = sha(bytes)) = WikiEntry(
        code = "xx", dbname = "xxwikisource", domain = "xx.wikisource.org", language = "xx", name = "xx",
        url = "xx.sqlite.gz", sha256 = sha, size = bytes.size.toLong(), sizeUncompressed = 0, workCount = 6,
        generatedAt = "2026-11-01T00:00:00Z", schemaVersion = 2, showUnknownByDefault = false, defaultViewCount = 2,
    )

    private fun sha(b: ByteArray) = java.security.MessageDigest.getInstance("SHA-256").digest(b).joinToString("") { "%02x".format(it) }

    @Test fun downloadsVerifiesAndInstallsACatalog() = runBlocking {
        val bytes = gz(FixtureCatalog.create(tmp.newFolder()))
        val e = entry(bytes)
        val manifest = """{"schema_version":2,"generated_at":"x","wikis":[${e.toJson()}]}"""
        server.enqueue(ok(manifest))
        server.enqueue(MockResponse.Builder().code(200).body(Buffer().write(bytes)).build())
        val store = CatalogStore(tmp.newFolder("catalogs"))
        val dl = CatalogDownloader(http, server.url("/rel/").toString(), store)
        val m = dl.fetchManifest()
        assertEquals(listOf("xx"), m.wikis.map { it.code })
        var last = 0L
        dl.download(m.wikis[0]) { done, _ -> last = done }
        assertEquals(bytes.size.toLong(), last)
        assertEquals(setOf("xx"), store.installed.value.keys)
        runBlocking { assertEquals(2, store.db("xx")!!.countWorks(WorkFilter())) }
        assertEquals("xx", store.cachedManifest()!!.wikis[0].code)
        assertFalse(store.partialFile("xx").exists())
        store.remove("xx")
        assertTrue(store.installed.value.isEmpty())
    }

    @Test fun resumesAPartialDownloadWithRange() = runBlocking {
        val bytes = gz(FixtureCatalog.create(tmp.newFolder()))
        val store = CatalogStore(tmp.newFolder("catalogs"))
        val half = bytes.size / 2
        store.partialFile("xx").writeBytes(bytes.copyOf(half))
        server.enqueue(MockResponse.Builder().code(206).body(Buffer().write(bytes, half, bytes.size - half)).build())
        CatalogDownloader(http, server.url("/").toString(), store).download(entry(bytes))
        assertEquals("bytes=$half-", server.takeRequest().headers["Range"])
        assertEquals(setOf("xx"), store.installed.value.keys)
    }

    @Test fun rejectsACorruptDownloadAndDropsThePartialFile() = runBlocking {
        val bytes = gz(FixtureCatalog.create(tmp.newFolder()))
        val store = CatalogStore(tmp.newFolder("catalogs"))
        server.enqueue(MockResponse.Builder().code(200).body(Buffer().write(bytes)).build())
        try {
            CatalogDownloader(http, server.url("/").toString(), store).download(entry(bytes, sha = "00".repeat(32))); fail()
        } catch (e: IOException) {
            assertTrue(e.message!!.contains("sha256"))
        }
        assertTrue(store.installed.value.isEmpty())
        assertFalse(store.partialFile("xx").exists())
    }
}
