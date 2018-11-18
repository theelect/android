package com.electionapp.android.di.auth

import android.os.Bundle
import android.support.v4.app.FragmentManager
import com.electionapp.android.di.scopes.ActivitiesScope
import com.electionapp.android.ui.auth.AuthActivity
import com.electionapp.android.ui.auth.AuthNavigator
import com.electionapp.android.ui.auth.IAuthNavigator
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