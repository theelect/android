package com.tonyecoleelection.domain.usecase.main


import com.tonyecoleelection.data.contracts.IPVCDataRepository
import com.tonyecoleelection.domain.base.Params
import com.tonyecoleelection.domain.base.Schedulers
import com.tonyecoleelection.domain.base.UseCase
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 14/05/2018.
 */

class FetchAllOccupationsFromServerUseCase @Inject constructor(schedulers: Schedulers,
                                                               var repository: IPVCDataRepository)
    : UseCase<Params, List<String>>(schedulers) {

    override fun buildObservable(params: Params?): Observable<List<String>> {
        return repository.fetchAllOccupationsFromServer(params!!.getParameters())
    }

}