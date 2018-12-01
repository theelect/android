package com.tonyecoleelection.android.views

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter

class AgeGroupXAxisFormatter : IAxisValueFormatter {

    protected var ageGroup = arrayOf("18-30", "31-40", "41-50", "51-60", "61-100")

    override fun getFormattedValue(value: Float, axis: AxisBase): String {

        val percent = value / axis.mAxisRange
        return ageGroup[(ageGroup.size * percent).toInt()]
    }
}
