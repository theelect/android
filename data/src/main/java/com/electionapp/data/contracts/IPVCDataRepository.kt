package com.electionapp.data.contracts

import com.electionapp.data.model.PVCData
import io.reactivex.Observable


interface IPVCDataRepository {

    fun fetchAllPVCDataWithFilters(hashMap: Map<String, Any>): Observable<List<PVCData>>


}