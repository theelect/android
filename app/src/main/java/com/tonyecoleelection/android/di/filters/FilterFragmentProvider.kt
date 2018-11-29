package com.tonyecoleelection.android.di.filters

import com.tonyecoleelection.android.di.filters.filter.FilterFragmentModule
import com.tonyecoleelection.android.di.scopes.FragmentScope
import com.tonyecoleelection.android.ui.filters.FiltersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FilterFragmentProvider {

    @FragmentScope
    @ContributesAndroidInjector(modules = [FilterFragmentModule::class])
    internal abstract fun bindsFiltersFragment(): FiltersFragment



}