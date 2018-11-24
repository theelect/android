package com.electionapp.data.contracts

import com.electionapp.data.model.PVCDataEntity
import com.electionapp.data.model.StatItemEntity
import io.reactivex.Observable


interface IPVCStatsRepository {

    fun fetchAllPVCStatsWithFilters(hashMap: Map<String, Any>): Observable<List<StatItemEntity>>


}