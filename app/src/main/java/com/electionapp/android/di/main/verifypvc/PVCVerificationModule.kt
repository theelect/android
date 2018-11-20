package com.electionapp.android.di.main.verifypvc


import android.arch.lifecycle.ViewModelProviders
import com.electionapp.android.di.scopes.FragmentScope
import com.electionapp.android.ui.main.IMainNavigator
import com.electionapp.android.ui.main.fragments.verifypvcdata.PVCVerificationFragment
import com.electionapp.android.ui.main.fragments.verifypvcdata.PVCVerificationViewModel
import com.electionapp.android.utils.ViewModelFactory
import com.electionapp.android.utils.mapper.UserMapper
import com.electionapp.domain.usecase.pvc.VerifyPVCUseCase
import com.electionapp.domain.usecase.usecase.FetchCurrentUserUseCase
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 14/05/2018.
 */

@Module
class PVCVerificationModule {

    @FragmentScope
    @Provides
    fun providesLoginVMFactory(useCase: VerifyPVCUseCase, fetchCurrentUserUseCase: FetchCurrentUserUseCase, userMapper: UserMapper, navigator: IMainNavigator): ViewModelFactory<PVCVerificationViewModel> {
        return ViewModelFactory(lazyOf(PVCVerificationViewModel(useCase, fetchCurrentUserUseCase,
                userMapper, navigator)))
    }

    @FragmentScope
    @Provides
    fun providesLoginVM(viewModelFactory: ViewModelFactory<PVCVerificationViewModel>, fragment: PVCVerificationFragment): PVCVerificationViewModel {
        return ViewModelProviders.of(fragment, viewModelFactory).get(PVCVerificationViewModel::class.java)
    }

}