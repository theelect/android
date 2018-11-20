package com.electionapp.data.repositories.verification

import android.annotation.SuppressLint
import com.electionapp.data.contracts.IPVCDataRepository
import com.electionapp.data.model.PVCDataEntity
import com.electionapp.data.network.ApiService
import com.electionapp.data.room.dao.PVCDao
import io.reactivex.Observable

class PVCDataRepository(var apiService: ApiService,
                        var pvcDao: PVCDao) : IPVCDataRepository {


    override fun fetchAllPVCDataWithFiltersFromDB(hashMap: Map<String, Any>): Observable<List<PVCDataEntity>> {
        return pvcDao.getPVCDataList().toObservable()
    }

    @SuppressLint("CheckResult")
    override fun fetchAllPVCDataWithFiltersFromServer(hashMap: Map<String, Any>): Observable<List<PVCDataEntity>> {
        return apiService.getAllVerifiedPVCWithFilters(hashMap).map {
            if (it.success == 0) {
                pvcDao.savePVCData(it.data)
            }
            it.data
        }
    }


}