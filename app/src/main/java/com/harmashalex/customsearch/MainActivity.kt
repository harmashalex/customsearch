package com.harmashalex.customsearch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.harmashalex.customsearch.ui.search.SearchFragment
import io.reactivex.plugins.RxJavaPlugins



class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RxJavaPlugins.setErrorHandler { throwable -> Log.d(TAG, throwable.message) }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, SearchFragment.newInstance(), SearchFragment::class.java.simpleName)
            .commitNow()
    }
}
