package com.electionapp.domain.usecase.pvc


import com.electionapp.data.contracts.IPVCDataRepository
import com.electionapp.domain.base.Params
import com.electionapp.domain.base.Schedulers
import com.electionapp.domain.base.UseCase
import com.electionapp.domain.entities.PVCDataModel
import com.electionapp.domain.mapper.PVCDataModelMapper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 14/05/2018.
 */

class FetchPVCDataUseCase @Inject constructor(schedulers: Schedulers,
                                              var pvcDataModelMapper: PVCDataModelMapper,
                                              var repository: IPVCDataRepository)
    : UseCase<Params, List<PVCDataModel>>(schedulers) {

    override fun buildObservable(params: Params?): Observable<List<PVCDataModel>> {
        return repository.fetchAllPVCDataWithFiltersFromDB(params!!.getParameters()).map {
            pvcDataModelMapper.mapFromList(it)
        }
    }

}