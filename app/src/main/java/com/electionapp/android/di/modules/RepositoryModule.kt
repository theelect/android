package com.electionapp.android.di.modules


import androidx.work.WorkManager
import com.electionapp.android.di.scopes.ApplicationScope
import com.electionapp.data.contracts.IAuthService
import com.electionapp.data.contracts.IPVCVerificationService
import com.electionapp.data.contracts.IUserCache
import com.electionapp.data.repositories.auth.AuthService
import com.electionapp.data.repositories.user.UserCache
import com.electionapp.data.repositories.verification.PVCVerificationService
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
    fun provideAuthenticationService(authService: AuthService): IAuthService {
        return authService
    }

    @ApplicationScope
    @Provides
    fun provideUserRepository(userCache: UserCache): IUserCache {
        return userCache
    }

}