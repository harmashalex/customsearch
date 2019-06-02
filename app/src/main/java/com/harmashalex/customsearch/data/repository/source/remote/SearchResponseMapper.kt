package com.harmashalex.customsearch.data.repository.source.remote

import com.harmashalex.customsearch.data.entity.api.response.SearchResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

fun mapSearch(searchResponseJson: String): SearchResponse {
    val moshi = Moshi.Builder().build()
    val type = Types.newParameterizedType(SearchResponse::class.java)
    val adapter: JsonAdapter<SearchResponse> = moshi.adapter(type)
    val searchResponse = adapter.fromJson(searchResponseJson)
    return searchResponse!!
}