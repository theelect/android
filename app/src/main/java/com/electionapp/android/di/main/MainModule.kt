package com.electionapp.android.di.main

import android.os.Bundle
import com.electionapp.android.di.scopes.ActivitiesScope
import com.electionapp.android.ui.main.*
import com.electionapp.android.utils.FragmentHistory
import com.electionapp.android.views.FragNavController
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