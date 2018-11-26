package com.electionapp.domain.usecase.main


import com.electionapp.data.contracts.IPVCDataRepository
import com.electionapp.domain.base.Params
import com.electionapp.domain.base.Schedulers
import com.electionapp.domain.base.UseCase
import com.electionapp.domain.entities.LGAModel
import com.electionapp.domain.mapper.LGAModelMapper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 14/05/2018.
 */

class FetchLGADataUseCase @Inject constructor(schedulers: Schedulers,
                                              var lgaModelMapper: LGAModelMapper,
                                              var repository: IPVCDataRepository)
    : UseCase<Params, List<LGAModel>>(schedulers) {

    override fun buildObservable(params: Params?): Observable<List<LGAModel>> {
        return repository.fetchAllLGASServer(params!!.getParameters()).map {
            lgaModelMapper.mapFromList(it).toList()
        }
    }

}