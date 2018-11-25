package com.electionapp.android.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by aliumujib on 11/06/2018.
 */

//View Pager fragments setting adapter class
class ViewPagerAdapter(fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager) {

    var tabs: MutableList<Fragment> = mutableListOf()
    var titles: MutableList<String>? = mutableListOf()
//    constructor(fragmentManager: FragmentManager) : this(tabs, titles, fragmentManager)

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun getItem(position: Int): Fragment {
        return tabs[position]
    }

    override fun getCount(): Int {
        return tabs.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when {
            titles != null -> titles!![position]
            else -> "PAGE_$position"
        }
    }

    fun addFrag(fragment: Fragment, title: String) {
        tabs.add(fragment)
        titles?.add(title)
    }

}