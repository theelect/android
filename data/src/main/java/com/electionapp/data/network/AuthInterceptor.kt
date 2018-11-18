package com.electionapp.data.network

import android.util.Log
import com.softcom.abujametrodata.contracts.ITokenManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/***
 * Copy and paste coding made possible by Adewale
 *
 * https://bitbucket.org/softcomdev/mtnf-android/src/dev_2/app/src/main/java/ng/softcom/mtnfdml/data/network/AuthInterceptor.java
 * **/

class AuthInterceptor(private var tokenManager: ITokenManager) : Interceptor {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        Log.d(TAG, "accessToken: " + tokenManager.getToken())

        val request = chain.request()

        val requestBuilder = request.newBuilder()


       if(tokenManager.getToken()!=null){
           requestBuilder.addHeader("Authorization", tokenManager.getToken()!!)
       }

        var response: Response? = null

        try {

            response = chain.proceed(requestBuilder.build())

        } catch (e: Exception) {
            Log.d(TAG, "<-- HTTP FAILED: $e")
            throw e
        }

        return response
    }

    companion object {
        private val TAG = AuthInterceptor::class.java.canonicalName
    }
}