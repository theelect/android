package com.electionapp.android.utils.binding

import android.databinding.DataBindingComponent
import com.electionapp.android.utils.imageloader.ImageLoader


class BindingComponentImpl(var imageLoader: ImageLoader) : DataBindingComponent {

    override fun getImageBindingAdapter(): ImageBindingAdapter {
        return ImageBindingAdapter(imageLoader)
    }

}