package com.tonyecoleelection.domain.usecase.pvc


import com.tonyecoleelection.data.contracts.IPVCStatsRepository
import com.tonyecoleelection.domain.base.Params
import com.tonyecoleelection.domain.base.Schedulers
import com.tonyecoleelection.domain.base.UseCase
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 14/05/2018.
 */

class FetchPVCCountServerUseCase @Inject constructor(schedulers: Schedulers,
                                                     var repository: IPVCStatsRepository)
    : UseCase<Params, Int>(schedulers) {

    override fun buildObservable(params: Params?): Observable<Int> {
        return repository.fetchPVCCount()
    }

}