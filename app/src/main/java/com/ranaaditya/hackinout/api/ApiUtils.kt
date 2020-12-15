package com.ranaaditya.hackinout.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiUtils {

    const val BASE_URL = "<yourlocal_host/IP>:port_running_docker/api/"
    const val LOGIN_URL = "auth/login"
    const val SIGNUP_URL = "auth/register"
    const val PAYLOAD_URL = "payload"

    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .callTimeout(10, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .build()

    fun provideGson(): Gson = GsonBuilder()
        .enableComplexMapKeySerialization()
        .disableHtmlEscaping()
        .create()

    fun provideRetrofit(okhttpClient: OkHttpClient, gson: Gson): Api = Retrofit.Builder()
        .client(okhttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build().create(Api::class.java)
}