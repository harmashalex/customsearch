package com.harmashalex.customsearch.data.repository.source.remote

import android.util.Log
import com.harmashalex.customsearch.data.entity.SearchItem
import com.harmashalex.customsearch.data.entity.SearchResult
import com.harmashalex.customsearch.data.entity.exception.RequestException
import com.harmashalex.customsearch.data.repository.SearchDataSource
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import okhttp3.OkHttpClient
import okhttp3.Request

class RemoteSearchDataSource @Inject constructor(val searchEngine: SearchEngine) : SearchDataSource {
    private val TAG = RemoteSearchDataSource::class.java.simpleName
    private val client = OkHttpClient()

    override fun getLastSearchResult(): Flowable<DataResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun search(query: String, startIndex: Int, count: Int): Flowable<DataResponse> {
        return Flowable.zip(makeRequestFlowable(query, startIndex, count), makeRequestFlowable(query, startIndex + count, count),
            BiFunction<String, String, Pair<String, String>>({ t1, t2 -> Pair(t1, t2) }))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap{
                val mergedSearchItemList = arrayListOf<SearchItem>()
                val firstSearchItemsList = mapSearch(it.first)?.items
                val secondSearchItemsList = mapSearch(it.second)?.items
                firstSearchItemsList?.let { mergedSearchItemList.addAll(it) }
                secondSearchItemsList?.let { mergedSearchItemList.addAll(it) }
                Flowable.just(
                    SuccessResponse(
                        SearchResult(
                            query,
                            mergedSearchItemList
                        )
                    )
                )
            }
    }

    private fun makeRequestFlowable(query: String, startIndex: Int, count: Int): Flowable<String> {
        return Flowable.fromCallable { makeRequest(query, startIndex, count) }
            .subscribeOn(Schedulers.io())
            .onErrorResumeNext(Flowable.just(""))
    }

    private fun makeRequest(query: String, startIndex: Int, count: Int): String {
        val url = searchEngine.buildUrl(query, startIndex, count).toString()
        Log.d(TAG, "Request url: $url")

        val request = Request.Builder()
            .url(url)
            .build()

        val response = client.newCall(request).execute()

        if (response.isSuccessful) {
            val responseJson = response.body?.string().toString()
            Log.d(TAG, "Response: $responseJson")
            return responseJson
        } else {
            throw RequestException(response.code, response.message)
        }
    }
}