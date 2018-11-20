package com.electionapp.data.repositories.verification

import android.telephony.SmsManager
import com.electionapp.constants.Constants
import com.electionapp.data.contracts.IPVCVerificationService
import com.electionapp.data.network.ApiService
import io.reactivex.Observable
import javax.inject.Inject


class PVCVerificationService @Inject constructor(var apiService: ApiService,
                                                     var smsManager: SmsManager) : IPVCVerificationService {

    override fun verifyPVCOnline(hashMap: Map<String, Any>): Observable<Boolean> {
        return apiService.verifyPVCViaApp(hashMap).map {
            it.data.is_verified
        }
    }

    override fun verifyPVCViaSMS(hashMap: Map<String, Any>): Observable<Boolean> {
        return Observable.create {
            val phoneNumber = "0123456789"

            val vin = hashMap[Constants.AUTH_CONSTANTS.VIN] as String
            val lastname = hashMap[Constants.AUTH_CONSTANTS.LAST_NAME] as String
            val phone = hashMap[Constants.AUTH_CONSTANTS.PHONE] as String
            val state = 33

            val message = "$vin,$lastname,$phone,$state"
            val parts = smsManager.divideMessage(message)
            smsManager.sendMultipartTextMessage(phoneNumber, null, parts, null, null)
        }
    }


}