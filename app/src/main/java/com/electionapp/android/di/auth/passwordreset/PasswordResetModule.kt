package com.electionapp.android.di.auth.passwordreset


import android.arch.lifecycle.ViewModelProviders
import com.electionapp.android.ui.auth.IAuthNavigator
import com.electionapp.android.ui.auth.fragments.resetpassword.PasswordResetFragment
import com.electionapp.android.ui.auth.fragments.resetpassword.PasswordResetViewModel
import com.electionapp.android.utils.ViewModelFactory
import com.electionapp.domain.usecase.auth.RequestPasswordResetUseCase
import com.electionapp.android.di.scopes.FragmentScope
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 14/05/2018.
 */

@Module
class PasswordResetModule {

    @FragmentScope
    @Provides
    fun providesOnBoardingViewModelFactory(authNavigator: IAuthNavigator, requestPasswordResetUseCase: RequestPasswordResetUseCase): ViewModelFactory<PasswordResetViewModel> {
        return ViewModelFactory(lazyOf(PasswordResetViewModel(authNavigator, requestPasswordResetUseCase)))
    }

    @FragmentScope
    @Provides
    fun providesOnBoardingViewModel(viewModelFactory: ViewModelFactory<PasswordResetViewModel>, passwordResetFragment: PasswordResetFragment): PasswordResetViewModel {
        return ViewModelProviders.of(passwordResetFragment, viewModelFactory).get(PasswordResetViewModel::class.java)
    }
}