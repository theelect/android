package com.electionapp.android.ui.auth.fragments.signup.emailsignup

import com.electionapp.android.R
import com.electionapp.android.ui.auth.BaseAuthViewModel
import com.electionapp.android.ui.auth.IAuthNavigator
import com.electionapp.constants.Constants
import com.electionapp.domain.base.DefaultObserver
import com.electionapp.domain.base.Params
import com.electionapp.domain.usecase.auth.SignUserUpViaEmailUseCase


/**
 * Created by aliumujib on 08/06/2018.
 */

class EmailSignUpViewModel(val signUserUpUseCase: SignUserUpViaEmailUseCase,
                           authNavigator: IAuthNavigator) : BaseAuthViewModel(authNavigator) {

    val loginParams = Params.create()

    fun signUserUp(email: String, firstName: String,
                   lastName: String, password: String,
                   phoneNumber: String, ward: String,
                   lga: String, vin: String) {
        loginParams.putData(Constants.AUTH_CONSTANTS.EMAIL, email)
        loginParams.putData(Constants.AUTH_CONSTANTS.PASSWORD, password)
        loginParams.putData(Constants.AUTH_CONSTANTS.FIRST_NAME, firstName)
        loginParams.putData(Constants.AUTH_CONSTANTS.LAST_NAME, lastName)
        loginParams.putData(Constants.AUTH_CONSTANTS.PHONE, phoneNumber)
        loginParams.putData(Constants.AUTH_CONSTANTS.WARD, ward)
        loginParams.putData(Constants.AUTH_CONSTANTS.LGA, lga)
        loginParams.putData(Constants.AUTH_CONSTANTS.VIN, vin)

        if (validateData(loginParams)) {
            addDisposable(signUserUpUseCase.execute(loginParams)
                    .doOnSubscribe {
                        showLoading()
                    }
                    .subscribeWith(SignUpObserver()))
        }
    }


    override fun validateData(params: Params): Boolean {
        return if (!params.notEmptyString(Constants.AUTH_CONSTANTS.EMAIL)) {
            displayError(R.string.input_email_error)
            false
        } else if (!params.notEmptyString(Constants.AUTH_CONSTANTS.PASSWORD)) {
            displayError(R.string.input_password_error)
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

    private fun onAuthCompleted(t: Boolean) {
        hideLoading()
        authNavigator.goToMain()
    }


    fun goToPasswordReset() {
        authNavigator.goToPasswordReset()
    }

    private fun onAuthFailed(exception: Throwable) {
        hideLoading()
        handleError(exception)
    }

    fun setSelectedDate(dateSelected: String) {
        loginParams.putData(Constants.USERS_CONSTANTS.DOB, dateSelected)
    }

    inner class SignUpObserver : DefaultObserver<Boolean>() {
        override fun onNext(t: Boolean) {
            super.onNext(t)
            onAuthCompleted(t)
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)
            onAuthFailed(exception)
        }

    }

}