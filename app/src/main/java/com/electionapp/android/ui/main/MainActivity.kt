package com.electionapp.android.ui.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.electionapp.android.R
import com.electionapp.android.ui.base.BaseActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector

import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.content.pm.PackageManager
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import com.electionapp.domain.base.Params
import com.electionapp.domain.usecase.auth.FetchCurrentUserWithTokenUserUseCase


class MainActivity : BaseActivity(),
        HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    var toggle: ActionBarDrawerToggle? = null

    private val MY_PERMISSIONS_REQUEST_SEND_SMS = 1


    @Inject
    lateinit var mainFragmentNavigation: IMainFragmentNavigation

    lateinit var tabs: Array<String>

    @Inject
    lateinit var fetchCurrentUserWithTokenUserUseCase: FetchCurrentUserWithTokenUserUseCase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        mainFragmentNavigation.setUp()

        tabs = this.resources.getStringArray(R.array.tab_name)

        initToolbar()

        initTab()

        requestPermisson()

        switchFragment(0)

        fetchCurrentUserWithTokenUserUseCase.execute(Params.EMPTY).subscribe({

        },{

        })

    }


    fun requestPermisson() {
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        arrayOf<String>(Manifest.permission.SEND_SMS),
                        MY_PERMISSIONS_REQUEST_SEND_SMS)
            }
        }
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

        setSupportActionBar(toolbar_main)
        toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar_main, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle!!)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        nav_view.setNavigationItemSelectedListener(onNavigationItemSelectedListener)

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
        return view
    }


    public override fun onStart() {
        super.onStart()
    }

    public override fun onStop() {

        super.onStop()
    }


    private fun switchFragment(position: Int) {
        mainFragmentNavigation.switchTab(position)
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
        mainFragmentNavigation.handleBackPress()
    }


    val onNavigationItemSelectedListener : NavigationView.OnNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_item_verify ->{
                mainFragmentNavigation.goToPVCVerification()
            }
            R.id.nav_item_data -> {
                mainFragmentNavigation.goToPVCValidationList()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        true
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
