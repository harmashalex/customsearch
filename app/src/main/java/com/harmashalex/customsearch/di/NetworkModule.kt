package com.harmashalex.customsearch.di

import android.app.Application
import com.harmashalex.customsearch.data.repository.source.remote.SearchEngine
import com.harmashalex.customsearch.data.repository.source.remote.engine.GoogleSearchEngine
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import java.util.*

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideSearchEngine(application: Application): SearchEngine {
        val properties = Properties()
        val assetManager = application.assets
        val inputStream = assetManager.open("configuration.properties")
        properties.load(inputStream)

        val url = properties.getProperty(SearchEngine.URL)
        val version = properties.getProperty(SearchEngine.VERSION)
        val searchEngineId = properties.getProperty(SearchEngine.ENGINE_ID)
        val apiKey = properties.getProperty(SearchEngine.API_KEY)
        return GoogleSearchEngine(url, version, searchEngineId, apiKey)
    }
}