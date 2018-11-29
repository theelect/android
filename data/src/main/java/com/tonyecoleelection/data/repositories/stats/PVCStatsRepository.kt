package com.tonyecoleelection.data.repositories.stats

import com.tonyecoleelection.data.contracts.IPVCStatsRepository
import com.tonyecoleelection.data.model.StatItemEntity
import com.tonyecoleelection.data.network.ApiService
import io.reactivex.Observable
import javax.inject.Inject


class PVCStatsRepository @Inject constructor(var apiService: ApiService) : IPVCStatsRepository {

    override fun fetchAllPVCStatsWithFilters(hashMap: Map<String, Any>): Observable<List<StatItemEntity>> {
        return apiService.getAllVerifiedPVCStatsWithFilters(hashMap)
    }

}