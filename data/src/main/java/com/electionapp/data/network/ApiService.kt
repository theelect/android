package com.electionapp.data.network


import com.electionapp.data.model.PVCDataEntity
import com.electionapp.data.model.UserEntity
import com.electionapp.data.network.reponses.GenericResponse
import io.reactivex.Observable
import retrofit2.http.*


interface ApiService {

    @POST("login")
    fun signUserIn(@Body body: Map<String, @JvmSuppressWildcards Any>): Observable<GenericResponse<Void>>

    @POST("create-wc")
    fun signWCUp(@Body body: Map<String, @JvmSuppressWildcards Any>): Observable<GenericResponse<Void>>

    @POST("user")
    fun fetchUserData(@Body body: Map<String, @JvmSuppressWildcards Any>): Observable<GenericResponse<UserEntity>>


    @POST("password-reset")
    fun requestPasswordReset(@Body body: Map<String, @JvmSuppressWildcards Any>): Observable<GenericResponse<Void>>

    @POST("pvc/verifyViaApp")
    fun verifyPVCViaApp(@Body body: Map<String, @JvmSuppressWildcards Any>): Observable<GenericResponse<PVCDataEntity>>

    @POST("pvc")
    fun getAllVerifiedPVCWithFilters(@Body body: Map<String, @JvmSuppressWildcards Any>): Observable<GenericResponse<List<PVCDataEntity>>>

}
