package com.electionapp.data.repositories.verification

import com.electionapp.data.contracts.IPVCDataRepository
import com.electionapp.data.contracts.IUserIDManager
import com.electionapp.data.model.LGAEntity
import com.electionapp.data.model.PVCDataEntity
import com.electionapp.data.network.ApiService
import com.electionapp.data.repositories.manager.UserIDManager
import com.electionapp.data.room.dao.PVCDao
import io.reactivex.Observable
import javax.inject.Inject

class PVCDataRepository @Inject constructor(var apiService: ApiService,
                                            var pvcDao: PVCDao) : IPVCDataRepository {

    override fun fetchAllLGASServer(hashMap: Map<String, Any>): Observable<List<LGAEntity>> {
        return apiService.getAllLGAsWithWards(hashMap)
    }

    override fun fetchAllOccupationsFromServer(hashMap: Map<String, Any>): Observable<List<String>> {
    return apiService.getAllOccupations(hashMap)
    }

    override fun savePVCData(pvcDataEntity: PVCDataEntity) {
        pvcDao.savePVCData(pvcDataEntity)
    }

    override fun fetchAllPVCDataWithFiltersFromDB(hashMap: Map<String, Any>): Observable<List<PVCDataEntity>> {
        return pvcDao.getPVCDataList().toObservable()
    }

    override fun fetchAllPVCDataWithFiltersFromServer(hashMap: Map<String, Any>): Observable<List<PVCDataEntity>> {
        return apiService.getAllVerifiedPVCWithFilters(hashMap).map {
            it.docs
        }
    }


}