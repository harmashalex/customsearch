package com.harmashalex.customsearch.data.repository

import android.arch.lifecycle.LiveData
import com.harmashalex.customsearch.data.entity.SearchResult
import com.harmashalex.customsearch.data.entity.api.DataResponse
import com.harmashalex.customsearch.data.entity.api.SuccessResponse
import com.harmashalex.customsearch.data.repository.source.local.LocalSearchDataSource
import com.harmashalex.customsearch.data.repository.source.remote.RemoteSearchDataSource
import io.reactivex.Flowable
import javax.inject.Inject

class SearchRepository @Inject constructor(val localDataSource: LocalSearchDataSource, val remoteDataSource: RemoteSearchDataSource): SearchDataSource {
    override fun getLastSearchResult() {
    }

    override fun search(query: String, startIndex: Int, count: Int): Flowable<DataResponse> {
        return remoteDataSource.search(query, startIndex, count)/*.also {
            when (it) {
                is SuccessResponse<*> -> localDataSource.saveLastSearch(it.data as SearchResult)
            }
        }*/
    }
}