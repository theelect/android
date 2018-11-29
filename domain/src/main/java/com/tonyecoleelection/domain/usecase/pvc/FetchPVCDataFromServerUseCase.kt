package com.tonyecoleelection.domain.usecase.pvc


import com.tonyecoleelection.data.contracts.IPVCDataRepository
import com.tonyecoleelection.domain.base.Params
import com.tonyecoleelection.domain.base.Schedulers
import com.tonyecoleelection.domain.base.UseCase
import com.tonyecoleelection.domain.entities.PVCDataModel
import com.tonyecoleelection.domain.mapper.PVCDataModelMapper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 14/05/2018.
 */

class FetchPVCDataFromServerUseCase @Inject constructor(schedulers: Schedulers,
                                                        var pvcDataModelMapper: PVCDataModelMapper,
                                                        var repository: IPVCDataRepository)
    : UseCase<Params, List<PVCDataModel>>(schedulers) {

    override fun buildObservable(params: Params?): Observable<List<PVCDataModel>> {
        return repository.fetchAllPVCDataWithFiltersFromServer(params!!.getParameters()).map {
            pvcDataModelMapper.mapFromList(it)
        }
    }

}