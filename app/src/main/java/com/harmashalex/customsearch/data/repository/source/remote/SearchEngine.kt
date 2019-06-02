package com.harmashalex.customsearch.data.repository.source.remote

import android.net.Uri

interface SearchEngine {
    fun buildUrl(query: String, startIndex: Int, count: Int): Uri

    companion object {
        val URL = "searchengine.url"
        val VERSION = "searchengine.v"
        val ENGINE_ID = "searchengine.id"
        val API_KEY = "api.key"
    }
}