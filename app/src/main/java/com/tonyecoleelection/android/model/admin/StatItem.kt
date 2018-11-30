package com.tonyecoleelection.android.model.admin

import java.text.DecimalFormat

data class StatItem(var count: Int,
                    var name: String,
                    var percentage: Double) {

    val nameCapitalized: String
        get() = name.capitalize()

    val countString: String
        get() {
            val formatter = DecimalFormat("#,###,###")
            return formatter.format(count)
        }
}