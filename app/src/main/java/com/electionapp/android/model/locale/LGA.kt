package com.electionapp.android.model.locale

import `in`.galaxyofandroid.spinerdialog.IdentifiableObject
import com.electionapp.android.R
import kotlinx.android.synthetic.main.weird_toolbar.*

/**
 *
 *
 **/

class LGA(
        val id: Int,
        val name: String,
        val wards: List<Ward>) : IdentifiableObject {

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