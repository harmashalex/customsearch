package com.harmashalex.customsearch.data.repository.source.local

import android.util.Log
import com.harmashalex.customsearch.data.entity.SearchResult
import com.harmashalex.customsearch.data.repository.source.remote.DataResponse
import com.harmashalex.customsearch.data.repository.SearchDataSource
import com.harmashalex.customsearch.data.repository.source.local.database.AppDatabase
import com.harmashalex.customsearch.data.repository.source.remote.SuccessResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LocalSearchDataSource @Inject constructor(val database: AppDatabase) : SearchDataSource {
    private val TAG = LocalSearchDataSource::class.java.simpleName

    override fun getLastSearchResult(): Flowable<DataResponse> {
        return Flowable.create<DataResponse>({
            val searchResult = database.searchDao().getLastSearchResult()
            if(searchResult.isNotEmpty()) {
                it.onNext(SuccessResponse(searchResult[0]))
            } else {
                it.onComplete()
            }
        }, BackpressureStrategy.BUFFER)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun search(query: String, startIndex: Int, count: Int): Flowable<DataResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun saveLastSearch(searchResult: SearchResult) {
        Log.d(TAG, "Save last search result: $searchResult")
        if (!searchResult.searchItems.isEmpty())
         database.searchDao().insert(searchResult)
    }
}