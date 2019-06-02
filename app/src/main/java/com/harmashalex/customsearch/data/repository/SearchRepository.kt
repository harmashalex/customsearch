package com.harmashalex.customsearch.data.repository

import com.harmashalex.customsearch.data.entity.SearchResult
import com.harmashalex.customsearch.data.repository.source.remote.DataResponse
import com.harmashalex.customsearch.data.repository.source.local.LocalSearchDataSource
import com.harmashalex.customsearch.data.repository.source.remote.RemoteSearchDataSource
import com.harmashalex.customsearch.data.repository.source.remote.SuccessResponse
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchRepository @Inject constructor(val localDataSource: LocalSearchDataSource, val remoteDataSource: RemoteSearchDataSource): SearchDataSource {
    override fun getLastSearchResult(): Flowable<DataResponse> {
        return localDataSource.getLastSearchResult().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun search(query: String, startIndex: Int, count: Int): Flowable<DataResponse> {
        return remoteDataSource.search(query, startIndex, count).doOnNext {
            when (it) {
                is SuccessResponse<*> -> localDataSource.saveLastSearch(it.data as SearchResult)
            }
        }
    }
}