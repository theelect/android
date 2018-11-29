package com.tonyecoleelection.android.di.main.verifypvc


import android.arch.lifecycle.ViewModelProviders
import com.tonyecoleelection.android.di.scopes.FragmentScope
import com.tonyecoleelection.android.ui.main.IMainFragmentNavigation
import com.tonyecoleelection.android.ui.main.fragments.verifypvcdata.PVCVerificationFragment
import com.tonyecoleelection.android.ui.main.fragments.verifypvcdata.PVCVerificationViewModel
import com.tonyecoleelection.android.utils.ViewModelFactory
import com.tonyecoleelection.android.utils.mapper.UserMapper
import com.tonyecoleelection.domain.usecase.pvc.VerifyPVCUseCase
import com.tonyecoleelection.domain.usecase.user.FetchCurrentUserUseCase
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 14/05/2018.
 */

@Module
class PVCVerificationModule {

    @FragmentScope
    @Provides
    fun providesVMFactory(useCase: VerifyPVCUseCase, fetchCurrentUserUseCase: FetchCurrentUserUseCase, userMapper: UserMapper, navigator: IMainFragmentNavigation): ViewModelFactory<PVCVerificationViewModel> {
        return ViewModelFactory(lazyOf(PVCVerificationViewModel(useCase, fetchCurrentUserUseCase,
                userMapper, navigator)))
    }

    @FragmentScope
    @Provides
    fun providesVM(viewModelFactory: ViewModelFactory<PVCVerificationViewModel>, fragment: PVCVerificationFragment): PVCVerificationViewModel {
        return ViewModelProviders.of(fragment, viewModelFactory).get(PVCVerificationViewModel::class.java)
    }

}