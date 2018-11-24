package com.electionapp.android.model.admin

import java.text.DecimalFormat

data class StatItem(var count: Int,
                    var name: String) {
    val countString: String
        get() {
            val formatter = DecimalFormat("#,###,###")
            return formatter.format(count)
        }
}