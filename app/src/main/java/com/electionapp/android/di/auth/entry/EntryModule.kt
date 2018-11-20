package com.electionapp.android.di.auth.entry


import android.arch.lifecycle.ViewModelProviders
import com.electionapp.android.di.scopes.FragmentScope
import com.electionapp.android.ui.auth.IAuthNavigator
import com.electionapp.android.ui.auth.fragments.entry.EntryFragment
import com.electionapp.android.ui.auth.fragments.entry.EntryViewModel
import com.electionapp.android.utils.ViewModelFactory
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