package com.electionapp.android.ui.filters

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.electionapp.android.R
import com.electionapp.android.ui.base.BaseActivity


/**
 * Created by f22labs on 07/03/17.
 */

open class FiltersActivity : BaseActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filters_activity)

    }


    companion object {
        val FILTER_TASK_REQ_CODE = 104
        fun startForResult(activity: Activity) {
            val intent = Intent(activity, FiltersActivity::class.java)
            activity.startActivityForResult(intent, FILTER_TASK_REQ_CODE)
        }

        fun startForResult(fragment: Fragment) {
            val intent = Intent(fragment.activity, FiltersActivity::class.java)
            fragment.startActivityForResult(intent, FILTER_TASK_REQ_CODE)
        }
    }

}
