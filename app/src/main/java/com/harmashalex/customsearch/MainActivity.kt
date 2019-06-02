package com.harmashalex.customsearch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.harmashalex.customsearch.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, SearchFragment.newInstance(), SearchFragment::class.java.simpleName)
            .commitNow()
    }
}
