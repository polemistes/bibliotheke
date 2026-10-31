// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.data.catalog

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import app.bibliotheke.wikisource.catalog.CatalogDownloader
import app.bibliotheke.wikisource.catalog.CatalogStore
import app.bibliotheke.wikisource.catalog.PoliteHttp
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.io.IOException

/** Downloads one wiki's catalog in the background; resumes from the partial file on retry. */
@HiltWorker
class CatalogDownloadWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val store: CatalogStore,
    private val downloader: CatalogDownloader,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val code = inputData.getString(CODE) ?: return Result.failure()
        return try {
            val entry = (store.cachedManifest()?.wiki(code) ?: downloader.fetchManifest().wiki(code))
                ?: return Result.failure(workDataOf(ERROR to "$code is not in the manifest"))
            var lastReported = -1L
            downloader.download(entry) { done, total ->
                // Report about every 1 %, not on every buffer.
                if (done - lastReported >= total / 100 || done == total) {
                    lastReported = done
                    setProgressAsync(workDataOf(DONE to done, TOTAL to total))
                }
            }
            Result.success()
        } catch (e: IOException) {
            Log.w(TAG, "download $code", e)
            // The catalog may have been replaced since the manifest was cached (404, 416,
            // sha256 mismatch): the next try works from a fresh manifest.
            runCatching { downloader.fetchManifest() }
            val permanent = e is PoliteHttp.HttpException && e.code in 400..499 && e.code != 404 && e.code != 416
            if (!permanent && runAttemptCount < 4) Result.retry() else Result.failure(workDataOf(ERROR to e.message))
        }
    }

    companion object {
        const val TAG = "catalog-download"
        const val WIKI_TAG = "wiki:"
        const val CODE = "code"
        const val DONE = "done"
        const val TOTAL = "total"
        const val ERROR = "error"
    }
}
