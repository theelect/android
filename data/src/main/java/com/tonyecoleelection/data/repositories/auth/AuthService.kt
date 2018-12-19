package com.tonyecoleelection.data.repositories.auth

import com.tonyecoleelection.data.contracts.IAuthService
import com.tonyecoleelection.data.contracts.IUserAccountTypeManager
import com.tonyecoleelection.data.contracts.IUserCache
import com.tonyecoleelection.data.network.ApiService
import com.softcom.abujametrodata.contracts.ITokenManager
import io.reactivex.Observable
import javax.inject.Inject

class AuthService @Inject constructor(var userCache: IUserCache,
                                      var tokenManager: ITokenManager,
                                      var userAccountTypeManager: IUserAccountTypeManager,
                                      var apiService: ApiService) : IAuthService {

    override fun resetUserPassword(hashMap: Map<String, Any>): Observable<String> {
        return apiService.updateUserPassword(hashMap).map {
            return@map it.message
        }
    }

    override fun fetchUserWithToken(): Observable<Boolean> {
        return apiService.fetchUserData().map {
            userAccountTypeManager.saveUserAccountType(it.role ?: "")
            userCache.saveCurrentUser(it)
            true
        }
    }


    override fun registerWC(hashMap: Map<String, Any>): Observable<Boolean> {
        return apiService.signWCUp(hashMap).flatMap {
            tokenManager.saveToken(it.token?:"")
            fetchUserWithToken()
        }
    }

    override fun requestUserPasswordReset(hashMap: Map<String, Any>): Observable<String> {
        return apiService.requestPasswordReset(hashMap).map {
            return@map it.message
        }
    }

    override fun logUserIn(hashMap: Map<String, Any>): Observable<Boolean> {
        return apiService.signUserIn(hashMap).flatMap {
            tokenManager.saveToken(it.token?:"")
            fetchUserWithToken()
        }
    }

}