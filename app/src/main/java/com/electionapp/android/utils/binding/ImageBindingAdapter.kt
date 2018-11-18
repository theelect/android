package com.electionapp.android.utils.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.electionapp.android.utils.imageloader.ImageLoader


class ImageBindingAdapter(private val imageLoader: ImageLoader) {

    @BindingAdapter("bind:imageUrl")
    fun loadImage(view: ImageView, imageUrl: String) {
        imageLoader.load(url = imageUrl, imageView = view, fadeEffect = true)
    }

    @BindingAdapter("bind:imageUrlWithCurve")
    fun loadImageWithCurve(view: ImageView, imageUrl: String) {
        imageLoader.load(url = imageUrl, imageView = view, curvature = 20)
    }

    @BindingAdapter("bind:resID")
    fun loadImage(view: ImageView, imageRes: Int) {
        imageLoader.load(resID = imageRes, imageView = view)
    }

}