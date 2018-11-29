package com.tonyecoleelection.android.di.auth.login


import android.arch.lifecycle.ViewModelProviders
import com.tonyecoleelection.android.di.scopes.FragmentScope
import com.tonyecoleelection.android.ui.auth.IAuthNavigator
import com.tonyecoleelection.android.ui.auth.fragments.login.LoginFragment
import com.tonyecoleelection.android.ui.auth.fragments.login.LoginViewModel
import com.tonyecoleelection.android.utils.ViewModelFactory
import com.tonyecoleelection.domain.usecase.auth.LogInUseCase
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