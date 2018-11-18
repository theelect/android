package com.electionapp.android.utils.extensions

import android.arch.lifecycle.MutableLiveData
import com.electionapp.android.utils.common.SingleLiveData

internal fun <T> mutableLiveDataOf(): MutableLiveData<T> = MutableLiveData()
internal fun <T> singleLiveDataOf(): SingleLiveData<T> = SingleLiveData()
