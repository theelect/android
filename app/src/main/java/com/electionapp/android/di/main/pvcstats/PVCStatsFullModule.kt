package com.electionapp.android.di.main.pvcstats


import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import com.electionapp.android.R
import com.electionapp.android.di.scopes.FragmentScope
import com.electionapp.android.model.admin.StatItem
import com.electionapp.android.ui.adapters.admin.StatAdapter
import com.electionapp.android.ui.adapters.base.SingleLayoutAdapter
import com.electionapp.android.ui.main.fragments.pvcstats.PVCAdminStatsFragment
import com.electionapp.android.ui.main.fragments.pvcstats.PVCAdminStatsViewModel
import com.electionapp.android.ui.main.fragments.statfulldetails.PVCStatsFullViewModel
import com.electionapp.android.ui.main.fragments.statfulldetails.StatFullDetailsFragment
import com.electionapp.android.utils.ViewModelFactory
import com.electionapp.android.utils.mapper.PVCStatsMapper
import com.electionapp.domain.usecase.admin.FetchPVCStatsUseCase
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