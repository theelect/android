package com.electionapp.android.ui.base

/**
 * Created by aliumujib on 08/06/2018.
 */

import android.arch.lifecycle.ViewModel
import android.databinding.Observable
import android.support.annotation.StringRes
import com.electionapp.android.R
import com.electionapp.android.utils.SingleLiveEvent
import com.electionapp.constants.NetworkState
import com.electionapp.domain.base.Params
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.net.SocketTimeoutException

open class BaseViewModel : ViewModel(), Observable {

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    val snackbarMessage = SingleLiveEvent<Int>()
    val snackbarStringMessage = SingleLiveEvent<String>()
    val dialogMessage = SingleLiveEvent<String>()
    val isLoading = SingleLiveEvent<NetworkState>()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun clearDisposables() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        clearDisposables()
    }

    open fun validateData(params: Params): Boolean {
        return true
    }

    fun showSnackbarMessage(@StringRes message: Int) {
        snackbarMessage.value = message
    }

    fun showSnackbarMessage(message: String) {
        snackbarStringMessage.value = message
    }

    fun showDialogMessage(@StringRes message: String) {
        dialogMessage.value = message
    }

    fun showLoading() {
        isLoading.value = NetworkState.LOADING
    }

    fun hideLoading() {
        isLoading.value = NetworkState.LOADED
    }

    fun showError(state: NetworkState) {
        isLoading.value = state
    }

    fun displayError(string: String?) {
        snackbarStringMessage.value = string
    }

    fun displayError(@StringRes res: Int?) {
        snackbarMessage.value = res
    }

    open fun setUp() {

    }


    /***
     * this is dirty as FUCK!!!
     * buttttttt then sha ... time dey go and I need to handle these olodo errors
     * **/
    open fun handleError(exception: Throwable) {
        if (exception is HttpException) {
            val responseBody = exception.response().errorBody()
            displayError(getErrorMessage(responseBody))
        } else if (exception is SocketTimeoutException) {
            showSnackbarMessage(R.string.check_internet)
        } else {
            displayError(exception.message)
        }
    }

    private fun getErrorMessage(responseBody: ResponseBody?): String? {
        return try {
            val jsonObject = JSONObject(responseBody?.string())
            jsonObject.getString("message")
        } catch (e: Exception) {
            e.message
        }

    }

}
