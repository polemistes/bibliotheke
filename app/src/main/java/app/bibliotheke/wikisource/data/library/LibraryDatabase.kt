// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.data.library

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * A downloaded (or downloading) work (PLAN.md 6.2; WS§7.2 adds [structure] and
 * [entryTitle]). [id] is `{wiki}:{page_title}`.
 */
@Entity(tableName = "downloaded_work")
data class DownloadedWork(
    @PrimaryKey val id: String,
    val wiki: String,
    val domain: String,
    val pageTitle: String,
    val entryTitle: String,
    val displayTitle: String,
    /** JSON list of author names. */
    val authors: String,
    val year: Int?,
    val language: String?,
    /** The catalog work id, when the work came from the catalog. */
    val catalogId: Long?,
    val licence: String? = null,
    val licenceUrl: String? = null,
    val sourceUrl: String,
    val coverPath: String? = null,
    val addedAt: Long,
    val lastOpenedAt: Long? = null,
    val totalChars: Long = 0,
    /** queued | downloading | ready | failed | partial */
    val state: String,
    /** single | subpages | linked | mixed | virtual */
    val structure: String? = null,
    /** Where the part tree came from: catalog | live (PLAN.md 7.1). */
    val resolvedFrom: String? = null,
    val missingParts: Int = 0,
    /** Directory under `files/works/`. */
    val dir: String,
    val bytesOnDisk: Long = 0,
    val error: String? = null,
) {
    companion object {
        const val QUEUED = "queued"
        const val DOWNLOADING = "downloading"
        const val READY = "ready"
        const val FAILED = "failed"
        const val PARTIAL = "partial"

        fun idOf(wiki: String, pageTitle: String) = "$wiki:$pageTitle"
    }
}

/**
 * One part (PLAN.md 6.2, WS§7.2): [index] is the position in the resolved tree, [sub]
 * the synthetic slice of an oversized page (0 otherwise, PLAN.md 7.1 step 6).
 */
@Entity(
    tableName = "part",
    foreignKeys = [ForeignKey(DownloadedWork::class, ["id"], ["workId"], onDelete = ForeignKey.CASCADE)],
    indices = [Index(value = ["workId", "index", "sub"], unique = true)],
)
data class Part(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val workId: String,
    val index: Int,
    val sub: Int = 0,
    val title: String,
    val pageTitle: String,
    val sourceUrl: String,
    /** volume | chapter | text | front | toc | synthetic */
    val kind: String,
    val depth: Int,
    val parentIndex: Int?,
    /** For synthetic parts: the first element id (PLAN.md 7.1 step 6). */
    val anchor: String? = null,
    /** Page labels of a synthetic part cut at page markers ("Pages 1–40"). */
    val pagesFrom: String? = null,
    val pagesTo: String? = null,
    /** Relative to the work's directory. */
    val filePath: String? = null,
    val charCount: Int = 0,
    /** pending | ready | failed | skipped (not selected in a partial download, or a toc part) */
    val state: String = PENDING,
    /** Whether this part is wanted (R4: whole work or selected chapters). */
    val selected: Boolean = true,
    val revId: Long? = null,
    val bytes: Long = 0,
    val error: String? = null,
) {
    companion object {
        const val PENDING = "pending"
        const val READY = "ready"
        const val FAILED = "failed"
        const val SKIPPED = "skipped"
    }
}

/** Last reading position per work (PLAN.md 7.4); the reader (WP4) writes it. */
@Entity(tableName = "reading_position")
data class ReadingPosition(
    @PrimaryKey val workId: String,
    val partId: Long,
    val charOffset: Int,
    val progress: Double,
    val updatedAt: Long,
)

@Entity(tableName = "bookmark", indices = [Index("workId")])
data class Bookmark(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val workId: String,
    val partId: Long,
    val charOffset: Int,
    val excerpt: String,
    val note: String?,
    val createdAt: Long,
)

