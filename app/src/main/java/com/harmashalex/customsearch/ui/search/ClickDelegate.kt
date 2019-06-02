package com.harmashalex.customsearch.ui.search

interface ClickDelegate {
    fun onSearchClicked(query: String)

    fun onStopSearchClicked()

    fun showLastQueryClicked()

    fun openInExternalBrowser(link: String)
}