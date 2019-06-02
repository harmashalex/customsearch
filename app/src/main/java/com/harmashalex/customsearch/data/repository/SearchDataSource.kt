package com.harmashalex.customsearch.data.repository

interface SearchDataSource {

    fun getLastSearchResult()

    fun search(query: String, startIndex: Int, count: Int)

    fun cancelRequest()
}