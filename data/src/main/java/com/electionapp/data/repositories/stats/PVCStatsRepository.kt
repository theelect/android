package com.electionapp.data.repositories.stats

import com.electionapp.data.contracts.IPVCStatsRepository
import com.electionapp.data.model.StatItemEntity
import com.electionapp.data.network.ApiService
import io.reactivex.Observable
import javax.inject.Inject


class PVCStatsRepository @Inject constructor(var apiService: ApiService) : IPVCStatsRepository {

    override fun fetchAllPVCStatsWithFilters(hashMap: Map<String, Any>): Observable<List<StatItemEntity>> {
        return apiService.getAllVerifiedPVCStatsWithFilters(hashMap)
    }

}