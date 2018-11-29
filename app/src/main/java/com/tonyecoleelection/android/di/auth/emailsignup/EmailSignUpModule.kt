package com.tonyecoleelection.android.di.auth.emailsignup


import android.arch.lifecycle.ViewModelProviders
import com.tonyecoleelection.android.ui.auth.IAuthNavigator
import com.tonyecoleelection.android.ui.auth.fragments.signup.emailsignup.EmailSignUpFragment
import com.tonyecoleelection.android.ui.auth.fragments.signup.emailsignup.EmailSignUpViewModel
import com.tonyecoleelection.android.utils.ViewModelFactory
import com.tonyecoleelection.domain.usecase.auth.SignUserUpViaEmailUseCase
import com.tonyecoleelection.android.di.scopes.FragmentScope
import com.tonyecoleelection.android.utils.mapper.LGAMapper
import com.tonyecoleelection.domain.usecase.main.FetchLGADataUseCase
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 14/05/2018.
 */

@Module
class EmailSignUpModule {

    @FragmentScope
    @Provides
    fun providesLoginVMFactory(useCase: SignUserUpViaEmailUseCase,
                               fetchLGADataUseCase: FetchLGADataUseCase,
                               lgaMapper: LGAMapper,
                               loginNavigator: IAuthNavigator): ViewModelFactory<EmailSignUpViewModel> {
        return ViewModelFactory(lazyOf(EmailSignUpViewModel(useCase, fetchLGADataUseCase, lgaMapper, loginNavigator)))
    }

    @FragmentScope
    @Provides
    fun providesLoginVM(viewModelFactory: ViewModelFactory<EmailSignUpViewModel>, emailSignUpFragment: EmailSignUpFragment): EmailSignUpViewModel {
        return ViewModelProviders.of(emailSignUpFragment, viewModelFactory).get(EmailSignUpViewModel::class.java)
    }

}