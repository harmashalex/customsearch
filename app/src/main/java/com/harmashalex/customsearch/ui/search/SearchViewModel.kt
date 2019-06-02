package com.harmashalex.customsearch.ui.search

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.harmashalex.customsearch.data.repository.source.remote.DataResponse
import com.harmashalex.customsearch.data.repository.SearchRepository
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class SearchViewModel
@Inject constructor(val searchRepository: SearchRepository) : ViewModel() {
    private val startIndex = 1
    private val count = 10

    val searchLiveData = MutableLiveData<DataResponse>()
    var searchDisposable: Disposable? = null

    fun search(query: String) {
        searchDisposable = searchRepository.search(query, startIndex, count).subscribe {
            searchLiveData.value = it
            cancelSearch()
        }
    }

    fun getLastSearchResult() {
        searchDisposable = searchRepository.getLastSearchResult().subscribe{
            searchLiveData.value = it
            cancelSearch()
        }
    }

    fun cancelSearch() {
        searchDisposable?.let { if (!it.isDisposed) it.dispose()}
    }
}