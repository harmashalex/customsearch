package com.harmashalex.customsearch.data.repository.source.local

import com.harmashalex.customsearch.data.repository.SearchDataSource
import javax.inject.Inject

class LocalSearchDataSource @Inject constructor() : SearchDataSource {
    override fun getLastSearchResult() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun search(query: String, startIndex: Int, count: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cancelRequest() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}