// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.di

import android.content.Context
import app.bibliotheke.wikisource.BuildConfig
import app.bibliotheke.wikisource.catalog.CatalogDownloader
import app.bibliotheke.wikisource.catalog.CatalogStore
import app.bibliotheke.wikisource.catalog.PoliteHttp
import app.bibliotheke.wikisource.catalog.WikiSearchApi
import androidx.room.Room
import app.bibliotheke.wikisource.content.ContentApi
import app.bibliotheke.wikisource.data.library.LibraryDao
import app.bibliotheke.wikisource.data.library.LibraryDatabase
import app.bibliotheke.wikisource.data.library.WorkStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides @Singleton
    fun okHttp(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    @Provides @Singleton
    fun politeHttp(client: OkHttpClient): PoliteHttp = PoliteHttp(client, PoliteHttp.userAgent(BuildConfig.VERSION_NAME))

    @Provides @Singleton
    fun catalogStore(@ApplicationContext context: Context): CatalogStore = CatalogStore(File(context.filesDir, "catalogs"))

    @Provides @Singleton
    fun catalogDownloader(http: PoliteHttp, store: CatalogStore): CatalogDownloader =
        CatalogDownloader(http, BuildConfig.CATALOG_BASE_URL, store)

    @Provides @Singleton
    fun wikiSearchApi(http: PoliteHttp): WikiSearchApi = WikiSearchApi(http)

    @Provides @Singleton
    fun contentApi(http: PoliteHttp): ContentApi = ContentApi(http)

    @Provides @Singleton
    fun libraryDatabase(@ApplicationContext context: Context): LibraryDatabase =
        Room.databaseBuilder(context, LibraryDatabase::class.java, "library.db")
            .addMigrations(LibraryDatabase.MIGRATION_1_2)
            .build()

    @Provides
    fun libraryDao(db: LibraryDatabase): LibraryDao = db.dao()

    @Provides @Singleton
    fun workStorage(@ApplicationContext context: Context): WorkStorage = WorkStorage(File(context.filesDir, "works"))
}
