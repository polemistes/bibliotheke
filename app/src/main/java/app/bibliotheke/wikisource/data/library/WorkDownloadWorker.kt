// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.data.library

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.ServiceInfo
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import app.bibliotheke.wikisource.R
import app.bibliotheke.wikisource.catalog.PoliteHttp
import app.bibliotheke.wikisource.catalog.WikiLinks
import app.bibliotheke.wikisource.content.ContentApi
import app.bibliotheke.wikisource.content.LiveResolver
import app.bibliotheke.wikisource.content.Namespaces
import app.bibliotheke.wikisource.content.Normaliser
import app.bibliotheke.wikisource.content.ParsoidPage
import app.bibliotheke.wikisource.content.PartSpec
import app.bibliotheke.wikisource.content.Splitter
import app.bibliotheke.wikisource.content.WikiProfile
import app.bibliotheke.wikisource.content.WorkStructure
import app.bibliotheke.wikisource.data.catalog.CatalogRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException

/**
 * Downloads one work in the background (R4, PLAN.md 7): resolves its parts (catalog,
 * else live) and stores the tree before any content (7.1 step 5), then fetches,
 * normalises and stores each wanted part with its images, splitting oversized pages.
 * Every finished part is recorded at once, so a retry after a kill or a lost
 * connection continues where it stopped.
 */
