package com.tonyecoleelection.android.ui.base

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.tonyecoleelection.android.utils.TAG

/**
 * Created by aliumujib on 08/06/2018.
 */

abstract class BaseNavigator {

    abstract fun getFragmentManager(): FragmentManager?

    @IdRes abstract fun getLayoutID(): Int

    protected fun findFragmentInstance(tag: String): Fragment? {
        return getFragmentManager()?.findFragmentByTag(tag)
    }

    protected fun addFragmentWithBackStack(fragment: Fragment, tag: String = fragment.TAG()) {
        val transaction = getFragmentManager()?.beginTransaction()
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction?.add(getLayoutID(), fragment)?.addToBackStack(tag)?.commit()
    }

    protected fun replaceFragment(fragment: Fragment) {
        val transaction = getFragmentManager()?.beginTransaction()
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction?.replace(getLayoutID(), fragment)?.commit()
    }
}