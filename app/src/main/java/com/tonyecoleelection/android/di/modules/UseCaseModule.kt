package com.tonyecoleelection.android.di.modules


import com.tonyecoleelection.data.contracts.*
import com.tonyecoleelection.domain.base.Schedulers
import com.tonyecoleelection.domain.usecase.admin.FetchPVCStatsUseCase
import com.tonyecoleelection.domain.usecase.auth.*
import com.tonyecoleelection.domain.usecase.main.FetchAllOccupationsFromServerUseCase
import com.tonyecoleelection.domain.usecase.main.FetchLGADataUseCase
import com.tonyecoleelection.domain.usecase.pvc.FetchPVCDataFromCacheUseCase
import com.tonyecoleelection.domain.usecase.pvc.FetchPVCDataFromServerUseCase
import com.tonyecoleelection.domain.usecase.pvc.VerifyPVCUseCase
import com.tonyecoleelection.domain.usecase.user.FetchCurrentUserUseCase
import com.tonyecoleelection.domain.usecase.user.FetchIfCurrentUserIsAdminUseCase
import com.tonyecoleelection.domain.usecase.user.LogUserOutUseCase
import com.softcom.abujametrodata.contracts.ITokenManager
import com.tonyecoleelection.domain.mapper.*
import com.tonyecoleelection.domain.usecase.admin.FetchPVCAgeStatsUseCase
import com.tonyecoleelection.domain.usecase.pvc.FetchPVCCountServerUseCase
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
    fun providesPasswordResetUseCase(schedulers: Schedulers, authService: IAuthService): PasswordResetUseCase {
        return PasswordResetUseCase(schedulers, authService)
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
    fun provideFetchPVCDataUseCase(schedulers: Schedulers, pvcDataModelMapper: PVCDataModelMapper, pvcDataRepository: IPVCDataRepository): FetchPVCDataFromCacheUseCase {
        return FetchPVCDataFromCacheUseCase(schedulers, pvcDataModelMapper, pvcDataRepository)
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
    fun provideLogUserOutUseCase(schedulers: Schedulers, userCache: IUserCache, tokenManager: ITokenManager, userAccountTypeManager: IUserAccountTypeManager, userIDManager: IUserIDManager): LogUserOutUseCase {
        return LogUserOutUseCase(schedulers, userIDManager, userCache, tokenManager, userAccountTypeManager)
    }

    @Provides
    fun provideFetchPVCStatsUseCase(schedulers: Schedulers, mapper: PVCStatsModelMapper, repository: IPVCStatsRepository): FetchPVCStatsUseCase {
        return FetchPVCStatsUseCase(schedulers, mapper, repository)
    }

    @Provides
    fun provideFetchPVCCountServerUseCase(schedulers: Schedulers, repository: IPVCStatsRepository): FetchPVCCountServerUseCase {
        return FetchPVCCountServerUseCase(schedulers, repository)
    }

    @Provides
    fun provideFetchPVCDataFromServerUseCase(schedulers: Schedulers, mapper: VoterDataModelMapper, repository: IPVCDataRepository): FetchPVCDataFromServerUseCase {
        return FetchPVCDataFromServerUseCase(schedulers, mapper, repository)
    }

    @Provides
    fun provideFetchAllOccupationsFromServerUseCase(schedulers: Schedulers, repository: IPVCDataRepository): FetchAllOccupationsFromServerUseCase {
        return FetchAllOccupationsFromServerUseCase(schedulers, repository)
    }

    @Provides
    fun provideFetchLGADataUseCase(schedulers: Schedulers, lgaModelMapper: LGAModelMapper, repository: IPVCDataRepository): FetchLGADataUseCase {
        return FetchLGADataUseCase(schedulers, lgaModelMapper, repository)
    }

    @Provides
    fun provideFetchPVCAgeStatsUseCase(schedulers: Schedulers, modelMapper: PVCStatsModelMapper, repository: IPVCStatsRepository): FetchPVCAgeStatsUseCase {
        return FetchPVCAgeStatsUseCase(schedulers, modelMapper, repository)
    }

}