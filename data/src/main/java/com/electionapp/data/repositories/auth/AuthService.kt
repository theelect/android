package com.electionapp.data.repositories.auth

import com.electionapp.data.contracts.IAuthService
import com.electionapp.data.contracts.IUserCache
import com.electionapp.data.network.ApiService
import com.softcom.abujametrodata.contracts.ITokenManager
import io.reactivex.Observable
import javax.inject.Inject

class AuthService @Inject constructor(var userCache: IUserCache,
                                      var tokenManager: ITokenManager,
                                      var apiService: ApiService) : IAuthService {

    override fun saveUserFacebookAuthToken(hashMap: Map<String, Any>): Observable<Boolean> {
        return apiService.sendUserFacebookAuthToken(hashMap).map {
            if (it.token == null) {
                return@map false
            } else {
                tokenManager.saveToken(it.token)
                return@map true
            }
        }
    }

    override fun saveUserGoogleAuthToken(hashMap: Map<String, Any>): Observable<Boolean> {
        return apiService.sendUserGoogleAuthToken(hashMap).map {
            if (it.token == null) {
                return@map false

            } else {
                tokenManager.saveToken(it.token)
                return@map true
            }
        }
    }

    override fun registerUser(hashMap: Map<String, Any>): Observable<Boolean> {
        return apiService.signUserUp(hashMap).map {
            if (it.token == null) {
                return@map false

            } else {
                tokenManager.saveToken(it.token)
                return@map true
            }
        }
    }

    override fun resetUserPassword(hashMap: Map<String, Any>): Observable<String> {
        return apiService.requestPasswordReset(hashMap).map {
            return@map it.message
        }
    }

    override fun logUserIn(hashMap: Map<String, Any>): Observable<Boolean> {
        return apiService.signUserIn(hashMap).map {
            if (it.token == null) {
                return@map false

            } else {
                tokenManager.saveToken(it.token)
                return@map true
            }
        }
    }

}