package com.electionapp.android.di.auth.login


import android.arch.lifecycle.ViewModelProviders
import com.electionapp.android.di.scopes.FragmentScope
import com.electionapp.android.ui.auth.IAuthNavigator
import com.electionapp.android.ui.auth.fragments.login.LoginFragment
import com.electionapp.android.ui.auth.fragments.login.LoginViewModel
import com.electionapp.android.utils.ViewModelFactory
import com.electionapp.domain.usecase.auth.LogInUseCase
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 14/05/2018.
 */

@Module
class LoginModule {


    @FragmentScope
    @Provides
    fun providesLoginVMFactory(useCase: LogInUseCase,
                               loginNavigator: IAuthNavigator): ViewModelFactory<LoginViewModel> {
        return ViewModelFactory(lazyOf(LoginViewModel(useCase, loginNavigator)))
    }

    @FragmentScope
    @Provides
    fun providesLoginVM(viewModelFactory: ViewModelFactory<LoginViewModel>, loginFragment: LoginFragment): LoginViewModel {
        return ViewModelProviders.of(loginFragment, viewModelFactory).get(LoginViewModel::class.java)
    }


}