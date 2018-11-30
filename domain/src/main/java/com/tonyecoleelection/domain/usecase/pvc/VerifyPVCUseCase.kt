package com.tonyecoleelection.domain.usecase.pvc


import com.tonyecoleelection.constants.Constants
import com.tonyecoleelection.data.contracts.IPVCVerificationService
import com.tonyecoleelection.domain.base.Params
import com.tonyecoleelection.domain.base.Schedulers
import com.tonyecoleelection.domain.base.UseCase
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
            params?.putData("state_id", "33")
            params?.getParameters()?.remove(Constants.PVC_VERIFICATION_CONSTANTS.IS_VERIFICATION_ONLINE)
            pvcVerificationService.verifyPVCOnline(params!!.getParameters())
        }else{
            pvcVerificationService.verifyPVCViaSMS(params!!.getParameters())
        }
    }

}