package com.electionapp.data.repositories.reviews

import com.electionapp.constants.Constants
import com.electionapp.data.contracts.IReviewsRepository
import com.electionapp.data.model.ReviewEntity
import com.electionapp.data.network.ApiService
import io.reactivex.Observable

class ReviewsRepository(var apiService: ApiService) : IReviewsRepository {

    override fun fetchReviewsForListing(hashMap: Map<String, Any>): Observable<List<ReviewEntity>> {
        val listingID = hashMap[Constants.REVIEW_CONSTANTS.LISTING_ID] as String
        return apiService.fetchReviewsForListing(listingID).map {
            it.reviews
        }
    }

}