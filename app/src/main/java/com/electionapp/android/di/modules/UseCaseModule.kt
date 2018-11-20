package com.electionapp.android.di.modules


import com.electionapp.data.contracts.IAuthService
import com.electionapp.data.contracts.IPVCVerificationService
import com.electionapp.data.contracts.IUserCache
import com.electionapp.data.repositories.verification.PVCVerificationService
import com.electionapp.domain.base.Schedulers
import com.electionapp.domain.mapper.UserModelMapper
import com.electionapp.domain.usecase.auth.*
import com.electionapp.domain.usecase.pvc.VerifyPVCUseCase
import com.electionapp.domain.usecase.usecase.FetchCurrentUserUseCase
import com.softcom.abujametrodata.contracts.ITokenManager
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 26/05/2018.
 */

@Module
class UseCaseModule {

    @Provides
    fun provideLoginUseCase(schedulers: Schedulers, authService: IAuthService): LogInUseCase {
        return LogInUseCase(schedulers, authService)
    }


    @Provides
    fun provideUserSignUpCheckUseCase(schedulers: Schedulers, authService: IAuthService): SignUserUpViaEmailUseCase {
        return SignUserUpViaEmailUseCase(schedulers, authService)
    }

    @Provides
    fun providesRequestPasswordResetUseCase(schedulers: Schedulers, authService: IAuthService): RequestPasswordResetUseCase {
        return RequestPasswordResetUseCase(schedulers, authService)
    }

    @Provides
    fun providesCheckUserIsSignedInUseCase(schedulers: Schedulers, tokenManager: ITokenManager): CheckUserIsSignedInUseCase {
        return CheckUserIsSignedInUseCase(schedulers, tokenManager)
    }


    @Provides
    fun providesVerifyPVCUseCase(schedulers: Schedulers, pvcVerificationService: IPVCVerificationService): VerifyPVCUseCase {
        return VerifyPVCUseCase(schedulers, pvcVerificationService)
    }


    @Provides
    fun providesFetchCurrentUserUseCase(schedulers: Schedulers, userCache: IUserCache, userModelMapper: UserModelMapper): FetchCurrentUserUseCase {
        return FetchCurrentUserUseCase(schedulers, userCache, userModelMapper)
    }

}