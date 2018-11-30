package com.tonyecoleelection.android.ui.adapters.base

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import com.tonyecoleelection.android.BR

class DataBindingViewHolder<T>(private val binding: ViewDataBinding, var itemClickListener: BindableItemClickListener<T>) :
        RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T) {
        itemView.setOnClickListener {
            itemClickListener?.onItemClicked(item)
        }
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }
}