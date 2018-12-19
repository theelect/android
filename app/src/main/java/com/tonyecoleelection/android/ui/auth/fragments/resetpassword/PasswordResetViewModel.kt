package com.tonyecoleelection.android.ui.auth.fragments.resetpassword

import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.ui.auth.BaseAuthViewModel
import com.tonyecoleelection.android.ui.auth.IAuthNavigator
import com.tonyecoleelection.android.utils.extensions.mutableLiveDataOf
import com.tonyecoleelection.constants.Constants
import com.tonyecoleelection.domain.base.DefaultObserver
import com.tonyecoleelection.domain.base.Params
import com.tonyecoleelection.domain.usecase.auth.PasswordResetUseCase
import com.tonyecoleelection.domain.usecase.auth.RequestPasswordResetUseCase


/**
 * Created by aliumujib on 08/06/2018.
 */

class PasswordResetViewModel(authNavigator: IAuthNavigator,
                             var passwordResetUseCase: PasswordResetUseCase,
                             var requestPasswordResetUseCase: RequestPasswordResetUseCase) : BaseAuthViewModel(authNavigator) {

    var currentlyDisplayedChild = mutableLiveDataOf<Int>()

    override fun setUp() {

    }

    fun requestResetPassword(email: String) {
        val params = Params.create()
        params.putString(Constants.AUTH_CONSTANTS.EMAIL, email)
        if (validateData(params)) {
            addDisposable(requestPasswordResetUseCase.execute(params)
                    .doOnSubscribe {
                        showLoading()
                    }
                    .subscribeWith(PasswordResetObserver()))
        }
    }

    override fun validateData(params: Params): Boolean {
        return if (!params.notEmptyString(Constants.AUTH_CONSTANTS.EMAIL)) {
            displayError(R.string.input_email_error)
            false
        } else {
            super.validateData(params)
        }
    }


     fun validateUpdateData(params: Params): Boolean {
        return if (!params.notEmptyString(Constants.AUTH_CONSTANTS.PASSWORD)) {
            displayError(R.string.input_password_error)
            false
        }else if (!params.notEmptyString(Constants.AUTH_CONSTANTS.RESET_CODE)){
            displayError(R.string.input_reset_code)
            false
        } else {
            super.validateData(params)
        }
    }


    private fun onRequestCompleted(t: String) {
        hideLoading()
        showSnackbarMessage(t)
        //navigateBack()
        currentlyDisplayedChild.value = 1
    }

    private fun onRequestFailed(exception: Throwable) {
        hideLoading()
        handleError(exception)
    }

    fun resetPasswordWithCode(pinCode: String, password: String) {
        val params = Params.create()
        params.putString(Constants.AUTH_CONSTANTS.PASSWORD, password)
        params.putString(Constants.AUTH_CONSTANTS.RESET_CODE, pinCode)

        if (validateUpdateData(params)) {
            addDisposable(passwordResetUseCase.execute(params)
                    .doOnSubscribe {
                        showLoading()
                    }
                    .subscribeWith(PasswordUpdateObserver()))
        }
    }


    private fun onUpdateCompleted(t: String) {
        hideLoading()
        showSnackbarMessage(t)
        navigateBack()
    }

    private fun onUpdateFailed(exception: Throwable) {
        hideLoading()
        handleError(exception)
    }


    inner class PasswordResetObserver : DefaultObserver<String>() {
        override fun onNext(t: String) {
            super.onNext(t)
            onRequestCompleted(t)
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)
            onRequestFailed(exception)
        }

    }


    inner class PasswordUpdateObserver : DefaultObserver<String>() {
        override fun onNext(t: String) {
            super.onNext(t)
            onUpdateCompleted(t)
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)
            onUpdateFailed(exception)
        }

    }


}