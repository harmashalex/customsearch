package com.harmashalex.customsearch.di

import com.harmashalex.customsearch.data.repository.SearchRepository
import com.harmashalex.customsearch.data.repository.source.local.LocalSearchDataSource
import com.harmashalex.customsearch.data.repository.source.remote.RemoteSearchDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideSearchRepository(localDataSource: LocalSearchDataSource, remoteDataSource: RemoteSearchDataSource): SearchRepository {
        return SearchRepository(localDataSource, remoteDataSource)
    }
}