package com.electionapp.android.ui.auth

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.electionapp.domain.usecase.auth.CheckUserIsSignedInUseCase
import com.electionapp.android.R
import com.electionapp.android.ui.main.MainActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


/**
 * Created by f22labs on 07/03/17.
 */

open class SplashActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var splashNavigator: ISplashNavigator


    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splash_activity)

        AndroidInjection.inject(this)


        splashNavigator.routeUser()

    }

}

interface ISplashNavigator {
    fun routeUser()
}


class SplashNavigator(private var checkUserIsSignedInUseCase: CheckUserIsSignedInUseCase, private var splashActivity: SplashActivity) : ISplashNavigator {

    override fun routeUser() {
        if (!checkUserIsSignedInUseCase.isUserSignedIn()) {
            splashActivity.finish()
            AuthActivity.start(splashActivity)
        } else {
            splashActivity.finish()
            MainActivity.start(splashActivity)
        }
    }

}


