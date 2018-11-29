package com.tonyecoleelection.android.di.auth.passwordreset


import android.arch.lifecycle.ViewModelProviders
import com.tonyecoleelection.android.ui.auth.IAuthNavigator
import com.tonyecoleelection.android.ui.auth.fragments.resetpassword.PasswordResetFragment
import com.tonyecoleelection.android.ui.auth.fragments.resetpassword.PasswordResetViewModel
import com.tonyecoleelection.android.utils.ViewModelFactory
import com.tonyecoleelection.domain.usecase.auth.RequestPasswordResetUseCase
import com.tonyecoleelection.android.di.scopes.FragmentScope
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