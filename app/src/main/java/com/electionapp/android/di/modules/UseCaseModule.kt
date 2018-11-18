package com.electionapp.android.di.modules


import com.electionapp.data.contracts.IAuthService
import com.electionapp.data.contracts.IListingsRepository
import com.electionapp.data.contracts.IReviewsRepository
import com.electionapp.domain.base.Schedulers
import com.electionapp.domain.usecase.auth.*
import com.electionapp.domain.mapper.ListingModelMapper
import com.electionapp.domain.mapper.ReviewModelMapper
import com.electionapp.domain.usecase.listings.FetchOtherListingsUseCase
import com.electionapp.domain.usecase.listings.FetchRecommendedListingsUseCase
import com.electionapp.domain.usecase.listings.FetchSimilarListingsUseCase
import com.electionapp.domain.usecase.reviews.FetchReviewsForListingUseCase
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
    fun provideSaveFacebookTokenToServerUseCase(schedulers: Schedulers, authService: IAuthService): SaveTokenToServerUseCase {
        return SaveTokenToServerUseCase(schedulers, authService)
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
    fun providesFetchOtherListingsUseCase(schedulers: Schedulers,
                                          listingsRepository: IListingsRepository,
                                          listingModelMapper: ListingModelMapper): FetchOtherListingsUseCase {
        return FetchOtherListingsUseCase(schedulers, listingsRepository, listingModelMapper)
    }


    @Provides
    fun providesFetchRecommendedListingsUseCase(schedulers: Schedulers,
                                                listingsRepository: IListingsRepository,
                                                listingModelMapper: ListingModelMapper): FetchRecommendedListingsUseCase {
        return FetchRecommendedListingsUseCase(schedulers, listingsRepository, listingModelMapper)
    }


    @Provides
    fun providesFetchSimilarListingsUseCase(schedulers: Schedulers,
                                                   listingsRepository: IListingsRepository,
                                                   listingModelMapper: ListingModelMapper): FetchSimilarListingsUseCase {
        return FetchSimilarListingsUseCase(schedulers, listingsRepository, listingModelMapper)
    }


    @Provides
    fun providesFetchReviewsForListingUseCase(schedulers: Schedulers,
                                              reviewsRepository: IReviewsRepository,
                                              reviewModelMapper: ReviewModelMapper): FetchReviewsForListingUseCase {
        return FetchReviewsForListingUseCase(schedulers, reviewsRepository, reviewModelMapper)
    }
}