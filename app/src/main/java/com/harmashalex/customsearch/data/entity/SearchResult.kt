package com.harmashalex.customsearch.data.entity

import android.arch.persistence.room.*
import java.util.*

@Entity(tableName = "SearchResultTable")
data class SearchResult(
    @PrimaryKey
    val searchRequest: String,
    val searchItems: List<SearchItem>,
    val requestTimeInMillis: Long = Calendar.getInstance().timeInMillis)