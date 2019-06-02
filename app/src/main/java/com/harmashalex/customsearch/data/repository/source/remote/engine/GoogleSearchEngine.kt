package com.harmashalex.customsearch.data.repository.source.remote.engine

import android.net.Uri
import android.util.Log
import com.harmashalex.customsearch.data.repository.source.remote.SearchEngine
import javax.inject.Inject

class GoogleSearchEngine
@Inject constructor(val baseUrl: String, val version: String, val searchEngineId: String, val apiKey: String) : SearchEngine {

    override fun buildUrl(query: String, startIndex: Int, count: Int): Uri {
        val uri = Uri.parse(baseUrl)
            .buildUpon()
            .appendPath(version)
            .appendQueryParameter("key", apiKey)
            .appendQueryParameter("cx", searchEngineId)
            .appendQueryParameter("start", startIndex.toString())
            .appendQueryParameter("num", count.toString())
            .appendQueryParameter("q", query.replace(" ", "+"))
            .appendQueryParameter("fields", FIELDS)
            .build()
        Log.d(GoogleSearchEngine::class.java.simpleName, "Base url: $uri")
        return uri
    }

    companion object {
        private val FIELDS = "items(title,link,snippet),queries(request(startIndex))"
    }
}