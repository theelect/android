package com.electionapp.domain.usecase.main


import com.electionapp.data.contracts.IPVCDataRepository
import com.electionapp.domain.base.Params
import com.electionapp.domain.base.Schedulers
import com.electionapp.domain.base.UseCase
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