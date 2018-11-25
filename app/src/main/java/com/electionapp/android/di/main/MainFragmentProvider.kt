package com.electionapp.android.di.main

import com.electionapp.android.di.main.pvcstats.PVCAdminStatsModule
import com.electionapp.android.di.main.validationdata.PVCValidationListModule
import com.electionapp.android.di.main.verifypvc.PVCVerificationModule
import com.electionapp.android.di.main.voterdata.VoterDataModule
import com.electionapp.android.di.scopes.FragmentScope
import com.electionapp.android.ui.main.fragments.pvcdatalist.PVCVerificationListFragment
import com.electionapp.android.ui.main.fragments.pvcstats.PVCAdminStatsFragment
import com.electionapp.android.ui.main.fragments.verifypvcdata.PVCVerificationFragment
import com.electionapp.android.ui.main.fragments.voterdata.VoterDataFragment
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

}