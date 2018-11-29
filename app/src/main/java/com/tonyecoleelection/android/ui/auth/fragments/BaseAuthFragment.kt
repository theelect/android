package com.tonyecoleelection.android.ui.auth.fragments


import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.ui.auth.BaseAuthViewModel
import com.tonyecoleelection.android.ui.base.BaseVMFragment


abstract class BaseAuthFragment<V : BaseAuthViewModel> : BaseVMFragment<V>() {


    var backButton: ImageView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backButton = view.findViewById(R.id.back_btn)

        backButton?.setOnClickListener {
            goBack()
        }
    }

    open fun goBack() {
        getViewModel().navigateBack()
    }
}
