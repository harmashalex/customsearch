package com.harmashalex.customsearch.ui.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.harmashalex.customsearch.R
import com.harmashalex.customsearch.data.entity.SearchItem
import com.harmashalex.customsearch.data.entity.SearchResult
import com.harmashalex.customsearch.data.repository.source.remote.ErrorResponse
import com.harmashalex.customsearch.data.repository.source.remote.SuccessResponse
import com.harmashalex.customsearch.di.DaggerAppComponent
import com.harmashalex.customsearch.util.Utils
import javax.inject.Inject

class SearchFragment : Fragment(), ClickDelegate {
    private val TAG = SearchFragment::class.java.simpleName
    private lateinit var searchViewModel: SearchViewModel
    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory
    private lateinit var searchRecyclerView: RecyclerView
    private lateinit var viewAdapter: SearchAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        DaggerAppComponent
            .builder()
            .application(activity!!.application)
            .build()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        searchRecyclerView = inflater.inflate(R.layout.fragment_search, container, false) as RecyclerView
        viewManager = LinearLayoutManager(context)
        viewAdapter = SearchAdapter(this)

        searchRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        searchViewModel = ViewModelProviders.of(this, viewModeFactory).get(SearchViewModel::class.java)
        searchViewModel.searchLiveData.observe(this, Observer {
            when(it) {
                is SuccessResponse<*> -> onSuccessSearch((it.data as SearchResult).searchItems)
                is ErrorResponse -> Log.e(TAG, "Error code=${it.errorCode} , message=${it.message}")
            }
        })

        return searchRecyclerView
    }

    private fun onSuccessSearch(searchItems: List<SearchItem>) {
        addItemsToList(searchItems)
        if (searchViewModel.canContinueSearch()) {
            searchViewModel.searchNext()
        }
    }

    private fun addItemsToList(searchItems: List<SearchItem>) {
        if (searchItems.isEmpty()) {
            Utils.showToastMessage(context!!, "Nothing to show")
        } else {
            viewAdapter.searchItems.addAll(searchItems)
            viewAdapter.notifyDataSetChanged()
        }
    }

    override fun onSearchClicked(query: String) {
        clearList()
        if (!TextUtils.isEmpty(query)) {
            searchViewModel.search(query)
        } else {
            Utils.showToastMessage(context!!, "Enter your query")
        }
    }

    private fun clearList() {
        viewAdapter.searchItems.clear()
        viewAdapter.notifyDataSetChanged()
    }

    override fun onStopSearchClicked() {
        searchViewModel.cancelSearch()
    }

    override fun showLastQueryClicked() {
        clearList()
        searchViewModel.getLastSearchResult()
    }

    override fun openInExternalBrowser(link: String) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(link)
        startActivity(openURL)
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}