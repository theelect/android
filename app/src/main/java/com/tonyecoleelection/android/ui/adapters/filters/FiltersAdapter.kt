package com.tonyecoleelection.android.ui.adapters.filters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import com.tonyecoleelection.android.BR
import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.model.locale.LGA
import com.tonyecoleelection.android.model.locale.Ward
import com.tonyecoleelection.android.views.sectionedadapter.SectionedRecyclerViewAdapter
import com.tonyecoleelection.android.views.sectionedadapter.SectionedViewHolder
import com.tonyecoleelection.domain.base.Params

class FiltersAdapter(val params: Params,
                     val checkedLGANameList: MutableList<String>,
                     val checkedWardNameList: MutableList<String>) : SectionedRecyclerViewAdapter<SectionedViewHolder>() {

    val list: MutableList<LGA> = mutableListOf()


    fun getCheckedLGAs(): MutableList<String> {
        return checkedLGANameList
    }

    fun getCheckedWards(): MutableList<String> {
        return checkedWardNameList
    }

    fun addVHToCheckedLGAList(checkedLGAName: String) {
        if (!checkedLGANameList.contains(checkedLGAName)) {
            checkedLGANameList.add(checkedLGAName)
        }
    }

    fun removeVHFromCheckedLGAList(checkedLGAName: String) {
        checkedLGANameList.remove(checkedLGAName)
    }

    fun addVHToCheckedWardList(checkedWardName: String) {
        if (!checkedWardNameList.contains(checkedWardName)) {
            checkedWardNameList.add(checkedWardName)
        }
    }

    fun removeVHFromCheckedWardList(checkedWardName: String) {
        checkedWardNameList.remove(checkedWardName)
    }

    private fun getIsLGASelected(lgaName: String): Boolean {
        return checkedLGANameList.contains(lgaName)
    }

    private fun getIsWardSelected(wardName: String): Boolean {
        return checkedWardNameList.contains(wardName)
    }

    //var lastCheckedVH: LGAHeaderVH? = null

    fun addData(list: List<LGA>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getSectionCount(): Int {
        return list.size
    }

    override fun getItemCount(section: Int): Int {
        return list[section].wards.size
    }

    override fun onBindHeaderViewHolder(holder: SectionedViewHolder, section: Int, expanded: Boolean) {
        if (holder is LGAHeaderVH) {
            holder.bind(list[section], getIsLGASelected(list[section].name))
            holder.caret.setImageResource(if (expanded) R.drawable.arrow_up else R.drawable.arrow_down)
        }
    }

    override fun onBindFooterViewHolder(holder: SectionedViewHolder, section: Int) {
    }

    override fun onBindViewHolder(holder: SectionedViewHolder, section: Int, relativePosition: Int, absolutePosition: Int) {
        if (holder is WardItemVH) {
            holder.bind(list[section].wards[relativePosition] as Ward, getIsWardSelected((list[section].wards[relativePosition] as Ward).name))
        }
    }

    override fun getItemViewType(section: Int, relativePosition: Int, absolutePosition: Int): Int {
        return if (section == 1) {
            // VIEW_TYPE_FOOTER is -3, VIEW_TYPE_HEADER is -2, VIEW_TYPE_ITEM is -1.
            // You can return 0 or greater.
            0
        } else super.getItemViewType(section, relativePosition, absolutePosition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionedViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> LGAHeaderVH.create(parent, this, params)
            VIEW_TYPE_ITEM -> WardItemVH.create(parent, this, params)
            else -> WardItemVH.create(parent, this, params)
        }

    }

    fun clearSelection() {
        checkedLGANameList.clear()
        checkedWardNameList.clear()
    }

    class LGAHeaderVH(itemView: View, val adapter: FiltersAdapter, val binding: ViewDataBinding, val params: Params) : SectionedViewHolder(itemView) {

        val caret: ImageView = itemView.findViewById(R.id.caret)
        val radioButton: CheckBox = itemView.findViewById(R.id.radioButton)
        val title: TextView = itemView.findViewById(R.id.title)
        var lga: LGA? = null

        init {
            itemView.setOnClickListener {
                if (isFooter) {
                    // ignore footer clicks
                }
                if (isHeader) {
                    adapter.toggleSectionExpanded(relativePosition.section())
                } else {

                }
            }


        }

        fun bind(lga: LGA, isChecked: Boolean) {
            this.lga = lga
            binding.setVariable(BR.item, lga)
            binding.setVariable(BR.checked, isChecked)

            radioButton.setOnCheckedChangeListener { compoundButton, b ->
                if (b) {
                    adapter.addVHToCheckedLGAList(lga.name)
                } else {
                    adapter.removeVHFromCheckedLGAList(lga.name)
                }
            }

            binding.executePendingBindings()
        }

        companion object {
            fun create(parent: ViewGroup, adapter: FiltersAdapter, params: Params): LGAHeaderVH {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, R.layout.lga_filter_item_header, parent, false)
                return LGAHeaderVH(binding.root, adapter, binding, params)
            }
        }
    }


    class WardItemVH(itemView: View, val adapter: FiltersAdapter, val binding: ViewDataBinding, val params: Params) : SectionedViewHolder(itemView) {


        val checkbox: CheckBox = itemView.findViewById(R.id.checkbox)
        val title: TextView = itemView.findViewById(R.id.title)
        var ward: Ward? = null

        init {


        }

        fun bind(ward: Ward, isChecked: Boolean) {
            this.ward = ward
            binding.setVariable(BR.item, ward)
            binding.setVariable(BR.checked, isChecked)

            checkbox.setOnCheckedChangeListener { compoundButton, b ->
                if (b) {
                    adapter.addVHToCheckedWardList(ward.name)
                } else {
                    adapter.removeVHFromCheckedWardList(ward!!.name)
                }
            }

            binding.executePendingBindings()
        }

        companion object {
            fun create(parent: ViewGroup, adapter: FiltersAdapter, params: Params): WardItemVH {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, R.layout.list_item_ward, parent, false)
                return WardItemVH(binding.root, adapter, binding, params)
            }
        }
    }


}
