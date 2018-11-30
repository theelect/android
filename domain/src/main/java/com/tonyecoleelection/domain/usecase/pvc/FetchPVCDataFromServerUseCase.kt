package com.tonyecoleelection.domain.usecase.pvc


import com.tonyecoleelection.data.contracts.IPVCDataRepository
import com.tonyecoleelection.domain.base.Params
import com.tonyecoleelection.domain.base.Schedulers
import com.tonyecoleelection.domain.base.UseCase
import com.tonyecoleelection.domain.entities.PVCDataModel
import com.tonyecoleelection.domain.entities.VoterDataModel
import com.tonyecoleelection.domain.mapper.VoterDataModelMapper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 14/05/2018.
 */

class FetchPVCDataFromServerUseCase @Inject constructor(schedulers: Schedulers,
                                                        var dataModelMapper: VoterDataModelMapper,
                                                        var repository: IPVCDataRepository)
    : UseCase<Params, VoterDataModel>(schedulers) {

    override fun buildObservable(params: Params?): Observable<VoterDataModel> {
        return repository.fetchAllPVCDataWithFiltersFromServer(params!!.getParameters()).map {
            dataModelMapper.mapFrom(it)
        }
    }

}