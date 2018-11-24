package com.electionapp.android.di.main.validationdata


import android.arch.lifecycle.ViewModelProviders
import com.electionapp.android.R
import com.electionapp.android.di.scopes.FragmentScope
import com.electionapp.android.model.pvc.PVCData
import com.electionapp.android.ui.adapters.base.SingleLayoutAdapter
import com.electionapp.android.ui.main.fragments.pvcdatalist.PVCVerificationListFragment
import com.electionapp.android.ui.main.fragments.pvcdatalist.PVCVerificationListViewModel
import com.electionapp.android.utils.ViewModelFactory
import com.electionapp.android.utils.mapper.PVCDataMapper
import com.electionapp.domain.usecase.pvc.FetchPVCDataUseCase
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 14/05/2018.
 */

@Module
class PVCValidationListModule {

    @FragmentScope
    @Provides
    fun providesVMFactory(pvcDataMapper: PVCDataMapper, fetchPVCDataUseCase: FetchPVCDataUseCase): ViewModelFactory<PVCVerificationListViewModel> {
        return ViewModelFactory(lazyOf(PVCVerificationListViewModel(fetchPVCDataUseCase, pvcDataMapper)))
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