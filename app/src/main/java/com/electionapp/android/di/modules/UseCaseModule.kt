package com.electionapp.android.di.modules


import com.electionapp.data.contracts.*
import com.electionapp.domain.base.Schedulers
import com.electionapp.domain.mapper.PVCDataModelMapper
import com.electionapp.domain.mapper.UserModelMapper
import com.electionapp.domain.usecase.auth.*
import com.electionapp.domain.usecase.pvc.FetchPVCDataUseCase
import com.electionapp.domain.usecase.pvc.VerifyPVCUseCase
import com.electionapp.domain.usecase.user.FetchCurrentUserUseCase
import com.electionapp.domain.usecase.user.FetchIfCurrentUserIsAdminUseCase
import com.electionapp.domain.usecase.user.LogUserOutUseCase
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


    @Provides
    fun provideFetchPVCDataUseCase(schedulers: Schedulers, pvcDataModelMapper: PVCDataModelMapper, pvcDataRepository: IPVCDataRepository): FetchPVCDataUseCase {
        return FetchPVCDataUseCase(schedulers, pvcDataModelMapper, pvcDataRepository)
    }

    @Provides
    fun provideFetchCurrentUserWithTokenUserUseCase(schedulers: Schedulers, authService: IAuthService): FetchCurrentUserWithTokenUserUseCase {
        return FetchCurrentUserWithTokenUserUseCase(schedulers, authService)
    }

    @Provides
    fun provideFetchIfCurrentUserIsAdminUseCase(schedulers: Schedulers, userAccountTypeManager: IUserAccountTypeManager): FetchIfCurrentUserIsAdminUseCase {
        return FetchIfCurrentUserIsAdminUseCase(schedulers, userAccountTypeManager)
    }

    @Provides
    fun provideLogUserOutUseCase(schedulers: Schedulers, userAccountTypeManager: IUserAccountTypeManager, userIDManager: IUserIDManager): LogUserOutUseCase {
        return LogUserOutUseCase(schedulers, userIDManager, userAccountTypeManager)
    }

}