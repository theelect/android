package com.tonyecoleelection.data.contracts

import com.tonyecoleelection.data.model.StatItemEntity
import io.reactivex.Observable


interface IPVCStatsRepository {

    fun fetchAllPVCStatsWithFilters(hashMap: Map<String, Any>): Observable<List<StatItemEntity>>


}