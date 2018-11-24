package com.electionapp.android.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import com.electionapp.android.R
import com.electionapp.android.fragments.DummyFragment
import com.electionapp.android.ui.main.fragments.pvcdatalist.PVCVerificationListFragment
import com.electionapp.android.ui.main.fragments.pvcstats.PVCAdminStatsFragment
import com.electionapp.android.ui.main.fragments.verifypvcdata.PVCVerificationFragment
import com.electionapp.android.utils.FragmentHistory
import com.electionapp.android.views.FragNavController
import kotlinx.android.synthetic.main.activity_main.*


interface IMainFragmentNavigation {
    val isRootFragment: Boolean

    fun setUp()

    fun goToPVCVerification()
    fun pushFragment(fragment: Fragment)
    fun push(position: Int)
    fun clearStack()
    fun switchTab(position: Int)
    fun popFragment()
    fun handleBackPress()
    fun goToPVCValidationList()
}

class MainFragmentNavigation(private var activity: MainActivity,
                             private var bundle: Bundle) : IMainFragmentNavigation,
        FragNavController.TransactionListener,
        FragNavController.RootFragmentListener {

    override fun goToPVCValidationList() {
        switchTab(1)
    }


    override fun handleBackPress() {

        if (!this.isRootFragment) {
            this.popFragment()
        } else {

            if (fragmentHistory.isEmpty) {
                //activity.onBackPressed()
            } else {


                if (fragmentHistory!!.stackSize > 1) {

                    val position = fragmentHistory!!.popPrevious()

                    switchTab(position)

                } else {

                    switchTab(0)

                    fragmentHistory!!.emptyStack()
                }
            }

        }
    }

    private fun updateTabSelection(currentTab: Int) {

    }


    override val isRootFragment: Boolean
        get() = navController.isRootFragment

    override fun switchTab(position: Int) {
        navController.switchTab(position)
    }

    override fun popFragment() {
        navController.popFragment()
    }

    override fun clearStack() {
        navController.clearStack()
    }

    override fun push(position: Int) {
        fragmentHistory.push(position)
    }

    private lateinit var navController: FragNavController
    private lateinit var fragmentHistory: FragmentHistory

    override fun setUp() {
        fragmentHistory = FragmentHistory()


        navController = FragNavController.newBuilder(bundle,
                activity.supportFragmentManager,
                R.id.content_frame)
                .transactionListener(this)
                .rootFragmentListener(this, 3)
                .build()
    }

    override fun pushFragment(fragment: Fragment) {
        navController.pushFragment(fragment)
    }


    override fun onTabTransaction(fragment: Fragment, index: Int) {
        // If we have a backstack, show the back button
        if (activity.supportActionBar != null && navController != null) {
            updateToolbar()

        }
    }

    private fun updateToolbar() {
//        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(!navController!!.isRootFragment)
//        activity.supportActionBar!!.setDisplayShowHomeEnabled(!navController!!.isRootFragment)
//        activity.supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        //TransitionManager.beginDelayedTransition(title_layout, ChangeBounds())
    }


    override fun onFragmentTransaction(fragment: Fragment, transactionType: FragNavController.TransactionType) {
        //do fragmentty stuff. Maybe change title, I'm not going to tell you how to live your life
        // If we have a backstack, show the back button
        if (activity.supportActionBar != null && navController != null) {

            updateToolbar()

        }
    }

    override fun getRootFragment(index: Int): Fragment {
        when (index) {
            FragNavController.TAB1 -> return PVCVerificationFragment.newInstance()
            FragNavController.TAB2 -> return PVCVerificationListFragment.newInstance()
            FragNavController.TAB3 -> return PVCAdminStatsFragment.newInstance()
            FragNavController.TAB4 -> return DummyFragment()
            FragNavController.TAB5 -> return DummyFragment()
        }
        throw IllegalStateException("Need to send an index that we know")
    }


    override fun goToPVCVerification() {
        switchTab(0)
    }


}