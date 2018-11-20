package com.electionapp.android.di.main

import com.electionapp.android.di.main.verifypvc.PVCVerificationModule
import com.electionapp.android.di.scopes.FragmentScope
import com.electionapp.android.ui.main.fragments.verifypvcdata.PVCVerificationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainFragmentProvider {

    @FragmentScope
    @ContributesAndroidInjector(modules = [PVCVerificationModule::class])
    internal abstract fun bindsPVCVerificationFragment(): PVCVerificationFragment

}