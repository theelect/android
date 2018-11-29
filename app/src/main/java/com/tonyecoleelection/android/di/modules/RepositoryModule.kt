package com.tonyecoleelection.android.di.modules


import androidx.work.WorkManager
import com.tonyecoleelection.android.di.scopes.ApplicationScope
import com.tonyecoleelection.data.contracts.*
import com.tonyecoleelection.data.repositories.auth.AuthService
import com.tonyecoleelection.data.repositories.manager.UserAccountTypeManager
import com.tonyecoleelection.data.repositories.stats.PVCStatsRepository
import com.tonyecoleelection.data.repositories.user.UserCache
import com.tonyecoleelection.data.repositories.verification.PVCDataRepository
import com.tonyecoleelection.data.repositories.verification.PVCVerificationService
import dagger.Module
import dagger.Provides

/**
 * Created by aliumujib on 26/05/2018.
 */

@Module(includes = [RoomModule::class, NetworkModule::class])
class RepositoryModule {

    @ApplicationScope
    @Provides
    fun provideWorkManager(): WorkManager {
        return WorkManager.getInstance()
    }

    @ApplicationScope
    @Provides
    fun provideVerificationService(pvcVerificationService: PVCVerificationService): IPVCVerificationService {
        return pvcVerificationService
    }


    @ApplicationScope
    @Provides
    fun providePVCDataRepository(pVCDataRepository: PVCDataRepository): IPVCDataRepository {
        return pVCDataRepository
    }

    @ApplicationScope
    @Provides
    fun providePVCStatsRepository(repository: PVCStatsRepository): IPVCStatsRepository {
        return repository
    }

    @ApplicationScope
    @Provides
    fun provideAuthenticationService(authService: AuthService): IAuthService {
        return authService
    }

    @ApplicationScope
    @Provides
    fun provideUserRepository(userCache: UserCache): IUserCache {
        return userCache
    }

    @Provides
    fun provideIUserAccountTypeManager(userAccountTypeManager: UserAccountTypeManager): IUserAccountTypeManager {
        return userAccountTypeManager
    }

}