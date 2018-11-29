package com.tonyecoleelection.domain.usecase.main


import com.tonyecoleelection.data.contracts.IPVCDataRepository
import com.tonyecoleelection.domain.base.Params
import com.tonyecoleelection.domain.base.Schedulers
import com.tonyecoleelection.domain.base.UseCase
import com.tonyecoleelection.domain.entities.LGAModel
import com.tonyecoleelection.domain.mapper.LGAModelMapper
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