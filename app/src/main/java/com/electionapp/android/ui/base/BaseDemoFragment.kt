package com.electionapp.android.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import com.electionapp.android.ui.main.MainActivity
import com.electionapp.android.ui.main.IMainFragmentNavigation


/**
 * Created by f22labs on 07/03/17.
 */

open class BaseDemoFragment : Fragment() {


    lateinit var mFragmentNavigation: IMainFragmentNavigation

    override open fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    open fun updateTitle(title: String) {
        (activity as MainActivity).updateToolbarTitle(title)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is IMainFragmentNavigation) {
            mFragmentNavigation = context
        }
    }

    interface FragmentNavigation {
        fun pushFragment(fragment: Fragment)
    }

    companion object {
        val ARGS_INSTANCE = "com.f22labs.instalikefragmenttransaction"
    }


}
