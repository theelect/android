package com.tonyecoleelection.android.model.admin

import com.tonyecoleelection.android.utils.toCamelCase
import java.text.DecimalFormat

data class StatItem(var count: Int,
                    var name: String,
                    var percentage: Double) {

    val nameCapitalized: String
        get() = name.toCamelCase()

    val countString: String
        get() {
            val formatter = DecimalFormat("#,###,###")
            return formatter.format(count)
        }
}