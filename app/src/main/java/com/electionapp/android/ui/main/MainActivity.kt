package com.electionapp.android.ui.main

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.electionapp.android.R
import com.electionapp.android.fragments.DummyFragment
import com.electionapp.android.ui.base.BaseActivity
import com.electionapp.android.utils.FragmentHistory
import com.electionapp.android.utils.IMainFragmentNavigation
import com.electionapp.android.utils.Utils
import com.electionapp.android.views.FragNavController
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.weird_toolbar.*
import javax.inject.Inject

class MainActivity : BaseActivity(),
        FragNavController.TransactionListener,
        FragNavController.RootFragmentListener,
        IMainFragmentNavigation,
        HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    private val tabIconsNormal = intArrayOf(R.drawable.tab_home,
            R.drawable.tab_news,
            R.drawable.tab_share,
            R.drawable.tab_search,
            R.drawable.tab_profile)

    private val tabIconsSelected = intArrayOf(R.drawable.tab_home,
            R.drawable.tab_news,
            R.drawable.tab_share,
            R.drawable.tab_search,
            R.drawable.tab_profile)

    var toggle: ActionBarDrawerToggle? = null

    lateinit var tabs: Array<String>

    private var navController: FragNavController? = null

    private var fragmentHistory: FragmentHistory? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)


        tabs = this.resources.getStringArray(R.array.tab_name)

        initToolbar()

        initTab()

        fragmentHistory = FragmentHistory()


        navController = FragNavController.newBuilder(savedInstanceState,
                supportFragmentManager,
                R.id.content_frame)
                .transactionListener(this)
                .rootFragmentListener(this, tabs.size)
                .build()


        switchTab(0)

        bottom_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                fragmentHistory!!.push(tab.position)

                switchTab(tab.position)

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

                navController!!.clearStack()

                switchTab(tab.position)


            }
        })


        setSupportActionBar(toolbar)

        toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle!!)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)


    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle?.syncState()
    }


    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        toggle?.onConfigurationChanged(newConfig)
    }


    private fun initToolbar() {
        setSupportActionBar(toolbar)


    }

    private fun initTab() {
        if (bottom_tab_layout != null) {
            for (i in tabs.indices) {
                bottom_tab_layout!!.addTab(bottom_tab_layout!!.newTab())
                val tab = bottom_tab_layout!!.getTabAt(i)
                if (tab != null)
                    tab.customView = getTabView(i)
            }
        }
    }


    private fun getTabView(position: Int): View {
        val view = LayoutInflater.from(this@MainActivity).inflate(R.layout.tab_item_bottom, null)
        val icon = view.findViewById(R.id.tab_icon) as ImageView
        val title = view.findViewById(R.id.tab_title) as TextView
        title.text = tabs[position]
        icon.setImageDrawable(Utils.setDrawableSelector(this@MainActivity, tabIconsNormal[position], tabIconsSelected[position]))
        return view
    }


    public override fun onStart() {
        super.onStart()
    }

    public override fun onStop() {

        super.onStop()
    }


    private fun switchTab(position: Int) {
        navController!!.switchTab(position)


        //        updateToolbarTitle(position);
    }


    override fun onResume() {
        super.onResume()
    }


    override fun onPause() {
        super.onPause()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {


            android.R.id.home -> {


                onBackPressed()
                return true
            }
        }

        if (toggle?.onOptionsItemSelected(item) == true) {
            return true
        }

        return super.onOptionsItemSelected(item)

    }

    override fun onBackPressed() {

        if (!navController!!.isRootFragment) {
            navController!!.popFragment()
        } else {

            if (fragmentHistory!!.isEmpty) {
                super.onBackPressed()
            } else {


                if (fragmentHistory!!.stackSize > 1) {

                    val position = fragmentHistory!!.popPrevious()

                    switchTab(position)

                    updateTabSelection(position)

                } else {

                    switchTab(0)

                    updateTabSelection(0)

                    fragmentHistory!!.emptyStack()
                }
            }

        }
    }


    private fun updateTabSelection(currentTab: Int) {

        for (i in tabs!!.indices) {
            val selectedTab = bottom_tab_layout!!.getTabAt(i)
            selectedTab!!.customView!!.isSelected = currentTab == i
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (navController != null) {
            navController!!.onSaveInstanceState(outState)
        }
    }

    override fun pushFragment(fragment: Fragment) {
        if (navController != null) {
            navController!!.pushFragment(fragment)
        }
    }


    override fun onTabTransaction(fragment: Fragment, index: Int) {
        // If we have a backstack, show the back button
        if (supportActionBar != null && navController != null) {


            updateToolbar()

        }
    }

    private fun updateToolbar() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(!navController!!.isRootFragment)
        supportActionBar!!.setDisplayShowHomeEnabled(!navController!!.isRootFragment)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        //TransitionManager.beginDelayedTransition(title_layout, ChangeBounds())
    }


    override fun onFragmentTransaction(fragment: Fragment, transactionType: FragNavController.TransactionType) {
        //do fragmentty stuff. Maybe change title, I'm not going to tell you how to live your life
        // If we have a backstack, show the back button
        if (supportActionBar != null && navController != null) {

            updateToolbar()

        }
    }

    override fun getRootFragment(index: Int): Fragment {
        when (index) {
            FragNavController.TAB1 -> return DummyFragment.newInstance()
            FragNavController.TAB2 -> return DummyFragment()
            FragNavController.TAB3 -> return DummyFragment()
            FragNavController.TAB4 -> return DummyFragment()
            FragNavController.TAB5 -> return DummyFragment()
        }
        throw IllegalStateException("Need to send an index that we know")
    }


    fun updateToolbarTitle(title: String) {
        supportActionBar!!.title = title
    }


    companion object {
        fun start(context: Context) {
            var intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }


}
