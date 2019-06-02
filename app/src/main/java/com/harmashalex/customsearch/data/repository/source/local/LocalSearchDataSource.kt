package com.harmashalex.customsearch.data.repository.source.local

import android.arch.lifecycle.LiveData
import android.util.Log
import com.harmashalex.customsearch.data.entity.SearchResult
import com.harmashalex.customsearch.data.entity.api.DataResponse
import com.harmashalex.customsearch.data.repository.SearchDataSource
import io.reactivex.Flowable
import javax.inject.Inject

class LocalSearchDataSource @Inject constructor() : SearchDataSource {
    private val TAG = LocalSearchDataSource::class.java.simpleName

    override fun getLastSearchResult() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun search(query: String, startIndex: Int, count: Int): Flowable<DataResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun saveLastSearch(searchResult: SearchResult) {
        Log.d(TAG, "Save last search result: $searchResult")
    }
}