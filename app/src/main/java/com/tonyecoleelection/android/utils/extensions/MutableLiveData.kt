package com.tonyecoleelection.android.utils.extensions

import android.arch.lifecycle.MutableLiveData
import com.tonyecoleelection.android.utils.common.SingleLiveData

internal fun <T> mutableLiveDataOf(): MutableLiveData<T> = MutableLiveData()
internal fun <T> singleLiveDataOf(): SingleLiveData<T> = SingleLiveData()
