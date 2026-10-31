// SPDX-License-Identifier: GPL-3.0-or-later
package app.bibliotheke.wikisource.di

import android.content.Context
import app.bibliotheke.wikisource.BuildConfig
import app.bibliotheke.wikisource.catalog.CatalogDownloader
import app.bibliotheke.wikisource.catalog.CatalogStore
import app.bibliotheke.wikisource.catalog.PoliteHttp
import app.bibliotheke.wikisource.catalog.WikiSearchApi
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
}
