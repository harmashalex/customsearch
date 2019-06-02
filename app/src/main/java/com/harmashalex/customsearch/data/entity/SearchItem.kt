package com.harmashalex.customsearch.data.entity

import com.squareup.moshi.Json

data class SearchItem(
    @Json(name = "title")
    val title: String,
    @Json(name = "snippet")
    val snippet: String,
    @Json(name = "link")
    val link: String)