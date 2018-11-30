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
import com.tonyecoleelection.constants.Constants
import com.tonyecoleelection.domain.base.Params


/**
 *  @author Aidan Follestad (afollestad)
 */

class FiltersAdapter(val params: Params) : SectionedRecyclerViewAdapter<SectionedViewHolder>() {

    val list: MutableList<LGA> = mutableListOf()

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
            holder.bind(list[section])
            holder.caret.setImageResource(if (expanded) R.drawable.arrow_up else R.drawable.arrow_down)
        }
    }

    override fun onBindFooterViewHolder(holder: SectionedViewHolder, section: Int) {
    }

    override fun onBindViewHolder(holder: SectionedViewHolder, section: Int, relativePosition: Int, absolutePosition: Int) {
        if (holder is WardItemVH) {
            holder.bind(list[section].wards[relativePosition] as Ward)
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

    class LGAHeaderVH(itemView: View, val adapter: FiltersAdapter, val binding: ViewDataBinding, val params: Params) : SectionedViewHolder(itemView) {

        val caret: ImageView = itemView.findViewById(R.id.caret)
        val radioButton: RadioButton = itemView.findViewById(R.id.radioButton)
        val title: TextView = itemView.findViewById(R.id.title)

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

            radioButton.setOnCheckedChangeListener { compoundButton, b ->
                params.putString(Constants.FILTER_CONSTANTS.LGA, title.text.toString().toLowerCase().toString())
            }
        }

        fun bind(lga: LGA) {
            val isChecked = (params.getData(Constants.FILTER_CONSTANTS.LGA, null) == lga.name)
            binding.setVariable(BR.item, lga)
            binding.setVariable(BR.checked, isChecked)
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

        init {

            checkbox.setOnCheckedChangeListener { compoundButton, b ->
                params.putString(Constants.FILTER_CONSTANTS.WARD, title.text.toString().toLowerCase().toString())
            }
        }

        fun bind(ward: Ward) {
            val isChecked = (params.getData(Constants.FILTER_CONSTANTS.WARD, null) == ward.name)
            binding.setVariable(BR.item, ward)
            binding.setVariable(BR.checked, isChecked)
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
