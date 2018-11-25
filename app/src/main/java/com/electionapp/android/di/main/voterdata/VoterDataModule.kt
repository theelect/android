package com.electionapp.android.di.main.voterdata


import android.arch.lifecycle.ViewModelProviders
import com.electionapp.android.R
import com.electionapp.android.di.scopes.FragmentScope
import com.electionapp.android.model.pvc.PVCData
import com.electionapp.android.ui.adapters.base.SingleLayoutAdapter
import com.electionapp.android.ui.main.fragments.pvcdatalist.PVCVerificationListFragment
import com.electionapp.android.ui.main.fragments.pvcdatalist.PVCVerificationListViewModel
import com.electionapp.android.ui.main.fragments.voterdata.VoterDataFragment
import com.electionapp.android.ui.main.fragments.voterdata.VoterDataViewModel
import com.electionapp.android.utils.ViewModelFactory
import com.electionapp.android.utils.mapper.PVCDataMapper
import com.electionapp.domain.usecase.pvc.FetchPVCDataFromCacheUseCase
import com.electionapp.domain.usecase.pvc.FetchPVCDataFromServerUseCase
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 14/05/2018.
 */

@Module
class VoterDataModule {

    @FragmentScope
    @Provides
    fun providesVMFactory(pvcDataMapper: PVCDataMapper, fetchPVCDataFromServerUseCase: FetchPVCDataFromServerUseCase): ViewModelFactory<VoterDataViewModel> {
        return ViewModelFactory(lazyOf(VoterDataViewModel(fetchPVCDataFromServerUseCase, pvcDataMapper)))
    }

    @FragmentScope
    @Provides
    fun providesVM(viewModelFactory: ViewModelFactory<VoterDataViewModel>, fragment: VoterDataFragment): VoterDataViewModel {
        return ViewModelProviders.of(fragment, viewModelFactory).get(VoterDataViewModel::class.java)
    }

    @FragmentScope
    @Provides
    fun providesAdapter(): SingleLayoutAdapter<PVCData> {
        return SingleLayoutAdapter<PVCData>(R.layout.item_pvc_data)
    }

}