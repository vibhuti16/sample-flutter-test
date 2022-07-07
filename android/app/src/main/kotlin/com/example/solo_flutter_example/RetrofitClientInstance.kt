package com.example.solo_flutter_example

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log

object RetrofitClientInstance {

    lateinit var retrofit: Retrofit

    private const val BASE_URL = "https://user-service.solobeta.co/v1/"

    private var token = ""

    val retrofitInstance: Retrofit
        get() {
            if (!this::retrofit.isInitialized) {
                val headersInterceptor = Interceptor { chain ->
                    val requestBuilder = chain.request().newBuilder()
                    requestBuilder.header("Authorization", "Bearer $token")
                    requestBuilder.header("Content-Type", "application/json")
                    requestBuilder.header("Accept", "application/json")
                    chain.proceed(requestBuilder.build())
                }

                val loggingInterceptor = HttpLoggingInterceptor { message ->
                    Log.d("message",message)
                }.apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }

                val okHttpClient = OkHttpClient()
                    .newBuilder()
                    .followRedirects(true)
                    .addInterceptor(headersInterceptor)
                    .addInterceptor(loggingInterceptor)
                    .build()
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()
            }
            return retrofit
        }

    fun setToken(token: String) {
        RetrofitClientInstance.token = token
    }
}