package com.electionapp.domain.usecase.listings


import com.electionapp.data.contracts.IListingsRepository
import com.electionapp.domain.base.Params
import com.electionapp.domain.base.Schedulers
import com.electionapp.domain.base.UseCase
import com.electionapp.domain.entities.ListingModel
import com.electionapp.domain.mapper.ListingModelMapper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 14/05/2018.
 */

class FetchOtherListingsUseCase @Inject constructor(schedulers: Schedulers,
                                                    var listingsRepository: IListingsRepository,
                                                    var listingModelMapper: ListingModelMapper)

    : UseCase<Params, List<ListingModel>>(schedulers) {

    override fun buildObservable(params: Params?): Observable<List<ListingModel>> {
       return listingsRepository.searchListings(params!!.getParameters()).map {
            listingModelMapper.mapFromList(it)
       }
    }

}