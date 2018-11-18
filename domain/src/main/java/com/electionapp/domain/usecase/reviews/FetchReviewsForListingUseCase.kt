package com.electionapp.domain.usecase.reviews


import com.electionapp.data.contracts.IReviewsRepository
import com.electionapp.domain.base.Params
import com.electionapp.domain.base.Schedulers
import com.electionapp.domain.base.UseCase
import com.electionapp.domain.entities.ReviewModel
import com.electionapp.domain.mapper.ReviewModelMapper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 14/05/2018.
 */

class FetchReviewsForListingUseCase @Inject constructor(schedulers: Schedulers,
                                                        var repository: IReviewsRepository,
                                                        var reviewModelMapper: ReviewModelMapper)

    : UseCase<Params, List<ReviewModel>>(schedulers) {

    override fun buildObservable(params: Params?): Observable<List<ReviewModel>> {
        return repository.fetchReviewsForListing(params!!.getParameters()).map {
            reviewModelMapper.mapFromList(it)
        }
    }

}