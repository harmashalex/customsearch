package com.harmashalex.customsearch.ui.search

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.harmashalex.customsearch.data.entity.SearchResult
import com.harmashalex.customsearch.data.repository.source.remote.DataResponse
import com.harmashalex.customsearch.data.repository.SearchRepository
import com.harmashalex.customsearch.data.repository.source.remote.SuccessResponse
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class SearchViewModel
@Inject constructor(val searchRepository: SearchRepository) : ViewModel() {
    //Limitation from Google Custom search api. 10 - max search result per single request
    private val count = 10
    //Limitation from Google Custom search api. 100 - max search result per single query
    private val maxIndex = 80

    private var startIndex = 1
    private var cancelSearch = false
    private var canContinueSearch = true
    private lateinit var currentQuery: String

    val searchLiveData = MutableLiveData<DataResponse>()
    var searchDisposable: Disposable? = null

    fun search(query: String) {
        cancelSearch = false
        canContinueSearch = true
        startIndex = 1
        currentQuery = query
        searchNext()
    }

     fun searchNext() {
        dispose()
        searchDisposable = searchRepository.search(currentQuery, startIndex, count).subscribe {
            searchLiveData.value = it
            startIndex = count.times(2).plus(startIndex)
            if (it is SuccessResponse<*>) {
                canContinueSearch = (it.data as SearchResult).searchItems.size == count.times(2) && (startIndex < maxIndex) && !cancelSearch
            }
        }
    }

    fun canContinueSearch(): Boolean {
        return canContinueSearch
    }

    fun getLastSearchResult() {
        dispose()
        searchDisposable = searchRepository.getLastSearchResult().subscribe{
            searchLiveData.value = it
        }
    }

    fun cancelSearch() {
        cancelSearch = true
        if (startIndex > 20) {
            dispose()
        }
    }

    private fun dispose() {
        searchDisposable?.let { if (!it.isDisposed) it.dispose()}
    }
}