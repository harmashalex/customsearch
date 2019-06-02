package com.harmashalex.customsearch.data.repository

import com.harmashalex.customsearch.data.repository.source.remote.DataResponse
import io.reactivex.Flowable

interface SearchDataSource {

    fun getLastSearchResult(): Flowable<DataResponse>

    fun search(query: String, startIndex: Int, count: Int): Flowable<DataResponse>
}