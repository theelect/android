package com.tonyecoleelection.data.network

import com.tonyecoleelection.data.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Inject

class ApiClient @Inject constructor(var authInterceptor: AuthInterceptor) {

    val service: ApiService = createService()

    private fun createService(): ApiService {
        val httpClientBuilder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }
        httpClientBuilder.addInterceptor(logging)
        httpClientBuilder.addInterceptor(RedirectInterceptor())
        httpClientBuilder.addInterceptor(authInterceptor)
        httpClientBuilder.followRedirects(false)
        httpClientBuilder.followSslRedirects(false)

        val builder = Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
              //  .baseUrl("https://femmebnb-v2.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        builder.client(httpClientBuilder.build())
        return builder.build().create(ApiService::class.java)
    }


    private class RedirectInterceptor : Interceptor {
        companion object {
            private const val MAX_REPEATS = 5
        }

        override fun intercept(chain: Interceptor.Chain?): Response {
            val request = chain!!.request()
            var response = chain.proceed(request)
            if (response.code() == 302) {
                var repeats = MAX_REPEATS
                while (repeats-- > 0 && response.code() == 302) {
                    response = chain.proceed(request.newBuilder().build())
                }
            }
            return response
        }
    }

}