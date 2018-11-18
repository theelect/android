package com.electionapp.data.repositories.listings

import com.electionapp.data.contracts.IListingsRepository
import com.electionapp.data.model.ListingEntity
import com.electionapp.data.network.ApiService
import io.reactivex.Observable

class ListingsRepository(val apiService: ApiService) : IListingsRepository {

    override fun searchListings(hashMap: Map<String, Any>): Observable<List<ListingEntity>> {
        return apiService.searchListings(hashMap).map {
            it.listings
        }
    }

}