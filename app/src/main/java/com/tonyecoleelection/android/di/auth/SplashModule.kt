package com.tonyecoleelection.android.di.auth

import com.tonyecoleelection.domain.usecase.auth.CheckUserIsSignedInUseCase
import com.tonyecoleelection.android.ui.auth.*
import com.tonyecoleelection.android.di.scopes.ActivitiesScope
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