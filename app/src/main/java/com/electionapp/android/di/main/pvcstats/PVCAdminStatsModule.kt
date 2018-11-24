package com.electionapp.android.di.main.pvcstats


import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import com.electionapp.android.di.scopes.FragmentScope
import com.electionapp.android.ui.adapters.admin.StatAdapter
import com.electionapp.android.ui.main.fragments.pvcstats.PVCAdminStatsFragment
import com.electionapp.android.ui.main.fragments.pvcstats.PVCAdminStatsViewModel
import com.electionapp.android.utils.ViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 14/05/2018.
 */

@Module
class PVCAdminStatsModule {

    @FragmentScope
    @Provides
    fun providesVMFactory(): ViewModelFactory<PVCAdminStatsViewModel> {
        return ViewModelFactory(lazyOf(PVCAdminStatsViewModel()))
    }

    @FragmentScope
    @Provides
    fun providesVM(viewModelFactory: ViewModelFactory<PVCAdminStatsViewModel>, fragment: PVCAdminStatsFragment): PVCAdminStatsViewModel {
        return ViewModelProviders.of(fragment, viewModelFactory).get(PVCAdminStatsViewModel::class.java)
    }


    @FragmentScope
    @Provides
    fun providesStatAdapter(context: Context): StatAdapter {
        return StatAdapter(context)
    }

}