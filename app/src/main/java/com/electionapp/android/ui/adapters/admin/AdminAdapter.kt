package com.electionapp.android.ui.adapters.admin

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.Color
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR
import com.electionapp.android.R
import com.electionapp.android.model.admin.StatGroup
import com.electionapp.android.model.admin.StatItem
import com.electionapp.android.ui.adapters.base.SingleLayoutAdapter
import com.electionapp.android.views.decorators.DividerItemDecoration
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import kotlinx.android.synthetic.main.fragment_pvc_admin_dash.*
import kotlinx.android.synthetic.main.item_stat_group_count.view.*
import kotlinx.android.synthetic.main.item_stat_pie_chart.view.*
import java.util.*

class StatAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var totalStat: StatItem? = null
    var lgaStatGroup: StatGroup? = null
    var wardStatGroup: StatGroup? = null
    var genderStatGroup: StatGroup? = null
    var professionStatGroup: StatGroup? = null

    override fun getItemCount(): Int {
        return 5
    }

    enum class ITEM_TYPE(val value: Int) {
        SINGLE_STAT_ITEM(1), MULTI_STAT_ITEM(2), PIE_CHART_ITEM(3), SECTION_CHART_ITEM(4)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            ITEM_TYPE.SINGLE_STAT_ITEM.value
        } else if (position == 1 || position == 2) {
            ITEM_TYPE.MULTI_STAT_ITEM.value
        } else if (position == 3) {
            ITEM_TYPE.PIE_CHART_ITEM.value
        } else {
            ITEM_TYPE.SECTION_CHART_ITEM.value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_TYPE.SINGLE_STAT_ITEM.value -> SingleStatViewHolder.create(parent)
            ITEM_TYPE.MULTI_STAT_ITEM.value -> MultiStatViewHolder.create(parent)
            ITEM_TYPE.PIE_CHART_ITEM.value -> PieChartStatViewHolder.create(parent)
            else -> HolePieChartStatViewHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (position) {
            0 -> (holder as SingleStatViewHolder).bind(totalStat)
            1 -> (holder as MultiStatViewHolder).bind(lgaStatGroup)
            2 -> (holder as MultiStatViewHolder).bind(wardStatGroup)
            3 -> (holder as PieChartStatViewHolder).bind(genderStatGroup)
            4 -> (holder as HolePieChartStatViewHolder).bind(professionStatGroup)
        }
    }

}

class SingleStatViewHolder(view: View, var binding: ViewDataBinding) : RecyclerView.ViewHolder(view) {

    fun bind(statItem: StatItem?) {
        binding.setVariable(BR.item, statItem)
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): SingleStatViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, R.layout.item_pvc_total_count, parent, false)
            return SingleStatViewHolder(binding.root, binding)
        }
    }
}


class HolePieChartStatViewHolder(view: View, binding: ViewDataBinding) : PieChartStatViewHolder(view, binding) {

    override fun bind(statGroup: StatGroup?) {
        super.bind(statGroup)
        view.stat_pie_chart.isDrawHoleEnabled = true
        view.stat_pie_chart.invalidate()
    }

    companion object {
        fun create(parent: ViewGroup): HolePieChartStatViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, R.layout.item_stat_pie_chart, parent, false)
            return HolePieChartStatViewHolder(binding.root, binding)
        }
    }
}


open class PieChartStatViewHolder(var view: View, var binding: ViewDataBinding) : RecyclerView.ViewHolder(view) {

    open fun bind(statGroup: StatGroup?) {
        view.stat_pie_chart.isDrawHoleEnabled = false
        view.stat_pie_chart.animateY(1400, Easing.EasingOption.EaseInOutQuad)

        val entries = ArrayList<PieEntry>()

        if (statGroup != null) {
            // NOTE: The order of the entries when being added to the entries array determines their position around the center of
            // the chart.
            var range = 0

            statGroup.items.forEach {
                range += it.count
            }

            for (i in 0 until statGroup.items.size) {

                entries.add(PieEntry(((statGroup.items[i].count.toFloat() / range) * 100), statGroup.items[i].name))
            }

            val colors = mutableListOf<Int>()
            for (i in 0 until statGroup.items.size) {
                val rnd = Random()
                val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                colors.add(color)
            }

            val dataSet = PieDataSet(entries, "")
            dataSet.colors = colors

            val data = PieData(dataSet)
            data.setValueFormatter(PercentFormatter())
            data.setValueTextSize(11f)
            data.setValueTextColor(Color.WHITE)
            view.stat_pie_chart.data = data

            // undo all highlights
            view.stat_pie_chart.highlightValues(null)
        }

        view.stat_pie_chart.invalidate()
        binding.setVariable(BR.item, statGroup)
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): PieChartStatViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, R.layout.item_stat_pie_chart, parent, false)
            return PieChartStatViewHolder(binding.root, binding)
        }
    }
}

class MultiStatViewHolder(var view: View, var binding: ViewDataBinding) : RecyclerView.ViewHolder(view) {

    fun bind(statGroup: StatGroup?) {
        val adapter = SingleLayoutAdapter<StatItem>(R.layout.item_stat_)
        val layoutManager = GridLayoutManager(view.context, 2)
        view.stat_group_rv.addItemDecoration(DividerItemDecoration(view.context))
        view.stat_group_rv.layoutManager = layoutManager
        view.stat_group_rv.adapter = adapter
        binding.setVariable(BR.item, statGroup)
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): MultiStatViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, R.layout.item_stat_group_count, parent, false)
            return MultiStatViewHolder(binding.root, binding)
        }
    }
}