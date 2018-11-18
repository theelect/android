package com.electionapp.data.contracts

import com.electionapp.data.model.ReviewEntity
import io.reactivex.Observable

interface IReviewsRepository {

    fun fetchReviewsForListing(hashMap: Map<String, Any>): Observable<List<ReviewEntity>>

}