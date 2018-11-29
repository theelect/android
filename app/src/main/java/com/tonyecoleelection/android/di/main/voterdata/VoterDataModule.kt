package com.tonyecoleelection.android.di.main.voterdata


import android.arch.lifecycle.ViewModelProviders
import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.di.scopes.FragmentScope
import com.tonyecoleelection.android.model.pvc.PVCData
import com.tonyecoleelection.android.ui.adapters.base.SingleLayoutAdapter
import com.tonyecoleelection.android.ui.main.fragments.voterdata.VoterDataFragment
import com.tonyecoleelection.android.ui.main.fragments.voterdata.VoterDataViewModel
import com.tonyecoleelection.android.utils.ViewModelFactory
import com.tonyecoleelection.android.utils.mapper.PVCDataMapper
import com.tonyecoleelection.domain.usecase.pvc.FetchPVCDataFromServerUseCase
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
        return SingleLayoutAdapter<PVCData>(R.layout.item_pvc_data_full)
    }

}