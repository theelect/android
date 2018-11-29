package com.tonyecoleelection.android.di

import com.tonyecoleelection.android.di.auth.AuthFragmentProvider
import com.tonyecoleelection.android.di.auth.AuthModule
import com.tonyecoleelection.android.di.auth.SplashModule
import com.tonyecoleelection.android.di.filters.FilterFragmentProvider
import com.tonyecoleelection.android.di.filters.FilterModule
import com.tonyecoleelection.android.di.main.MainFragmentProvider
import com.tonyecoleelection.android.di.main.MainModule
import com.tonyecoleelection.android.ui.auth.AuthActivity
import com.tonyecoleelection.android.ui.auth.SplashActivity
import com.tonyecoleelection.android.ui.main.MainActivity
import com.tonyecoleelection.android.di.scopes.ActivitiesScope
import com.tonyecoleelection.android.ui.filters.FiltersActivity
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