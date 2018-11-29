package com.tonyecoleelection.android.di.main.pvcstats


import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import com.tonyecoleelection.android.di.scopes.FragmentScope
import com.tonyecoleelection.android.ui.adapters.admin.StatAdapter
import com.tonyecoleelection.android.ui.main.fragments.pvcstats.PVCAdminStatsFragment
import com.tonyecoleelection.android.ui.main.fragments.pvcstats.PVCAdminStatsViewModel
import com.tonyecoleelection.android.utils.ViewModelFactory
import com.tonyecoleelection.android.utils.mapper.PVCStatsMapper
import com.tonyecoleelection.domain.usecase.admin.FetchPVCStatsUseCase
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 14/05/2018.
 */

@Module
class PVCAdminStatsModule {

    @FragmentScope
    @Provides
    fun providesVMFactory(fetchPVCStatsUseCase: FetchPVCStatsUseCase,
                          userPVCStatsMapper: PVCStatsMapper): ViewModelFactory<PVCAdminStatsViewModel> {
        return ViewModelFactory(lazyOf(PVCAdminStatsViewModel(fetchPVCStatsUseCase, userPVCStatsMapper)))
    }

    @FragmentScope
    @Provides
    fun providesVM(viewModelFactory: ViewModelFactory<PVCAdminStatsViewModel>, fragment: PVCAdminStatsFragment): PVCAdminStatsViewModel {
        return ViewModelProviders.of(fragment, viewModelFactory).get(PVCAdminStatsViewModel::class.java)
    }


    @FragmentScope
    @Provides
    fun providesStatAdapter(context: Context, fragment: PVCAdminStatsFragment): StatAdapter {
        return StatAdapter(context, fragment)
    }

}