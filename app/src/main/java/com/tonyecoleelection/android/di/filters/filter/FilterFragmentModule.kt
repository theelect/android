package com.tonyecoleelection.android.di.filters.filter


import android.arch.lifecycle.ViewModelProviders
import com.tonyecoleelection.android.di.scopes.FragmentScope
import com.tonyecoleelection.android.ui.adapters.filters.FiltersAdapter
import com.tonyecoleelection.android.ui.filters.FiltersFragment
import com.tonyecoleelection.android.ui.filters.FiltersViewModel
import com.tonyecoleelection.android.utils.ViewModelFactory
import com.tonyecoleelection.android.utils.mapper.LGAMapper
import com.tonyecoleelection.domain.base.Params
import com.tonyecoleelection.domain.usecase.main.FetchAllOccupationsFromServerUseCase
import com.tonyecoleelection.domain.usecase.main.FetchLGADataUseCase
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