package com.ranaaditya.hackinout.api

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("mobileNumber")
    var mobileNumber: String,

    @SerializedName("password")
    var password: String
)