package com.tonyecoleelection.android.di.auth.entry


import android.arch.lifecycle.ViewModelProviders
import com.tonyecoleelection.android.di.scopes.FragmentScope
import com.tonyecoleelection.android.ui.auth.IAuthNavigator
import com.tonyecoleelection.android.ui.auth.fragments.entry.EntryFragment
import com.tonyecoleelection.android.ui.auth.fragments.entry.EntryViewModel
import com.tonyecoleelection.android.utils.ViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 14/05/2018.
 */

@Module
class EntryModule {

    @FragmentScope
    @Provides
    fun providesEntryVMFactory(loginNavigator: IAuthNavigator): ViewModelFactory<EntryViewModel> {
        return ViewModelFactory(lazyOf(EntryViewModel(loginNavigator)))
    }

    @FragmentScope
    @Provides
    fun providesEntryVM(viewModelFactory: ViewModelFactory<EntryViewModel>, fragment: EntryFragment): EntryViewModel {
        return ViewModelProviders.of(fragment, viewModelFactory).get(EntryViewModel::class.java)
    }

}