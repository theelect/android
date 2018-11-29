package com.tonyecoleelection.android.di.auth

import android.os.Bundle
import android.support.v4.app.FragmentManager
import com.tonyecoleelection.android.di.scopes.ActivitiesScope
import com.tonyecoleelection.android.ui.auth.AuthActivity
import com.tonyecoleelection.android.ui.auth.AuthNavigator
import com.tonyecoleelection.android.ui.auth.IAuthNavigator
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 14/05/2018.
 */

@Module
class AuthModule {

    @Provides
    @ActivitiesScope
    fun providesNavigator(authActivity: AuthActivity): IAuthNavigator {
        return AuthNavigator(authActivity)
    }

    @Provides
    @ActivitiesScope
    fun providesNavBundle(): Bundle {
        return Bundle()
    }


    @Provides
    @ActivitiesScope
    fun providesFragmentManager(activity: AuthActivity): FragmentManager {
        return activity.supportFragmentManager
    }
}