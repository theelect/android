package com.electionapp.android.utils.extensions

import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import com.electionapp.android.utils.common.ActivityBindingProperty


internal fun <T : ViewDataBinding> activityBinding(@LayoutRes resId: Int) = ActivityBindingProperty<T>(resId)
