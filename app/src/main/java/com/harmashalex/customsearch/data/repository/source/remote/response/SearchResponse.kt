package com.harmashalex.customsearch.data.repository.source.remote.response

import com.harmashalex.customsearch.data.entity.SearchItem
import com.squareup.moshi.Json

data class SearchResponse (@Json(name = "items") val items: List<SearchItem>?)
