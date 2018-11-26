package com.electionapp.android.di.auth.emailsignup


import android.arch.lifecycle.ViewModelProviders
import com.electionapp.android.ui.auth.IAuthNavigator
import com.electionapp.android.ui.auth.fragments.signup.emailsignup.EmailSignUpFragment
import com.electionapp.android.ui.auth.fragments.signup.emailsignup.EmailSignUpViewModel
import com.electionapp.android.utils.ViewModelFactory
import com.electionapp.domain.usecase.auth.SignUserUpViaEmailUseCase
import com.electionapp.android.di.scopes.FragmentScope
import com.electionapp.android.utils.mapper.LGAMapper
import com.electionapp.domain.usecase.main.FetchLGADataUseCase
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