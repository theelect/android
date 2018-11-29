package com.tonyecoleelection.data.repositories.verification

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.telephony.SmsManager
import com.tonyecoleelection.constants.Constants
import com.tonyecoleelection.data.contracts.IPVCVerificationService
import com.tonyecoleelection.data.network.ApiService
import io.reactivex.Observable
import javax.inject.Inject
import android.content.Intent
import android.content.IntentFilter
import android.app.Activity
import android.app.PendingIntent
import com.tonyecoleelection.data.contracts.IPVCDataRepository


class PVCVerificationService @Inject constructor(var apiService: ApiService,
                                                 var application: Application,
                                                 var pvcDataRepository: IPVCDataRepository,
                                                 var smsManager: SmsManager) : IPVCVerificationService {

    override fun verifyPVCOnline(hashMap: Map<String, Any>): Observable<Boolean> {
        return apiService.verifyPVCViaApp(hashMap).map {
            pvcDataRepository.savePVCData(it)
            it.is_verified
        }
    }

    override fun verifyPVCViaSMS(hashMap: Map<String, Any>): Observable<Boolean> {
        return Observable.create {
            val phoneNumber = "+2348162927009"

            val vin = hashMap[Constants.AUTH_CONSTANTS.VIN] as String
            val lastname = hashMap[Constants.AUTH_CONSTANTS.LAST_NAME] as String
            val phone = hashMap[Constants.AUTH_CONSTANTS.PHONE] as String
            val state = 33

            val message = "$vin,$lastname,$phone,$state"
            val parts = smsManager.divideMessage(message)

            val SENT = "SMS_SENT"
            val DELIVERED = "SMS_DELIVERED"

            val sentPI = PendingIntent.getBroadcast(application, 0, Intent(SENT), 0)
            val deliveredPI = PendingIntent.getBroadcast(application, 0, Intent(DELIVERED), 0)


            application.registerReceiver(
                    object : BroadcastReceiver() {
                        override fun onReceive(arg0: Context, arg1: Intent) {
                            when (resultCode) {
                                Activity.RESULT_OK -> {
                                    it.onNext(true)
                                }
                                SmsManager.RESULT_ERROR_GENERIC_FAILURE -> {
                                    it.onNext(false)
                                }
                                SmsManager.RESULT_ERROR_NO_SERVICE -> {
                                    it.onNext(false)
                                }
                                SmsManager.RESULT_ERROR_NULL_PDU -> {
                                    it.onNext(false)
                                }
                                SmsManager.RESULT_ERROR_RADIO_OFF -> {
                                    it.onNext(false)
                                }
                            }
                        }
                    }, IntentFilter(SENT))

            application.registerReceiver(
                    object : BroadcastReceiver() {

                        override fun onReceive(arg0: Context, arg1: Intent) {
                            when (resultCode) {
                                Activity.RESULT_OK -> {
                                    it.onNext(true)
                                }
                                Activity.RESULT_CANCELED -> {
                                    it.onNext(false)
                                }
                            }
                        }
                    }, IntentFilter(DELIVERED))

            smsManager.sendMultipartTextMessage(phoneNumber, null, parts, arrayListOf(sentPI), arrayListOf(deliveredPI))

        }
    }


}