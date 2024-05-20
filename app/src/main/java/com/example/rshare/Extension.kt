package com.example.rshare

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.rshare.data.api.RShareApi

fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(this).load(imageUrl).into(this)
}
fun ImageView.loadMovieImage(movieImagePath: String?) {
    val apiImageBaseUrl = "${RShareApi.IMAGE_BASE_URL}$movieImagePath"
    Glide.with(this).load(apiImageBaseUrl).into(this)
}