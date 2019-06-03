package com.harmashalex.customsearch.util

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

object Utils {
    fun loadImage(url: String, imageView: ImageView) {
        Glide.with(imageView)
            .load(url)
            .into(imageView)
    }

    fun showToastMessage(context: Context, text: String) {
        val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        toast.show()
    }
}