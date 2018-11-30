package com.tonyecoleelection.android.ui.adapters.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.tonyecoleelection.android.BR
import com.tonyecoleelection.android.utils.common.DiffUtilCallback
import com.tonyecoleelection.android.utils.extensions.inflater

class SingleLayoutAdapter<T>(@LayoutRes private val resId: Int, var onItemClickListener: BindableItemClickListener<T>? = null) :
        RecyclerView.Adapter<SingleLayoutAdapter.ViewHolder<T>>(), BindableAdapter<T> {

    override fun setData(data: T) {
        if (data is List<*>) {
          //  items = data as List<T>
            val diffCallback = DiffUtilCallback(this.items, data)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            data.forEach {
                if(!items.contains(it)){
                    items.add(it as T)
                }
            }
            diffResult.dispatchUpdatesTo(this)
        }
    }


    fun clearData() {
        items.clear()
        notifyDataSetChanged()
    }

    var items = mutableListOf<T>()

//    var items by observable<List<T>>(mutableListOf()) { _, oldValue, newValue ->
//        DiffUtil
//                .calculateDiff(DiffUtilCallback(oldValue, newValue))
//                .dispatchUpdatesTo(this)
//    }

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
