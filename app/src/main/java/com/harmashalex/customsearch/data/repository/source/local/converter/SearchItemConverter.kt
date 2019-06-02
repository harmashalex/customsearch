package com.harmashalex.customsearch.data.repository.source.local.converter

import android.arch.persistence.room.TypeConverter
import com.harmashalex.customsearch.data.entity.SearchItem
import com.squareup.moshi.JsonAdapter
import java.util.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class SearchItemConverter {
    var moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromJsonToSearchItemsList(searchItemsJson: String?): List<SearchItem> {
        if (searchItemsJson == null) {
            return Collections.emptyList()
        }
        val listType = Types.newParameterizedType(List::class.java, SearchItem::class.java)
        val adapter: JsonAdapter<List<SearchItem>> = moshi.adapter(listType)
        return adapter.fromJson(searchItemsJson)!!
    }

    @TypeConverter
    fun fromSearchItemsListToJson(searchItems: List<SearchItem>): String {
        val listType = Types.newParameterizedType(List::class.java, SearchItem::class.java)
        val adapter: JsonAdapter<List<SearchItem>> = moshi.adapter(listType)
        return adapter.toJson(searchItems).toString()
    }
}