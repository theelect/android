package com.electionapp.data.network


import com.electionapp.data.model.PVCDataEntity
import com.electionapp.data.model.StatItemEntity
import com.electionapp.data.model.UserEntity
import com.electionapp.data.network.reponses.GenericResponse
import com.electionapp.data.network.reponses.VoterDataPagingResponse
import io.reactivex.Observable
import retrofit2.http.*


interface ApiService {

    @POST("login")
    fun signUserIn(@Body body: Map<String, @JvmSuppressWildcards Any>): Observable<GenericResponse<Void>>

    @POST("create-wc")
    fun signWCUp(@Body body: Map<String, @JvmSuppressWildcards Any>): Observable<GenericResponse<Void>>

    @GET("user")
    fun fetchUserData(): Observable<UserEntity>


    @POST("password-reset")
    fun requestPasswordReset(@Body body: Map<String, @JvmSuppressWildcards Any>): Observable<GenericResponse<Void>>

    @POST("pvc/verifyViaApp")
    fun verifyPVCViaApp(@Body body: Map<String, @JvmSuppressWildcards Any>): Observable<PVCDataEntity>

    @GET("pvc")
    fun getAllVerifiedPVCWithFilters(@QueryMap body: Map<String, @JvmSuppressWildcards Any>): Observable<VoterDataPagingResponse>

    @GET("pvc/statistics")
    fun getAllVerifiedPVCStatsWithFilters(@QueryMap body: Map<String, @JvmSuppressWildcards Any>): Observable<List<StatItemEntity>>

}
