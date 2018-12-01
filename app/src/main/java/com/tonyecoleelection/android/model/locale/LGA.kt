package com.tonyecoleelection.android.model.locale

import `in`.galaxyofandroid.spinerdialog.IdentifiableObject
import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.utils.toCamelCase
import java.util.ArrayList

/**
 *
 *
 **/

class LGA(
        val id: Int,
        val name: String,
        val wards: ArrayList<IdentifiableObject>) : IdentifiableObject {

    val nameCapitalized: String
        get() = name.toCamelCase()

    override fun getIdentifier(): Int {
        return id
    }

    override fun getSubtitle(): String {
        return "Local Gov't Area"
    }

    override fun getRecourseId(): Int {
        return R.drawable.email_grey
    }

    override fun getTitle(): String {
        return name
    }

}