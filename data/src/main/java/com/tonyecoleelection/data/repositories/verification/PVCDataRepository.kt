package com.tonyecoleelection.data.repositories.verification

import com.tonyecoleelection.data.contracts.IPVCDataRepository
import com.tonyecoleelection.data.model.LGAEntity
import com.tonyecoleelection.data.model.PVCDataEntity
import com.tonyecoleelection.data.network.ApiService
import com.tonyecoleelection.data.network.reponses.VoterDataPagingResponse
import com.tonyecoleelection.data.room.dao.PVCDao
import io.reactivex.Observable
import javax.inject.Inject

class PVCDataRepository @Inject constructor(var apiService: ApiService,
                                            var pvcDao: PVCDao) : IPVCDataRepository {

    override fun fetchAllLGASServer(hashMap: Map<String, Any>): Observable<List<LGAEntity>> {
        return apiService.getAllLGAsWithWards(hashMap)
    }

    override fun fetchAllOccupationsFromServer(hashMap: Map<String, Any>): Observable<List<String>> {
        return apiService.getAllOccupations(hashMap).map {
            it.filter {
                !it.isNullOrBlank()
            }
        }
    }

    override fun savePVCData(pvcDataEntity: PVCDataEntity) {
        pvcDao.savePVCData(pvcDataEntity)
    }

    override fun fetchAllPVCDataWithFiltersFromDB(hashMap: Map<String, Any>): Observable<List<PVCDataEntity>> {
        return pvcDao.getPVCDataList().toObservable()
    }

    override fun fetchAllPVCDataWithFiltersFromServer(hashMap: Map<String, Any>): Observable<VoterDataPagingResponse> {
        return apiService.getAllVerifiedPVCWithFilters(hashMap)
    }


}