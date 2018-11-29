package com.tonyecoleelection.android.di.main

import android.os.Bundle
import com.tonyecoleelection.android.di.scopes.ActivitiesScope
import com.tonyecoleelection.android.ui.main.*
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 14/05/2018.
 */

@Module
class MainModule {


    @ActivitiesScope
    @Provides
    fun providesMainNavigator(activity: MainActivity): IMainFragmentNavigation {
        return MainFragmentNavigation(activity, Bundle())
    }

}