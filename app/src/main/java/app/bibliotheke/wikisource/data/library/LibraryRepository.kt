// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.data.library

import android.content.Context
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import app.bibliotheke.wikisource.catalog.WikiLinks
import app.bibliotheke.wikisource.data.catalog.CatalogRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/** Progress of a running work download, as the UI shows it. */
data class WorkProgress(val partsDone: Int, val partsTotal: Int)

/** Downloaded works: queueing, progress, deleting (R4, PLAN.md 6.2). */
@Singleton
class LibraryRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dao: LibraryDao,
    private val storage: WorkStorage,
    private val catalogs: CatalogRepository,
) {
    private val workManager get() = WorkManager.getInstance(context)

    val works: Flow<List<DownloadedWork>> = dao.works()
    val totalBytes: Flow<Long> = dao.totalBytes()

    fun work(id: String): Flow<DownloadedWork?> = dao.workFlow(id)
    fun parts(id: String): Flow<List<Part>> = dao.partsFlow(id)
    fun byCatalog(wiki: String, catalogId: Long): Flow<DownloadedWork?> = dao.workByCatalogId(wiki, catalogId)

    /** Running downloads by work id. */
    val progress: Flow<Map<String, WorkProgress>> =
        workManager.getWorkInfosByTagFlow(WorkDownloadWorker.TAG).map { infos ->
            infos.filter { it.state == WorkInfo.State.RUNNING }.mapNotNull { info ->
                val id = info.tags.firstOrNull { it.startsWith(WorkDownloadWorker.WORK_TAG) }
                    ?.removePrefix(WorkDownloadWorker.WORK_TAG) ?: return@mapNotNull null
                id to WorkProgress(info.progress.getInt(WorkDownloadWorker.DONE, 0), info.progress.getInt(WorkDownloadWorker.TOTAL, 0))
            }.toMap()
        }

    /**
     * Downloads a catalog work, whole or the parts at [indexes] (R4); a work already in
     * the library gets those parts added.
     */
    suspend fun downloadCatalogWork(wiki: String, catalogId: Long, indexes: List<Int>? = null): String? = withContext(Dispatchers.IO) {
        val db = catalogs.db(wiki) ?: return@withContext null
        val w = db.work(catalogId) ?: return@withContext null
        val id = DownloadedWork.idOf(wiki, w.pageTitle)
        val inserted = dao.insertWork(
            DownloadedWork(
                id = id, wiki = wiki, domain = db.domain, pageTitle = w.pageTitle, entryTitle = w.entryTitle,
                displayTitle = w.displayTitle,
                authors = Json.encodeToString(w.authors.filter { it.role == "author" }.map { it.author.name }),
                year = w.year, language = w.language, catalogId = w.id,
                sourceUrl = WikiLinks.pageUrl(db.domain, w.entryTitle), addedAt = System.currentTimeMillis(),
                state = DownloadedWork.QUEUED, dir = storage.dirName(wiki, w.pageTitle), coverPath = w.coverUrl,
            ),
        )
        if (inserted == -1L) {
            if (indexes == null) dao.select(id, dao.parts(id).map { it.index }.distinct(), true) else dao.select(id, indexes, true)
        }
        enqueue(id, if (inserted == -1L) null else indexes)
        id
    }

    /** Downloads a page the catalog does not have (open by URL); its parts are resolved live (PLAN.md 7.1 step 2). */
    suspend fun downloadPage(wiki: String, domain: String, title: String): String = withContext(Dispatchers.IO) {
        val id = DownloadedWork.idOf(wiki, title)
        dao.insertWork(
            DownloadedWork(
                id = id, wiki = wiki, domain = domain, pageTitle = title, entryTitle = title, displayTitle = title,
                authors = "[]", year = null, language = null, catalogId = null,
                sourceUrl = WikiLinks.pageUrl(domain, title), addedAt = System.currentTimeMillis(),
                state = DownloadedWork.QUEUED, dir = storage.dirName(wiki, title),
            ),
        )
        enqueue(id, null)
        id
    }

    fun retry(id: String) = enqueue(id, null)

    private fun enqueue(id: String, indexes: List<Int>?) {
        val req = OneTimeWorkRequestBuilder<WorkDownloadWorker>()
            .setInputData(
                workDataOf(WorkDownloadWorker.WORK_ID to id, WorkDownloadWorker.INDEXES to indexes?.let { Json.encodeToString(it) }),
            )
            .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
            .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 30, TimeUnit.SECONDS)
            .addTag(WorkDownloadWorker.TAG)
            .addTag(WorkDownloadWorker.WORK_TAG + id)
            .build()
        // A request for more parts while a download runs follows it; finished parts are skipped.
        workManager.enqueueUniqueWork(WorkDownloadWorker.uniqueName(id), ExistingWorkPolicy.APPEND_OR_REPLACE, req)
    }

    suspend fun delete(id: String) = withContext(Dispatchers.IO) {
        workManager.cancelUniqueWork(WorkDownloadWorker.uniqueName(id))
        val w = dao.work(id) ?: return@withContext
        storage.delete(w)
        dao.deleteJobs(id)
        dao.deleteWork(id)
    }

    suspend fun markOpened(id: String) = withContext(Dispatchers.IO) {
        dao.work(id)?.let { dao.updateWork(it.copy(lastOpenedAt = System.currentTimeMillis())) }
    }

    suspend fun readPart(work: DownloadedWork, part: Part): String? = withContext(Dispatchers.IO) { storage.readPart(work, part) }
}
