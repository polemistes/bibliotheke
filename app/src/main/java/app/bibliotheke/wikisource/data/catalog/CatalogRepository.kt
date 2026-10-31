// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.data.catalog

import android.content.Context
import android.util.Log
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import app.bibliotheke.wikisource.catalog.CatalogDb
import app.bibliotheke.wikisource.catalog.CatalogDownloader
import app.bibliotheke.wikisource.catalog.CatalogStore
import app.bibliotheke.wikisource.catalog.Manifest
import app.bibliotheke.wikisource.catalog.WikiEntry
import app.bibliotheke.wikisource.data.settings.SettingsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/** A catalog download as the UI shows it. */
sealed interface DownloadState {
    data class Running(val done: Long, val total: Long) : DownloadState
    data object Queued : DownloadState
    data class Failed(val message: String?) : DownloadState
}

/** Manifest, installed catalogs and their downloads (PLAN.md 6.1). */
@Singleton
class CatalogRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val store: CatalogStore,
    private val downloader: CatalogDownloader,
    private val settings: SettingsRepository,
) {
    private val workManager get() = WorkManager.getInstance(context)
    private val _manifest = MutableStateFlow(store.cachedManifest())

    /** The last manifest fetched (cached on disk, so it is there offline). */
    val manifest: StateFlow<Manifest?> = _manifest.asStateFlow()

    /** Installed catalogs by wiki code. */
    val installed: StateFlow<Map<String, WikiEntry>> = store.installed

    /** Downloads queued, running or failed, by wiki code. */
    val downloads: Flow<Map<String, DownloadState>> =
        workManager.getWorkInfosByTagFlow(CatalogDownloadWorker.TAG).map { infos ->
            infos.mapNotNull { info ->
                val code = info.tags.firstOrNull { it.startsWith(CatalogDownloadWorker.WIKI_TAG) }
                    ?.removePrefix(CatalogDownloadWorker.WIKI_TAG) ?: return@mapNotNull null
                val state = when (info.state) {
                    WorkInfo.State.ENQUEUED, WorkInfo.State.BLOCKED -> DownloadState.Queued
                    WorkInfo.State.RUNNING -> DownloadState.Running(
                        info.progress.getLong(CatalogDownloadWorker.DONE, 0),
                        info.progress.getLong(CatalogDownloadWorker.TOTAL, 0),
                    )
                    WorkInfo.State.FAILED -> DownloadState.Failed(info.outputData.getString(CatalogDownloadWorker.ERROR))
                    else -> return@mapNotNull null
                }
                code to state
            }.toMap()
        }

    fun db(code: String): CatalogDb? = runCatching { store.db(code) }.onFailure { Log.w(TAG, "open $code", it) }.getOrNull()

    suspend fun refreshManifest(): Result<Manifest> = withContext(Dispatchers.IO) {
        runCatching { downloader.fetchManifest() }
            .onSuccess {
                _manifest.value = it
                settings.setLastManifestCheck(System.currentTimeMillis())
            }
            .onFailure { Log.w(TAG, "manifest", it) }
    }

    fun download(codes: Collection<String>) {
        for (code in codes) {
            val req = OneTimeWorkRequestBuilder<CatalogDownloadWorker>()
                .setInputData(workDataOf(CatalogDownloadWorker.CODE to code))
                .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
                .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 30, TimeUnit.SECONDS)
                .addTag(CatalogDownloadWorker.TAG)
                .addTag(CatalogDownloadWorker.WIKI_TAG + code)
                .build()
            workManager.enqueueUniqueWork("catalog-$code", ExistingWorkPolicy.KEEP, req)
        }
    }

    fun cancelDownload(code: String) {
        workManager.cancelUniqueWork("catalog-$code")
    }

    fun remove(code: String) {
        cancelDownload(code)
        store.remove(code)
    }

    /**
     * At most daily (PLAN.md 6.1): fetch the manifest and download every installed
     * catalog whose sha256 changed.
     */
    suspend fun checkForUpdates(now: Long = System.currentTimeMillis()) {
        if (now - settings.current().lastManifestCheck < TimeUnit.HOURS.toMillis(24)) return
        val m = refreshManifest().getOrNull() ?: return
        val stale = installed.value.filter { (code, have) ->
            val e = m.wiki(code)
            e != null && e.schemaVersion == have.schemaVersion && !e.sha256.equals(have.sha256, ignoreCase = true)
        }.keys
        if (stale.isNotEmpty()) download(stale)
    }

    companion object {
        private const val TAG = "CatalogRepository"
    }
}
