package com.electionapp.android.di.filters

import com.electionapp.android.di.filters.filter.FilterFragmentModule
import com.electionapp.android.di.main.pvcstats.PVCAdminStatsModule
import com.electionapp.android.di.main.validationdata.PVCValidationListModule
import com.electionapp.android.di.main.verifypvc.PVCVerificationModule
import com.electionapp.android.di.main.voterdata.VoterDataModule
import com.electionapp.android.di.scopes.FragmentScope
import com.electionapp.android.ui.filters.FiltersFragment
import com.electionapp.android.ui.main.fragments.pvcdatalist.PVCVerificationListFragment
import com.electionapp.android.ui.main.fragments.pvcstats.PVCAdminStatsFragment
import com.electionapp.android.ui.main.fragments.verifypvcdata.PVCVerificationFragment
import com.electionapp.android.ui.main.fragments.voterdata.VoterDataFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FilterFragmentProvider {

    @FragmentScope
    @ContributesAndroidInjector(modules = [FilterFragmentModule::class])
    internal abstract fun bindsFiltersFragment(): FiltersFragment



}