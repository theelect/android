package com.tonyecoleelection.android.di.main.pvcstats


import android.arch.lifecycle.ViewModelProviders
import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.di.scopes.FragmentScope
import com.tonyecoleelection.android.model.admin.StatItem
import com.tonyecoleelection.android.ui.adapters.base.SingleLayoutAdapter
import com.tonyecoleelection.android.ui.main.fragments.statfulldetails.PVCStatsFullViewModel
import com.tonyecoleelection.android.ui.main.fragments.statfulldetails.StatFullDetailsFragment
import com.tonyecoleelection.android.utils.ViewModelFactory
import com.tonyecoleelection.android.utils.mapper.PVCStatsMapper
import com.tonyecoleelection.domain.usecase.admin.FetchPVCStatsUseCase
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 14/05/2018.
 */

@Module
class PVCStatsFullModule {

    @FragmentScope
    @Provides
    fun providesVMFactory(fetchPVCStatsUseCase: FetchPVCStatsUseCase,
                          userPVCStatsMapper: PVCStatsMapper): ViewModelFactory<PVCStatsFullViewModel> {
        return ViewModelFactory(lazyOf(PVCStatsFullViewModel(fetchPVCStatsUseCase, userPVCStatsMapper)))
    }

    @FragmentScope
    @Provides
    fun providesVM(viewModelFactory: ViewModelFactory<PVCStatsFullViewModel>, fragment: StatFullDetailsFragment): PVCStatsFullViewModel {
        return ViewModelProviders.of(fragment, viewModelFactory).get(PVCStatsFullViewModel::class.java)
    }


    @FragmentScope
    @Provides
    fun providesStatAdapter(fragment: StatFullDetailsFragment): SingleLayoutAdapter<StatItem> {
        return SingleLayoutAdapter(R.layout.item_stat_card, fragment)
    }

}