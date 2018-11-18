package com.electionapp.domain.usecase.listings


import com.electionapp.domain.base.Params
import com.electionapp.domain.base.Schedulers
import com.electionapp.domain.base.UseCase
import com.electionapp.domain.entities.ListingModel
import com.electionapp.domain.mapper.ListingModelMapper
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * Created by aliumujib on 14/05/2018.
 */

class FetchHomePageListingGroupsUseCase @Inject constructor(schedulers: Schedulers,
                                                            var fetchRecommendedListingsUseCase: FetchRecommendedListingsUseCase,
                                                            var fetchOtherListingsUseCase: FetchOtherListingsUseCase,
                                                            var listingModelMapper: ListingModelMapper)

    : UseCase<Params, List<List<ListingModel>>>(schedulers) {

    override fun buildObservable(params: Params?): Observable<List<List<ListingModel>>> {
        return Observable.zip(fetchRecommendedListingsUseCase.execute(params), fetchOtherListingsUseCase.execute(params),
                BiFunction { list1:List<ListingModel>, list2: List<ListingModel> ->
            val listOflists = listOf(list1, list2)
            return@BiFunction listOflists
        })
    }

}