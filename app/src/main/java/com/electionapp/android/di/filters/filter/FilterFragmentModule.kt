package com.electionapp.android.di.filters.filter


import android.arch.lifecycle.ViewModelProviders
import com.electionapp.android.di.scopes.FragmentScope
import com.electionapp.android.ui.adapters.filters.FiltersAdapter
import com.electionapp.android.ui.filters.FiltersFragment
import com.electionapp.android.ui.filters.FiltersViewModel
import com.electionapp.android.utils.ViewModelFactory
import com.electionapp.android.utils.mapper.LGAMapper
import com.electionapp.domain.base.Params
import com.electionapp.domain.usecase.main.FetchAllOccupationsFromServerUseCase
import com.electionapp.domain.usecase.main.FetchLGADataUseCase
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 14/05/2018.
 */

@Module
class FilterFragmentModule {

    @FragmentScope
    @Provides
    fun providesVMFactory(fetchAllOccupationsFromServerUseCase: FetchAllOccupationsFromServerUseCase,
                          fetchLGADataUseCase: FetchLGADataUseCase,
                          params: Params,
                          lgaMapper: LGAMapper): ViewModelFactory<FiltersViewModel> {
        return ViewModelFactory(lazyOf(FiltersViewModel(fetchLGADataUseCase, lgaMapper, params, fetchAllOccupationsFromServerUseCase)))
    }

    @FragmentScope
    @Provides
    fun providesVM(viewModelFactory: ViewModelFactory<FiltersViewModel>, filtersFragment: FiltersFragment): FiltersViewModel {
        return ViewModelProviders.of(filtersFragment, viewModelFactory).get(FiltersViewModel::class.java)
    }

    @FragmentScope
    @Provides
    fun providesSharedParams(): Params {
        return Params.EMPTY
    }

    @FragmentScope
    @Provides
    fun providesFiltersAdapter(params: Params): FiltersAdapter {
        return FiltersAdapter(params)
    }


}