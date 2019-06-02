package com.harmashalex.customsearch.ui.search

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.harmashalex.customsearch.R
import com.harmashalex.customsearch.di.DaggerAppComponent
import javax.inject.Inject

class SearchFragment : Fragment() {
    private lateinit var searchViewModel: SearchViewModel
    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        DaggerAppComponent
            .builder()
            .application(activity!!.application)
            .build()
            .inject(this)

        searchViewModel = ViewModelProviders.of(this, viewModeFactory).get(SearchViewModel::class.java)
        searchViewModel.search("android")
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}