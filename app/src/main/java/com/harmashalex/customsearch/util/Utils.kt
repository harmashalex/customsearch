package com.harmashalex.customsearch.util

import android.widget.ImageView
import com.bumptech.glide.Glide

object Utils {
    fun loadImage(url: String, imageView: ImageView) {
        Glide.with(imageView)
            .load(url)
            .into(imageView)
    }
}