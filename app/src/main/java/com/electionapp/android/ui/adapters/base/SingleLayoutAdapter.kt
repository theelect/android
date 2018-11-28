package com.electionapp.android.ui.adapters.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.electionapp.android.BR
import com.electionapp.android.utils.common.DiffUtilCallback
import com.electionapp.android.utils.extensions.inflater

import kotlin.properties.Delegates.observable

class SingleLayoutAdapter<T>(@LayoutRes private val resId: Int, var onItemClickListener: BindableItemClickListener<T>? = null) :
        RecyclerView.Adapter<SingleLayoutAdapter.ViewHolder<T>>(), BindableAdapter<T> {

    override fun setData(data: T) {
        if (data is List<*>) {
            items = data as List<T>
        }
    }

    var items by observable<List<T>>(listOf()) { _, oldValue, newValue ->
        DiffUtil
                .calculateDiff(DiffUtilCallback(oldValue, newValue))
                .dispatchUpdatesTo(this)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) = holder.bind(items[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder<T>(parent.inflate(), onItemClickListener)

    private fun ViewGroup.inflate() = DataBindingUtil.inflate<ViewDataBinding>(inflater, resId, this, false)

    class ViewHolder<T>(private val binding: ViewDataBinding, var onItemClickListener: BindableItemClickListener<T>?) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T) = with(binding) {
            itemView.setOnClickListener {
                onItemClickListener?.onItemClicked(item)
            }
            setVariable(BR.item, item)
            executePendingBindings()
        }
    }

}
