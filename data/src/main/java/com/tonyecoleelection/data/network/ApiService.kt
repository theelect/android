package com.tonyecoleelection.data.network


import com.tonyecoleelection.data.model.LGAEntity
import com.tonyecoleelection.data.model.PVCDataEntity
import com.tonyecoleelection.data.model.StatItemEntity
import com.tonyecoleelection.data.model.UserEntity
import com.tonyecoleelection.data.network.reponses.AgeStats
import com.tonyecoleelection.data.network.reponses.DataCountResponse
import com.tonyecoleelection.data.network.reponses.GenericResponse
import com.tonyecoleelection.data.network.reponses.VoterDataPagingResponse
import io.reactivex.Observable
import retrofit2.http.*


interface ApiService {

    @POST("login")
    fun signUserIn(@Body body: Map<String, @JvmSuppressWildcards Any>): Observable<GenericResponse<Void>>

    @POST("create-wc")
    fun signWCUp(@Body body: Map<String, @JvmSuppressWildcards Any>): Observable<GenericResponse<Void>>

    @GET("user")
    fun fetchUserData(): Observable<UserEntity>

    @POST("request-password-reset")
    fun requestPasswordReset(@Body body: Map<String, @JvmSuppressWildcards Any>): Observable<GenericResponse<Void>>

    @POST("pvc/verifyViaApp")
    fun verifyPVCViaApp(@Body body: Map<String, @JvmSuppressWildcards Any>): Observable<PVCDataEntity>

    @GET("pvc-count")
    fun pvcCount(): Observable<DataCountResponse>

    @GET("pvc/age_statistics")
    fun pvcAgeStats(): Observable<AgeStats>

    @GET("pvc")
    fun getAllVerifiedPVCWithFilters(@QueryMap body: Map<String, @JvmSuppressWildcards Any>): Observable<VoterDataPagingResponse>

    @GET("pvc/statistics")
    fun getAllVerifiedPVCStatsWithFilters(@QueryMap body: Map<String, @JvmSuppressWildcards Any>): Observable<List<StatItemEntity>>

    @GET("lgas")
    fun getAllLGAsWithWards(@QueryMap body: Map<String, @JvmSuppressWildcards Any>): Observable<List<LGAEntity>>

    @GET(" pvc/occupation")
    fun getAllOccupations(@QueryMap body: Map<String, @JvmSuppressWildcards Any>): Observable<List<String>>

}


