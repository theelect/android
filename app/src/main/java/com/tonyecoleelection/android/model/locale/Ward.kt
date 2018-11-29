package com.tonyecoleelection.android.model.locale

import `in`.galaxyofandroid.spinerdialog.IdentifiableObject
import com.tonyecoleelection.android.R

/**
 *
 *
 **/

class Ward(
        val id: Int,
        val name: String) : IdentifiableObject {
    override fun getIdentifier(): Int {
        return id
    }

    override fun getSubtitle(): String {
        return "Ward"
    }

    override fun getRecourseId(): Int {
        return R.drawable.email_grey
    }

    override fun getTitle(): String {
        return name
    }

}