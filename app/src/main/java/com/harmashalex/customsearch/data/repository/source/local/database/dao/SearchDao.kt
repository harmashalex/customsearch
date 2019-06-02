package com.harmashalex.customsearch.data.repository.source.local.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.harmashalex.customsearch.data.entity.SearchResult

@Dao
interface SearchDao {
    @Query("SELECT * FROM SearchResultTable ORDER BY requestTimeInMillis DESC LIMIT 1")
    fun getLastSearchResult(): List<SearchResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searchResult: SearchResult)
}