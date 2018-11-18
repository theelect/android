package com.softcom.abujametro.di.modules


import androidx.work.WorkManager
import com.electionapp.android.di.modules.NetworkModule
import com.electionapp.android.di.modules.RoomModule
import com.electionapp.data.contracts.IAuthService
import com.electionapp.data.contracts.IListingsRepository
import com.electionapp.data.contracts.IReviewsRepository
import com.electionapp.data.contracts.IUserCache
import com.electionapp.data.network.ApiService
import com.electionapp.data.repositories.auth.AuthService
import com.electionapp.data.repositories.listings.ListingsRepository
import com.electionapp.data.repositories.reviews.ReviewsRepository
import com.electionapp.data.repositories.user.UserCache
import com.electionapp.android.di.scopes.ApplicationScope
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
    fun provideAuthenticationService(authService: AuthService): IAuthService {
        return authService
    }

    @ApplicationScope
    @Provides
    fun provideUserRepository(userCache: UserCache): IUserCache {
        return userCache
    }

    @ApplicationScope
    @Provides
    fun providesListingsRepository(apiService: ApiService): IListingsRepository {
        return ListingsRepository(apiService)
    }

    @ApplicationScope
    @Provides
    fun provideReviewsRepository(apiService: ApiService): IReviewsRepository {
        return ReviewsRepository(apiService)
    }

//    @ApplicationScope
//    @Provides
//    fun provideTicketRepo(ticketDao: TicketDao, invalidTicketDao: InvalidTicketDao,
//                          ticketSyncTaskScheduler: TicketSyncTaskScheduler,
//                          apiService: ApiService): ITicketRepository {
//        return TicketRepository(ticketDao, invalidTicketDao, ticketSyncTaskScheduler, apiService)
//    }

}