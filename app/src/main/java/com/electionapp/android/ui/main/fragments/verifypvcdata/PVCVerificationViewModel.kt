package com.electionapp.android.ui.main.fragments.verifypvcdata

import com.electionapp.android.R
import com.electionapp.android.ui.base.BaseViewModel
import com.electionapp.android.ui.main.IMainNavigator
import com.electionapp.constants.Constants
import com.electionapp.domain.base.DefaultObserver
import com.electionapp.domain.base.Params
import com.electionapp.domain.usecase.pvc.VerifyPVCUseCase


/**
 * Created by aliumujib on 08/06/2018.
 */

class PVCVerificationViewModel(private val verifyPVCUseCase: VerifyPVCUseCase,
                               mainNavigator: IMainNavigator) : BaseViewModel() {

    val verifyParams = Params.create()

    fun verifyPVCWithDetails(firstName: String,
                             lastName: String,
                             phoneNumber: String, vin: String) {

        verifyParams.putData(Constants.AUTH_CONSTANTS.FIRST_NAME, firstName)
        verifyParams.putData(Constants.AUTH_CONSTANTS.LAST_NAME, lastName)
        verifyParams.putData(Constants.AUTH_CONSTANTS.PHONE, phoneNumber)
        verifyParams.putData(Constants.AUTH_CONSTANTS.VIN, vin)

        if (validateData(verifyParams)) {
            addDisposable(verifyPVCUseCase.execute(verifyParams)
                    .doOnSubscribe {
                        showLoading()
                    }
                    .subscribeWith(VerificationObserver()))
        }
    }


    override fun validateData(params: Params): Boolean {
        return if (!params.notEmptyString(Constants.AUTH_CONSTANTS.PHONE)) {
            displayError(R.string.input_phone_error)
            false
        } else if (!params.notEmptyString(Constants.AUTH_CONSTANTS.FIRST_NAME)) {
            displayError(R.string.input_first_name_error)
            false
        } else if (!params.notEmptyString(Constants.AUTH_CONSTANTS.LAST_NAME)) {
            displayError(R.string.input_last_name_error)
            false
        } else if (!params.notEmptyString(Constants.AUTH_CONSTANTS.WARD)) {
            displayError(R.string.pick_your_ward)
            false
        } else {
            super.validateData(params)
        }
    }

    private fun onVerificationCompleted(t: Boolean) {
        hideLoading()
    }


    private fun onVerificationFailed(exception: Throwable) {
        hideLoading()
        handleError(exception)
    }

    inner class VerificationObserver : DefaultObserver<Boolean>() {
        override fun onNext(t: Boolean) {
            super.onNext(t)
            onVerificationCompleted(t)
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)
            onVerificationFailed(exception)
        }

    }

}