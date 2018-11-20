package com.electionapp.domain.usecase.pvc


import com.electionapp.constants.Constants
import com.electionapp.data.contracts.IAuthService
import com.electionapp.data.contracts.IPVCDataRepository
import com.electionapp.data.contracts.IPVCVerificationService
import com.electionapp.data.model.PVCData
import com.electionapp.data.repositories.verification.PVCVerificationService
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
    : UseCase<Params, List<PVCData>>(schedulers) {

    //TODO, make mapper and domain models
    override fun buildObservable(params: Params?): Observable<List<PVCData>> {
        return repository.fetchAllPVCDataWithFilters(params!!.getParameters())
    }

}