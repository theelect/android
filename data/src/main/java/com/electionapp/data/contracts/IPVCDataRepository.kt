package com.electionapp.data.contracts

import com.electionapp.data.model.LGAEntity
import com.electionapp.data.model.PVCDataEntity
import io.reactivex.Observable


interface IPVCDataRepository {

    fun fetchAllPVCDataWithFiltersFromDB(hashMap: Map<String, Any>): Observable<List<PVCDataEntity>>

    fun fetchAllPVCDataWithFiltersFromServer(hashMap: Map<String, Any>): Observable<List<PVCDataEntity>>

    fun savePVCData(pvcDataEntity: PVCDataEntity)

    fun fetchAllLGASServer(hashMap: Map<String, Any>): Observable<List<LGAEntity>>

    fun fetchAllOccupationsFromServer(hashMap: Map<String, Any>): Observable<List<String>>

}