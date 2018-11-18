package com.electionapp.data.contracts

import com.electionapp.data.model.ListingEntity
import io.reactivex.Observable

interface IListingsRepository {

    fun searchListings(hashMap: Map<String, Any>): Observable<List<ListingEntity>>

}