package com.harmashalex.customsearch.data.repository.source.local.database

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.TypeConverters
import com.harmashalex.customsearch.data.entity.SearchResult
import com.harmashalex.customsearch.data.repository.source.local.converter.SearchItemConverter
import com.harmashalex.customsearch.data.repository.source.local.database.dao.SearchDao

@Database(entities = [SearchResult::class], version = 1)
@TypeConverters(SearchItemConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchDao(): SearchDao
}