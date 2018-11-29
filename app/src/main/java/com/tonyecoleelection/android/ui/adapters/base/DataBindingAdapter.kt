package com.tonyecoleelection.android.ui.adapters.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup


abstract class DataBindingAdapter<T>(diffCallback: DiffUtil.ItemCallback<T>, var itemClickListener: BindableItemClickListener<T>) :
        ListAdapter<T, DataBindingViewHolder<T>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, resId(), parent, false)
        return DataBindingViewHolder(binding, itemClickListener)
    }

    abstract fun resId(): Int

    override fun onBindViewHolder(holder: DataBindingViewHolder<T>, position: Int) = holder.bind(getItem(position))
}