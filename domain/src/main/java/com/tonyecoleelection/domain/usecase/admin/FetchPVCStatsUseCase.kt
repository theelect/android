package com.tonyecoleelection.domain.usecase.admin


import com.tonyecoleelection.data.contracts.IPVCStatsRepository
import com.tonyecoleelection.domain.base.Params
import com.tonyecoleelection.domain.base.Schedulers
import com.tonyecoleelection.domain.base.UseCase
import com.tonyecoleelection.domain.entities.StatItemModel
import com.tonyecoleelection.domain.mapper.PVCStatsModelMapper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 14/05/2018.
 */

class FetchPVCStatsUseCase @Inject constructor(schedulers: Schedulers,
                                               var mapper: PVCStatsModelMapper,
                                               var repository: IPVCStatsRepository)
    : UseCase<Params, List<StatItemModel>>(schedulers) {

    override fun buildObservable(params: Params?): Observable<List<StatItemModel>> {
        return repository.fetchAllPVCStatsWithFilters(params!!.getParameters()).map {
            mapper.mapFromList(it)
        }
    }

}