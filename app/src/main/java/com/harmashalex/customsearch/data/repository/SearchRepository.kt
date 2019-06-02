package com.harmashalex.customsearch.data.repository

import com.harmashalex.customsearch.data.repository.source.local.LocalSearchDataSource
import com.harmashalex.customsearch.data.repository.source.remote.RemoteSearchDataSource
import javax.inject.Inject

class SearchRepository @Inject constructor(val localDataSource: LocalSearchDataSource, val remoteDataSource: RemoteSearchDataSource): SearchDataSource {
    override fun getLastSearchResult() {
    }

    override fun search(query: String, startIndex: Int, count: Int) {
        remoteDataSource.search(query, startIndex, count)
    }

    override fun cancelRequest() {
    }
}