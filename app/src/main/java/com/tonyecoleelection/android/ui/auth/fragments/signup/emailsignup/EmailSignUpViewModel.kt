package com.tonyecoleelection.android.ui.auth.fragments.signup.emailsignup

import `in`.galaxyofandroid.spinerdialog.IdentifiableObject
import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.model.locale.LGA
import com.tonyecoleelection.android.ui.auth.BaseAuthViewModel
import com.tonyecoleelection.android.ui.auth.IAuthNavigator
import com.tonyecoleelection.android.utils.mapper.LGAMapper
import com.tonyecoleelection.constants.Constants
import com.tonyecoleelection.domain.base.DefaultObserver
import com.tonyecoleelection.domain.base.Params
import com.tonyecoleelection.domain.usecase.auth.SignUserUpViaEmailUseCase
import com.tonyecoleelection.domain.usecase.main.FetchLGADataUseCase
import java.util.ArrayList


/**
 * Created by aliumujib on 08/06/2018.
 */

class EmailSignUpViewModel(val signUserUpUseCase: SignUserUpViaEmailUseCase,
                           val fetchLGADataUseCase: FetchLGADataUseCase,
                           val lgaMapper: LGAMapper,
                           authNavigator: IAuthNavigator) : BaseAuthViewModel(authNavigator) {

    val loginParams = Params.create()
    private val lgaList: ArrayList<IdentifiableObject> = arrayListOf()
    private var selectedLGA: LGA? = null

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

    fun getLGAList(): ArrayList<IdentifiableObject> {
        return lgaList
    }

    fun getSelectedLGA(): LGA? {
        if (selectedLGA == null) {
            showSnackbarMessage("Please select a Local Government Area")
        }
        return selectedLGA
    }

    override fun setUp() {
        super.setUp()
        addDisposable(fetchLGADataUseCase.execute(Params.EMPTY).map {
            lgaMapper.mapFromList(it)
        }
                .doOnSubscribe {
                    showLoading()
                }.doOnNext {
                    hideLoading()
                }.doOnError {
                    hideLoading()
                }
                .subscribe({
                    lgaList.clear()
                    lgaList.addAll(it)
                }, {
                    handleError(it)
                    it.printStackTrace()
                }))
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


    fun setSelectedLGA(item: IdentifiableObject?) {
        selectedLGA = item as LGA?
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