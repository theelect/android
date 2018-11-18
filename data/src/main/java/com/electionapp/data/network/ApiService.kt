package com.electionapp.data.network


import com.electionapp.data.network.reponses.GenericResponse
import com.electionapp.data.network.reponses.ListingArrayResponse
import com.electionapp.data.network.reponses.ReviewArrayResponse
import io.reactivex.Observable
import retrofit2.http.*


interface ApiService {

    @POST("login")
    fun signUserIn(@Body body: Map<String, @JvmSuppressWildcards Any>): Observable<GenericResponse<Void>>

    @POST("signup")
    fun signUserUp(@Body body: Map<String, @JvmSuppressWildcards Any>): Observable<GenericResponse<Void>>

    @POST("auth/google")
    fun sendUserGoogleAuthToken(@Body body: Map<String, @JvmSuppressWildcards Any>): Observable<GenericResponse<Void>>

    @POST("auth/facebook")
    fun sendUserFacebookAuthToken(@Body body: Map<String, @JvmSuppressWildcards Any>): Observable<GenericResponse<Void>>

    @POST("password-reset")
    fun requestPasswordReset(@Body body: Map<String, @JvmSuppressWildcards Any>): Observable<GenericResponse<Void>>

    @GET("listing/s")
    fun searchListings(@QueryMap body: Map<String, @JvmSuppressWildcards Any>): Observable<ListingArrayResponse>

    @GET("listing/{itemId}/reviews/")
    fun fetchReviewsForListing(@Path("itemId") itemId:String): Observable<ReviewArrayResponse>

}
