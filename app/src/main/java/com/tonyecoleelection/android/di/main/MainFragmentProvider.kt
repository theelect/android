package com.tonyecoleelection.android.di.main

import com.tonyecoleelection.android.di.main.pvcstats.PVCAdminStatsModule
import com.tonyecoleelection.android.di.main.pvcstats.PVCStatsFullModule
import com.tonyecoleelection.android.di.main.validationdata.PVCValidationListModule
import com.tonyecoleelection.android.di.main.verifypvc.PVCVerificationModule
import com.tonyecoleelection.android.di.main.voterdata.VoterDataModule
import com.tonyecoleelection.android.di.scopes.FragmentScope
import com.tonyecoleelection.android.ui.main.fragments.pvcdatalist.PVCVerificationListFragment
import com.tonyecoleelection.android.ui.main.fragments.pvcstats.PVCAdminStatsFragment
import com.tonyecoleelection.android.ui.main.fragments.statfulldetails.StatFullDetailsFragment
import com.tonyecoleelection.android.ui.main.fragments.verifypvcdata.PVCVerificationFragment
import com.tonyecoleelection.android.ui.main.fragments.voterdata.VoterDataFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainFragmentProvider {

    @FragmentScope
    @ContributesAndroidInjector(modules = [PVCVerificationModule::class])
    internal abstract fun bindsPVCVerificationFragment(): PVCVerificationFragment


    @FragmentScope
    @ContributesAndroidInjector(modules = [PVCValidationListModule::class])
    internal abstract fun bindsPVCValidationListFragment(): PVCVerificationListFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [PVCAdminStatsModule::class])
    internal abstract fun bindsPVCAdminStatsFragment(): PVCAdminStatsFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [VoterDataModule::class])
    internal abstract fun bindsVoterDataFragment(): VoterDataFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [PVCStatsFullModule::class])
    internal abstract fun bindsStatFullDetailsFragment(): StatFullDetailsFragment

}