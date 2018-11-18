package com.electionapp.android.di.modules


import com.electionapp.data.network.ApiClient
import com.electionapp.data.network.ApiService
import com.electionapp.data.network.AuthInterceptor
import com.electionapp.data.repositories.manager.TokenManager
import com.electionapp.data.repositories.manager.UserIDManager
import com.softcom.abujametrodata.contracts.ITokenManager
import com.electionapp.data.contracts.IUserIDManager
import com.electionapp.android.di.scopes.ApplicationScope

import dagger.Module
import dagger.Provides


/**
 * Created by aliumujib on 12/05/2018.
 */

@Module
class NetworkModule {
    @ApplicationScope
    @Provides
    fun providesTokenManager(tokenManager: TokenManager): ITokenManager {
        return tokenManager
    }

    @ApplicationScope
    @Provides
    fun providesUserIdManager(userIdManager: UserIDManager): IUserIDManager {
        return userIdManager
    }

    @ApplicationScope
    @Provides
    fun provideApiClient(authInterceptor: AuthInterceptor): ApiClient {
        return ApiClient(authInterceptor)
    }

    @ApplicationScope
    @Provides
    fun provideApi(apiClient: ApiClient): ApiService {
        return apiClient.service
    }

    @ApplicationScope
    @Provides
    fun provideAuthInterceptor(tokenManager: ITokenManager): AuthInterceptor {
        return AuthInterceptor(tokenManager)
    }

}
