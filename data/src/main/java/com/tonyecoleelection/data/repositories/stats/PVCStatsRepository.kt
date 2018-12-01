package com.tonyecoleelection.data.repositories.stats

import com.tonyecoleelection.data.contracts.IPVCStatsRepository
import com.tonyecoleelection.data.model.StatItemEntity
import com.tonyecoleelection.data.network.ApiService
import io.reactivex.Observable
import javax.inject.Inject


class PVCStatsRepository @Inject constructor(var apiService: ApiService) : IPVCStatsRepository {

    override fun fetchAgeGroupStats(): Observable<List<StatItemEntity>> {
        return apiService.pvcAgeStats().map {
            val stats = mutableListOf<StatItemEntity>()
            stats.add(StatItemEntity(it.eighteen_to_thirty, "18 - 30",0.0))
            stats.add(StatItemEntity(it.thirty_one_to_fourty, "31 - 40",0.0))
            stats.add(StatItemEntity(it.fourty_one_to_fifty, "41 - 50",0.0))
            stats.add(StatItemEntity(it.fifty_one_to_sixty, "51 - 60",0.0))
            stats.add(StatItemEntity(it.sixty_one_to_hundred, "61 - 100",0.0))
            return@map stats
        }
    }

    override fun fetchPVCCount(): Observable<Int> {
        return apiService.pvcCount().map {
            it.total_verified + it.total_unverified
        }
    }

    override fun fetchAllPVCStatsWithFilters(hashMap: Map<String, Any>): Observable<List<StatItemEntity>> {
        return apiService.getAllVerifiedPVCStatsWithFilters(hashMap)
    }

}