package com.tonyecoleelection.android.ui.adapters.base

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

class DataBindingViewHolder<T>(private val binding: ViewDataBinding, var itemClickListener: BindableItemClickListener<T>) :
        RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T) {
//        binding.setVariable(BR.item, item)
//        binding.setVariable(BR.clickListener, itemClickListener)
        binding.executePendingBindings()
    }
}