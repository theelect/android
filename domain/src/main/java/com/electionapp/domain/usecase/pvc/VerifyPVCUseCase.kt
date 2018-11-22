package com.electionapp.domain.usecase.pvc


import com.electionapp.constants.Constants
import com.electionapp.data.contracts.IAuthService
import com.electionapp.data.contracts.IPVCVerificationService
import com.electionapp.data.repositories.verification.PVCVerificationService
import com.electionapp.domain.base.Params
import com.electionapp.domain.base.Schedulers
import com.electionapp.domain.base.UseCase
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aliumujib on 14/05/2018.
 */

class VerifyPVCUseCase @Inject constructor(schedulers: Schedulers,
                                           var pvcVerificationService: IPVCVerificationService)
    : UseCase<Params, Boolean>(schedulers) {

    override fun buildObservable(params: Params?): Observable<Boolean> {
        var isOnline = params?.getBoolean(Constants.PVC_VERIFICATION_CONSTANTS.IS_VERIFICATION_ONLINE, true)
        return if (isOnline!!) {
            params?.putData("state_id", "4")
            params?.getParameters()?.remove(Constants.PVC_VERIFICATION_CONSTANTS.IS_VERIFICATION_ONLINE)
            pvcVerificationService.verifyPVCOnline(params!!.getParameters())
        }else{
            pvcVerificationService.verifyPVCViaSMS(params!!.getParameters())
        }
    }

}