package com.tonyecoleelection.android.ui.adapters.filters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import android.widget.TextView
import com.tonyecoleelection.android.BR
import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.utils.setCheckedWithOutTrigger
import com.tonyecoleelection.android.views.sectionedadapter.SectionedViewHolder
import com.tonyecoleelection.constants.Constants
import kotlinx.android.synthetic.main.list_item_ward.view.*

class MultiSelectAdapter(var items: MutableList<String>,
                         var selectedItems: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var multiSelectVH: MultiSelectVH? = null

    fun clearSelection() {
        selectedItems.clear()
    }

    fun addData(list: List<String>) {
        items.clear()
        items.addAll(list)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return MultiSelectVH.create(p0, this)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, position: Int) {
//        var isSelected: Boolean = if (position == 0) {
//            multiSelectVH = (p0 as MultiSelectVH)
//            !selectedItems.isEmpty()
//        } else {
//            getIsItemSelected(items[position])
//        }

        (p0 as MultiSelectVH).bind(items[position], getIsItemSelected(items[position]))
    }

    private fun getIsItemSelected(name: String): Boolean {
        return selectedItems.contains(name)
    }

    class MultiSelectVH(itemView: View, val adapter: MultiSelectAdapter, val binding: ViewDataBinding) : SectionedViewHolder(itemView) {

        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
        val title: TextView = itemView.findViewById(R.id.title)
        var name: String? = null

        val checkListener = OnCheckedChangeListener { compoundButton, b ->
            if (b) {
                adapter.addVHToCheckedList(name!!)
            } else {
                adapter.removeVHFromCheckedList(name!!)
            }
        }

        fun bind(name: String, isChecked: Boolean) {
            this.name = name
            checkBox.setOnCheckedChangeListener(null)
            binding.setVariable(BR.item, name)
            binding.setVariable(BR.checked, isChecked)
            checkBox.setOnCheckedChangeListener(checkListener)
            binding.executePendingBindings()
        }


        fun setCheckedIfTitleIsAll(checked: Boolean) {
            if (this.title.text == Constants.FILTER_CONSTANTS.ALL) {
                checkBox.isChecked = checked
            }
        }

        companion object {
            fun create(parent: ViewGroup, adapter: MultiSelectAdapter): MultiSelectVH {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, R.layout.item_multiselect_adapter, parent, false)
                return MultiSelectVH(binding.root, adapter, binding)
            }
        }
    }


    private fun addVHToCheckedList(name: String) {
//
//        if (name == Constants.FILTER_CONSTANTS.ALL) {
//            selectedItems.clear()
//        }else{
//
//        }
//
//        if (!selectedItems.isEmpty()) {
//            notifyItemChanged(1, items.lastIndex)
//        }

        if (!selectedItems.contains(name)) {
            selectedItems.add(name)
        }
    }

    private fun removeVHFromCheckedList(name: String) {
        selectedItems.add(name)

//        if (selectedItems.isEmpty()) {
//            notifyItemChanged(0)
//        }
    }

}