package com.electionapp.data.contracts

import android.content.Intent
import io.reactivex.Notification
import io.reactivex.Observable

interface IGoogleLoginManager {

    fun getAuthToken(): Observable<Notification<String>>

    fun start()

    fun handleCompletedSignIn(data:Intent)

    object CONSTANTS{
        val RC_SIGN_IN: Int = 211
    }
}