package com.electionapp.domain.usecase.admin


import com.electionapp.data.contracts.IPVCStatsRepository
import com.electionapp.domain.base.Params
import com.electionapp.domain.base.Schedulers
import com.electionapp.domain.base.UseCase
import com.electionapp.domain.entities.PVCDataModel
import com.electionapp.domain.entities.StatItemModel
import com.electionapp.domain.mapper.PVCDataModelMapper
import com.electionapp.domain.mapper.PVCStatsModelMapper
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