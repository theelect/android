package com.tonyecoleelection.android.di.main.validationdata


import android.arch.lifecycle.ViewModelProviders
import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.di.scopes.FragmentScope
import com.tonyecoleelection.android.model.pvc.PVCData
import com.tonyecoleelection.android.ui.adapters.base.SingleLayoutAdapter
import com.tonyecoleelection.android.ui.main.fragments.pvcdatalist.PVCVerificationListFragment
import com.tonyecoleelection.android.ui.main.fragments.pvcdatalist.PVCVerificationListViewModel
import com.tonyecoleelection.android.utils.ViewModelFactory
import com.tonyecoleelection.android.utils.mapper.PVCDataMapper
import com.tonyecoleelection.domain.usecase.pvc.FetchPVCDataFromCacheUseCase
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 14/05/2018.
 */

@Module
class PVCValidationListModule {

    @FragmentScope
    @Provides
    fun providesVMFactory(pvcDataMapper: PVCDataMapper, fetchPVCDataFromCacheUseCase: FetchPVCDataFromCacheUseCase): ViewModelFactory<PVCVerificationListViewModel> {
        return ViewModelFactory(lazyOf(PVCVerificationListViewModel(fetchPVCDataFromCacheUseCase, pvcDataMapper)))
    }

    @FragmentScope
    @Provides
    fun providesVM(viewModelFactory: ViewModelFactory<PVCVerificationListViewModel>, fragment: PVCVerificationListFragment): PVCVerificationListViewModel {
        return ViewModelProviders.of(fragment, viewModelFactory).get(PVCVerificationListViewModel::class.java)
    }

    @FragmentScope
    @Provides
    fun providesAdapter(): SingleLayoutAdapter<PVCData> {
        return SingleLayoutAdapter<PVCData>(R.layout.item_pvc_data)
    }

}