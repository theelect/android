package com.tonyecoleelection.data.contracts

import com.tonyecoleelection.data.model.LGAEntity
import com.tonyecoleelection.data.model.PVCDataEntity
import com.tonyecoleelection.data.network.reponses.VoterDataPagingResponse
import io.reactivex.Observable


interface IPVCDataRepository {

    fun fetchAllPVCDataWithFiltersFromDB(hashMap: Map<String, Any>): Observable<List<PVCDataEntity>>

    fun fetchAllPVCDataWithFiltersFromServer(hashMap: Map<String, Any>): Observable<VoterDataPagingResponse>

    fun savePVCData(pvcDataEntity: PVCDataEntity)

    fun fetchAllLGASServer(hashMap: Map<String, Any>): Observable<List<LGAEntity>>

    fun fetchAllOccupationsFromServer(hashMap: Map<String, Any>): Observable<List<String>>

}