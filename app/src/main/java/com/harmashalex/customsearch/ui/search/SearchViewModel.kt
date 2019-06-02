package com.harmashalex.customsearch.ui.search

import android.arch.lifecycle.ViewModel
import com.harmashalex.customsearch.data.repository.SearchRepository
import javax.inject.Inject

class SearchViewModel
@Inject constructor(val searchRepository: SearchRepository) : ViewModel() {
    fun search(query: String) {
        searchRepository.search(query, 10, 10)
    }
}