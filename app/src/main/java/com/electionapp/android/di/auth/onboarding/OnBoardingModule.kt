package com.electionapp.android.di.auth.onboarding


import android.arch.lifecycle.ViewModelProviders
import com.electionapp.android.ui.auth.IAuthNavigator
import com.electionapp.android.ui.auth.fragments.onboarding.OnBoardingFragment
import com.electionapp.android.ui.auth.fragments.onboarding.OnBoardingViewModel
import com.electionapp.android.utils.ViewModelFactory
import com.electionapp.android.di.scopes.FragmentScope
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 14/05/2018.
 */

@Module
class OnBoardingModule {


    @FragmentScope
    @Provides
    fun providesOnBoardingViewModelFactory(authNavigator: IAuthNavigator): ViewModelFactory<OnBoardingViewModel> {
        return ViewModelFactory(lazyOf(OnBoardingViewModel(authNavigator)))
    }

    @FragmentScope
    @Provides
    fun providesOnBoardingViewModel(viewModelFactory: ViewModelFactory<OnBoardingViewModel>, onBoardingFragment: OnBoardingFragment): OnBoardingViewModel {
        return ViewModelProviders.of(onBoardingFragment, viewModelFactory).get(OnBoardingViewModel::class.java)
    }
}