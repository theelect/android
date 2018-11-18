package com.electionapp.android.di.main

import com.electionapp.android.di.scopes.ActivitiesScope
import com.electionapp.android.ui.main.IMainNavigator
import com.electionapp.android.ui.main.MainActivity
import com.electionapp.android.ui.main.MainNavigator
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 14/05/2018.
 */

@Module
class MainModule {


    @ActivitiesScope
    @Provides
    fun providesMainNavigator(activity: MainActivity): IMainNavigator {
        return MainNavigator(activity)
    }

}