@HiltWorker
class WorkDownloadWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val dao: LibraryDao,
    private val storage: WorkStorage,
    private val catalogs: CatalogRepository,
    private val api: ContentApi,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val id = inputData.getString(WORK_ID) ?: return Result.failure()
        var work = dao.work(id) ?: return Result.failure()
        runCatching { setForeground(foregroundInfo(work.displayTitle, 0, 0)) }
            .onFailure { Log.i(TAG, "not in the foreground: ${it.message}") }
        dao.setWorkState(id, DownloadedWork.DOWNLOADING)
        return try {
            val profile = WikiProfile.forWiki(work.wiki, work.domain)
            val ns = namespaces(work.domain)
            val source = storage.cachingSource(work, api.source(work.domain))
            if (dao.parts(id).isEmpty()) work = resolve(work, profile, ns, source)
            download(work, profile, ns, source)
            finish(id)
            Result.success()
        } catch (e: IOException) {
            Log.w(TAG, "download $id", e)
            val permanent = e is PoliteHttp.HttpException && e.code in 400..499 && e.code != 404 && e.code != 429
            if (!permanent && runAttemptCount < MAX_ATTEMPTS) {
                dao.setWorkState(id, DownloadedWork.QUEUED, e.message)
                Result.retry()
            } else {
                dao.setWorkState(id, DownloadedWork.FAILED, e.message)
                Result.failure(workDataOf(ERROR to e.message))
            }
        } catch (e: CancellationException) {
            // Stopped (constraints, a kill, a replacement): queued again, the parts done stay done.
            dao.setWorkState(id, DownloadedWork.QUEUED)
            throw e
        } catch (e: Exception) {
            // The page does not exist, or a bug: say so instead of waiting for ever.
            Log.e(TAG, "download $id", e)
            dao.setWorkState(id, DownloadedWork.FAILED, e.message ?: e.javaClass.simpleName)
            Result.failure(workDataOf(ERROR to e.message))
        }
    }

    private suspend fun resolve(work: DownloadedWork, profile: WikiProfile, ns: Namespaces, source: app.bibliotheke.wikisource.content.PageSource): DownloadedWork {
        val db = catalogs.db(work.wiki)
        val catalogWork = work.catalogId?.let { db?.work(it) }
        val structure = if (catalogWork != null && (catalogWork.partsInCatalog || catalogWork.partCount <= 1)) {
            WorkStructure.fromCatalog(catalogWork, db!!.parts(catalogWork.id))
        } else {
            LiveResolver(source, profile, ns).resolve(work.entryTitle)
        }
        val wanted: Set<Int>? = inputData.getString(INDEXES)?.let { Json.decodeFromString<List<Int>>(it).toSet() }
        // A wanted part brings its ancestors' own pages (a front part) along only when asked.
        dao.insertParts(
            structure.parts.map { p ->
                val selected = wanted == null || p.index in wanted
                Part(
                    workId = work.id, index = p.index, title = p.title, pageTitle = p.pageTitle,
                    sourceUrl = WikiLinks.pageUrl(work.domain, p.pageTitle), kind = p.kind, depth = p.depth,
                    parentIndex = p.parentIndex, selected = selected,
                    state = if (selected && p.downloadable) Part.PENDING else Part.SKIPPED,
                )
            },
        )
        val updated = work.copy(structure = structure.structure, resolvedFrom = structure.source, missingParts = structure.missingParts)
        dao.updateWork(updated)
        return updated
    }

    private suspend fun download(work0: DownloadedWork, profile: WikiProfile, ns: Namespaces, source: app.bibliotheke.wikisource.content.PageSource) {
        var work = work0
        val normaliser = Normaliser(profile, ns)
        while (true) {
            currentCoroutineContext().ensureActive()
            val parts = dao.parts(work.id)
            val todo = parts.filter { it.selected && it.sub == 0 && it.kind != PartSpec.KIND_TOC && it.state in setOf(Part.PENDING, Part.FAILED) }
            val wanted = parts.count { it.selected && it.sub == 0 && it.kind != PartSpec.KIND_TOC }
            setProgress(workDataOf(DONE to wanted - todo.size, TOTAL to wanted))
            runCatching { setForeground(foregroundInfo(work.displayTitle, wanted - todo.size, wanted)) }
            val part = todo.firstOrNull() ?: return
            val index = parts.filter { it.sub == 0 }.associate { it.pageTitle to it.index }
            val page = source.page(part.pageTitle)
            if (page == null) {
                dao.updatePart(part.copy(state = Part.FAILED, error = "missing"))
                continue
            }
            val parsoid = ParsoidPage(part.pageTitle, page.html, profile, ns)
            val r = normaliser.normalise(parsoid, Normaliser.Context(part.pageTitle, { index[it] }, storage.imageBase(work)))
            val imgDir = storage.imageDir(work).apply { mkdirs() }
            for (img in r.images) {
                val f = File(imgDir, img.file)
                if (f.isFile) continue
                // An image that cannot be had leaves the text readable (rule 4); it is retried on refresh.
                try {
                    api.download(img.url, f)
                } catch (e: PoliteHttp.HttpException) {
                    Log.w(TAG, "image ${img.url}: ${e.code}")
                }
            }
            val fetchedAt = System.currentTimeMillis()
            val split = if (r.charCount > profile.oversizedChars) Splitter.split(r.html, profile.oversizedChars) else null
            if (split == null) {
                val f = write(work, part.index, 0, r.html, Meta(part.title, page.revId, fetchedAt, r.pageNumbers, r.sourceLineNumbers, r.verse))
                dao.updatePart(
                    part.copy(state = Part.READY, filePath = storage.relative(work, f), charCount = r.charCount, revId = page.revId,
                        bytes = f.length(), error = null),
                )
            } else {
                val rows = split.slices.mapIndexed { sub, s ->
                    val title = when (val t = s.title) {
                        is Splitter.Title.Heading -> t.text
                        else -> part.title
                    }
                    val pages = s.title as? Splitter.Title.Pages
                    val f = write(work, part.index, sub, s.html,
                        Meta(title, page.revId, fetchedAt, "wsr-page" in s.html, "wsr-ln-src" in s.html, "wsr-verse" in s.html))
                    part.copy(
                        id = if (sub == 0) part.id else 0, sub = sub, title = title, kind = if (sub == 0 && split.slices.size == 1) part.kind else PartSpec.KIND_SYNTHETIC,
                        anchor = s.anchor, pagesFrom = pages?.from, pagesTo = pages?.to, filePath = storage.relative(work, f),
                        charCount = s.text.length, state = Part.READY, revId = page.revId, bytes = f.length(), error = null,
                    )
                }
                dao.replaceWithSlices(rows.first(), rows.drop(1))
            }
            if (work.licence == null && page.licence != null) {
                work = work.copy(licence = page.licence, licenceUrl = page.licenceUrl)
                dao.updateWork(work)
            }
        }
    }

    @Serializable
    private class Meta(
        val title: String, val revId: Long?, val fetchedAt: Long,
        val pageNumbers: Boolean, val sourceLineNumbers: Boolean, val verse: Boolean,
    )

    /** The part's HTML and its `meta.json` (PLAN.md 7.2 rules 5–6), each written whole before it replaces the old. */
    private fun write(work: DownloadedWork, index: Int, sub: Int, html: String, meta: Meta): File {
        val f = storage.partFile(work, index, sub)
        f.parentFile?.mkdirs()
        writeAtomic(f, html)
        writeAtomic(storage.metaFile(work, index, sub), Json.encodeToString(Meta.serializer(), meta))
        return f
    }

    private fun writeAtomic(f: File, text: String) {
        val tmp = File(f.path + ".tmp")
        tmp.writeText(text)
        if (!tmp.renameTo(f)) throw IOException("cannot write $f")
    }

    private suspend fun finish(id: String) {
        val work = dao.work(id) ?: return
        val parts = dao.parts(id)
        val wanted = parts.filter { it.kind != PartSpec.KIND_TOC }
        val state = when {
            wanted.any { it.selected && it.state == Part.FAILED } -> DownloadedWork.FAILED
            wanted.all { it.state == Part.READY } -> DownloadedWork.READY
            else -> DownloadedWork.PARTIAL
        }
        storage.clearRaw(work)
        dao.updateWork(
            work.copy(
                state = state, totalChars = parts.sumOf { it.charCount.toLong() }, bytesOnDisk = storage.size(work),
                error = parts.firstOrNull { it.state == Part.FAILED }?.let { "${it.pageTitle}: ${it.error}" },
            ),
        )
    }

    /** Namespace names of the wiki, fetched once and kept beside the works. */
    private suspend fun namespaces(domain: String): Namespaces {
        val f = File(storage.root, "namespaces/$domain.json")
        val names: Map<String, Int> = runCatching { Json.decodeFromString<Map<String, Int>>(f.readText()) }.getOrNull()
            ?: api.namespaces(domain).also {
                f.parentFile?.mkdirs()
                f.writeText(Json.encodeToString(it))
            }
        return Namespaces(Namespaces.CANONICAL + names)
    }

    private fun foregroundInfo(title: String, done: Int, total: Int): ForegroundInfo {
        val nm = applicationContext.getSystemService(NotificationManager::class.java)
        if (Build.VERSION.SDK_INT >= 26 && nm.getNotificationChannel(CHANNEL) == null) {
            nm.createNotificationChannel(
                NotificationChannel(CHANNEL, applicationContext.getString(R.string.notification_channel_downloads), NotificationManager.IMPORTANCE_LOW),
            )
        }
        val n = NotificationCompat.Builder(applicationContext, CHANNEL)
            .setSmallIcon(android.R.drawable.stat_sys_download)
            .setContentTitle(title)
            .setContentText(applicationContext.getString(R.string.notification_downloading, done, total))
            .setProgress(total, done, total == 0)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .build()
        return if (Build.VERSION.SDK_INT >= 29) ForegroundInfo(NOTIFICATION_ID, n, ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC)
        else ForegroundInfo(NOTIFICATION_ID, n)
    }

    companion object {
        const val TAG = "work-download"
        const val WORK_TAG = "work:"
        const val WORK_ID = "workId"
        const val INDEXES = "indexes"
        const val DONE = "done"
        const val TOTAL = "total"
        const val ERROR = "error"
        private const val CHANNEL = "downloads"
        private const val NOTIFICATION_ID = 2
        private const val MAX_ATTEMPTS = 8

        fun uniqueName(id: String) = "work-$id"
    }
}
