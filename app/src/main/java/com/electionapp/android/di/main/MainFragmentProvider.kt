package com.electionapp.android.di.main

import com.electionapp.android.di.main.verifypvc.PVCVerifcationModule
import com.electionapp.android.di.scopes.FragmentScope
import com.electionapp.android.ui.main.fragments.verifypvcdata.PVCVerificationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainFragmentProvider {

    @FragmentScope
    @ContributesAndroidInjector(modules = [PVCVerifcationModule::class])
    internal abstract fun bindsPVCVerificationFragment(): PVCVerificationFragment


}