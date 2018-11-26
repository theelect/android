package com.electionapp.android.ui.filters

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.electionapp.android.R
import com.electionapp.android.ui.base.BaseActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


/**
 * Created by f22labs on 07/03/17.
 */

open class FiltersActivity : BaseActivity(),
        HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        setContentView(R.layout.filters_activity)

    }


    companion object {
        val FILTER_TASK_REQ_CODE = 104
        val FILTER_TASK_DATA = "FILTER_TASK_DATA"

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
