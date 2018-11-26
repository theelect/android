package com.electionapp.android.di

import com.electionapp.android.di.auth.AuthFragmentProvider
import com.electionapp.android.di.auth.AuthModule
import com.electionapp.android.di.auth.SplashModule
import com.electionapp.android.di.filters.FilterFragmentProvider
import com.electionapp.android.di.filters.FilterModule
import com.electionapp.android.di.main.MainFragmentProvider
import com.electionapp.android.di.main.MainModule
import com.electionapp.android.ui.auth.AuthActivity
import com.electionapp.android.ui.auth.SplashActivity
import com.electionapp.android.ui.main.MainActivity
import com.electionapp.android.di.scopes.ActivitiesScope
import com.electionapp.android.ui.filters.FiltersActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

//    @Binds
//    @IntoMap
//    @ActivityKey(AuthActivity::class)
//    internal abstract fun authActivity(builder: AuthComponent.Builder): AndroidInjector.Factory<out Activity>
//

    @ActivitiesScope
    @ContributesAndroidInjector(modules = [FilterModule::class, FilterFragmentProvider::class])
    internal abstract fun bindFilterActivity(): FiltersActivity


    @ActivitiesScope
    @ContributesAndroidInjector(modules = [AuthModule::class, AuthFragmentProvider::class])
    internal abstract fun bindAuthActivity(): AuthActivity

    @ActivitiesScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainFragmentProvider::class])
    internal abstract fun bindMainActivity(): MainActivity

    @ActivitiesScope
    @ContributesAndroidInjector(modules = [SplashModule::class])
    internal abstract fun bindSplashActivity(): SplashActivity

}