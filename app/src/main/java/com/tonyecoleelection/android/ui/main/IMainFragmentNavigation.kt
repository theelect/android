package com.tonyecoleelection.android.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.fragments.DummyFragment
import com.tonyecoleelection.android.ui.main.fragments.pvcdatalist.PVCVerificationListFragment
import com.tonyecoleelection.android.ui.main.fragments.pvcstats.PVCAdminStatsFragment
import com.tonyecoleelection.android.ui.main.fragments.statfulldetails.StatFullDetailsFragment
import com.tonyecoleelection.android.ui.main.fragments.verifypvcdata.PVCVerificationFragment
import com.tonyecoleelection.android.ui.main.fragments.voterdata.VoterDataFragment
import com.tonyecoleelection.android.utils.FragmentHistory
import com.tonyecoleelection.android.views.FragNavController
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
    fun goToPVCAdminStats()
    fun goToPVCValidationList()
    fun goToVoterDataList()
    fun goToPVCAdminStatsDetails(mode: Int)
    fun goToVoterDataList(name: String?, mode: Int)

    fun initDrawerToggle()
    fun onOptionsItemSelected(item: MenuItem): Boolean?
    fun onConfigurationChanged(newConfig: Configuration?)
    fun syncDrawerState()
}

class MainFragmentNavigation(private var activity: MainActivity,
                             private var bundle: Bundle) : IMainFragmentNavigation,
        FragNavController.TransactionListener,
        FragNavController.RootFragmentListener {


    override fun syncDrawerState() {
        toggle?.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        toggle?.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean? {
        return toggle?.onOptionsItemSelected(item)
    }

    val toolbar = activity.findViewById<Toolbar>(R.id.toolbar_main)
    val drawerLayout = activity.findViewById<DrawerLayout>(R.id.drawer_layout)
    var toggle: ActionBarDrawerToggle? = null

    override fun initDrawerToggle() {
        activity.setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setHomeButtonEnabled(true)
        drawerLayout.addDrawerListener(toggle!!)
        toggle?.syncState()
    }

    override fun goToVoterDataList(name: String?, mode: Int) {
        updateToolbarTitle("Voter Feed")
        pushFragment(VoterDataFragment.newInstance(mode, name))
    }


    override fun goToPVCAdminStatsDetails(mode: Int) {
        if (mode == 2) {
            updateToolbarTitle("LGA")
        } else {
            updateToolbarTitle("Wards")
        }
        pushFragment(StatFullDetailsFragment.newInstance(mode))
    }

    override fun goToPVCAdminStats() {
        switchTab(2)
        updateToolbarTitle("Dashboard")
    }

    override fun goToVoterDataList() {
        switchTab(3)
        updateToolbarTitle("Voter Feed")
    }

    override fun goToPVCValidationList() {
        switchTab(1)
        updateToolbarTitle("Voter Data")
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
                .rootFragmentListener(this, 4)
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
        if (!navController.isRootFragment) {
            activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            activity.supportActionBar!!.setDisplayShowHomeEnabled(true)
            activity.supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            //toolbar.navigationIcon = ContextCompat.getDrawable(activity, R.drawable.ic_arrow_back)
            toolbar.setNavigationOnClickListener {
                popFragment()
            }
        } else {
            activity.supportActionBar!!.setHomeAsUpIndicator(null)
            initDrawerToggle()
        }
        // TransitionManager.beginDelayedTransition(title_layout, ChangeBounds())
    }


    private fun updateToolbarTitle(title: String) {
        activity.supportActionBar!!.title = title
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
            FragNavController.TAB4 -> return VoterDataFragment.newInstance()
            FragNavController.TAB5 -> return DummyFragment()
        }
        throw IllegalStateException("Need to send an index that we know")
    }


    override fun goToPVCVerification() {
        switchTab(0)
        updateToolbarTitle("PVC Data Collect")
    }


}