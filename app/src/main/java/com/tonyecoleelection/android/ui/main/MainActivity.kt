package com.tonyecoleelection.android.ui.main

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
import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.ui.base.BaseActivity
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
import com.tonyecoleelection.android.utils.IRestartHelper
import com.tonyecoleelection.android.utils.delay
import com.tonyecoleelection.android.utils.mapper.UserMapper
import com.tonyecoleelection.domain.base.Params
import com.tonyecoleelection.domain.usecase.user.FetchCurrentUserUseCase
import com.tonyecoleelection.domain.usecase.user.FetchIfCurrentUserIsAdminUseCase
import com.tonyecoleelection.domain.usecase.user.LogUserOutUseCase
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.nav_header_main.*


class MainActivity : BaseActivity(),
        HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }


    private val MY_PERMISSIONS_REQUEST_SEND_SMS = 1

    @Inject
    lateinit var mainFragmentNavigation: IMainFragmentNavigation

    lateinit var tabs: Array<String>

    @Inject
    lateinit var fetchIfCurrentUserIsAdminUseCase: FetchIfCurrentUserIsAdminUseCase

    @Inject
    lateinit var fetchCurrentUserUseCase: FetchCurrentUserUseCase

    @Inject
    lateinit var userMapper: UserMapper

    @Inject
    lateinit var logUserOutUseCase: LogUserOutUseCase

    @Inject
    lateinit var restartHelper: IRestartHelper

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        mainFragmentNavigation.setUp()

        tabs = this.resources.getStringArray(R.array.tab_name)

        initToolbar()

        initTab()

        delay({
            initNavHeader()
        }, 2000)

        requestPermission()

        if (fetchIfCurrentUserIsAdminUseCase.getIfUserIsAdmin()) {
            nav_view.menu.clear()
            nav_view.inflateMenu(R.menu.activity_main_drawer_admin)
            mainFragmentNavigation.goToPVCAdminStats()
        } else {
            nav_view.menu.clear()
            nav_view.inflateMenu(R.menu.activity_main_drawer_wc)
            mainFragmentNavigation.goToPVCVerification()
        }

    }

    private fun initNavHeader() {
        compositeDisposable.addAll(fetchCurrentUserUseCase.execute(Params.EMPTY)
                .map {
                    userMapper.mapFrom(it)
                }
                .subscribe({
                    nav_header_name.text = "${it.firstname} ${it.lastname}"
                    nav_header_email.text = "${it.email}"
                }, {
                    it.printStackTrace()
                }))
    }

    private fun requestPermission() {
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
        mainFragmentNavigation.syncDrawerState()
    }


    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        mainFragmentNavigation.onConfigurationChanged(newConfig)
    }


    private fun initToolbar() {
        mainFragmentNavigation.initDrawerToggle()
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

        if (mainFragmentNavigation?.onOptionsItemSelected(item) == true) {
            return true
        }

        return super.onOptionsItemSelected(item)

    }

    override fun onBackPressed() {
        mainFragmentNavigation.handleBackPress()
    }


    private val onNavigationItemSelectedListener: NavigationView.OnNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_item_verify -> {
                mainFragmentNavigation.clearStack()
                mainFragmentNavigation.goToPVCVerification()
            }
            R.id.nav_item_data -> {
                mainFragmentNavigation.clearStack()
                mainFragmentNavigation.goToPVCValidationList()
            }
            R.id.nav_item_dashboard -> {
                mainFragmentNavigation.clearStack()
                mainFragmentNavigation.goToPVCAdminStats()
            }
            R.id.nav_voter_data_dashboard -> {
                mainFragmentNavigation.clearStack()
                mainFragmentNavigation.goToVoterDataList()
            }
            R.id.nav_item_logout -> {
                compositeDisposable.addAll(logUserOutUseCase.execute(Params.EMPTY).subscribe({
                    restartHelper.doRestart()
                }, {
                    it.printStackTrace()
                }))
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
