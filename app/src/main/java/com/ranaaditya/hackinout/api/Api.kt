package com.ranaaditya.hackinout.api


import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Api {

    @Headers("Content-Type:application/json")
    @POST(ApiUtils.LOGIN_URL)
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @Headers("Content-Type:application/json")
    @POST(ApiUtils.SIGNUP_URL)
    suspend fun signup(@Body signUpRequest: SignUpRequest): SignUpResponse
}