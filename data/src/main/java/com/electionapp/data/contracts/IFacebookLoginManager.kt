package com.electionapp.data.contracts

import io.reactivex.Observable


interface IFacebookLoginManager {

    fun getAuthToken(): Observable<String>

}
