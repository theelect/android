package com.tonyecoleelection.android.ui.adapters.base


interface BindableAdapter<T> {
    fun setData(data: T)
    fun reloadDataSet(data: T)
}