/** A download request as the queue shows it (PLAN.md 6.2); WorkManager runs it. */
@Entity(tableName = "download_job", indices = [Index("workId")])
data class DownloadJob(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val workId: String,
    /** full | parts */
    val kind: String,
    /** JSON list of part indexes for `parts`. */
    val partIds: String?,
    /** queued | running | done | failed */
    val state: String,
    val bytesDone: Long = 0,
    val bytesTotal: Long = 0,
    val partsDone: Int = 0,
    val partsTotal: Int = 0,
    val error: String? = null,
    val createdAt: Long,
)

@Dao
interface LibraryDao {
    @Query("SELECT * FROM downloaded_work ORDER BY coalesce(lastOpenedAt, addedAt) DESC")
    fun works(): Flow<List<DownloadedWork>>

    @Query("SELECT * FROM downloaded_work WHERE id = :id")
    fun workFlow(id: String): Flow<DownloadedWork?>

    @Query("SELECT * FROM downloaded_work WHERE id = :id")
    suspend fun work(id: String): DownloadedWork?

    @Query("SELECT * FROM downloaded_work WHERE wiki = :wiki AND catalogId = :catalogId")
    fun workByCatalogId(wiki: String, catalogId: Long): Flow<DownloadedWork?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWork(work: DownloadedWork): Long

    @Update
    suspend fun updateWork(work: DownloadedWork)

    @Query("UPDATE downloaded_work SET state = :state, error = :error WHERE id = :id")
    suspend fun setWorkState(id: String, state: String, error: String? = null)

    @Query("DELETE FROM downloaded_work WHERE id = :id")
    suspend fun deleteWork(id: String)

    @Query("SELECT * FROM part WHERE workId = :workId ORDER BY `index`, sub")
    fun partsFlow(workId: String): Flow<List<Part>>

    @Query("SELECT * FROM part WHERE workId = :workId ORDER BY `index`, sub")
    suspend fun parts(workId: String): List<Part>

    @Insert
    suspend fun insertParts(parts: List<Part>)

    @Update
    suspend fun updatePart(part: Part)

    @Query("DELETE FROM part WHERE workId = :workId AND `index` = :index AND sub > 0")
    suspend fun deleteSlices(workId: String, index: Int)

    @Query("UPDATE part SET selected = :selected, state = CASE WHEN state = 'skipped' AND :selected THEN 'pending' ELSE state END WHERE workId = :workId AND `index` IN (:indexes)")
    suspend fun select(workId: String, indexes: List<Int>, selected: Boolean)

    /** Replaces one page's row by its synthetic slices (PLAN.md 7.1 step 6). */
    @Transaction
    suspend fun replaceWithSlices(first: Part, rest: List<Part>) {
        deleteSlices(first.workId, first.index)
        updatePart(first)
        if (rest.isNotEmpty()) insertParts(rest)
    }

    @Insert
    suspend fun insertJob(job: DownloadJob): Long

    @Update
    suspend fun updateJob(job: DownloadJob)

    @Query("SELECT * FROM download_job WHERE id = :id")
    suspend fun job(id: Long): DownloadJob?

    @Query("SELECT * FROM download_job WHERE state IN ('queued', 'running', 'failed') ORDER BY createdAt")
    fun openJobs(): Flow<List<DownloadJob>>

    @Query("DELETE FROM download_job WHERE workId = :workId")
    suspend fun deleteJobs(workId: String)

    @Query("SELECT coalesce(sum(bytesOnDisk), 0) FROM downloaded_work")
    fun totalBytes(): Flow<Long>
}

@Database(
    entities = [DownloadedWork::class, Part::class, ReadingPosition::class, Bookmark::class, DownloadJob::class],
    version = 1,
    exportSchema = true,
)
abstract class LibraryDatabase : RoomDatabase() {
    abstract fun dao(): LibraryDao
}
