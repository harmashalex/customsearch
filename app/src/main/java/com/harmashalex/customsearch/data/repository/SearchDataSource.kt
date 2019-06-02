package com.harmashalex.customsearch.data.repository

import com.harmashalex.customsearch.data.entity.api.DataResponse
import io.reactivex.Flowable

interface SearchDataSource {

    fun getLastSearchResult()

    fun search(query: String, startIndex: Int, count: Int): Flowable<DataResponse>
}