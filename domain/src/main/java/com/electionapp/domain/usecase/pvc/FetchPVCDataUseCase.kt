package com.electionapp.domain.usecase.pvc


import com.electionapp.data.contracts.IPVCDataRepository
import com.electionapp.data.model.PVCDataEntity
import com.electionapp.domain.base.Params
import com.electionapp.domain.base.Schedulers
import com.electionapp.domain.base.UseCase
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 14/05/2018.
 */

class FetchPVCDataUseCase @Inject constructor(schedulers: Schedulers,
                                              var repository: IPVCDataRepository)
    : UseCase<Params, List<PVCDataEntity>>(schedulers) {

    //TODO, make mapper and domain models
    override fun buildObservable(params: Params?): Observable<List<PVCDataEntity>> {
        return repository.fetchAllPVCDataWithFiltersFromServer(params!!.getParameters())
    }

}