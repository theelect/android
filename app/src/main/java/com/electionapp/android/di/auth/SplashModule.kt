package com.electionapp.android.di.auth

import com.electionapp.domain.usecase.auth.CheckUserIsSignedInUseCase
import com.electionapp.android.ui.auth.*
import com.electionapp.android.di.scopes.ActivitiesScope
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 14/05/2018.
 */

@Module
class SplashModule {

    @Provides
    @ActivitiesScope
    fun providesNavigator(checkUserIsSignedInUseCase: CheckUserIsSignedInUseCase, splashActivity: SplashActivity): ISplashNavigator {
        return SplashNavigator(checkUserIsSignedInUseCase, splashActivity)
    }

}