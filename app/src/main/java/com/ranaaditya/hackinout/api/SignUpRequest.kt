package com.ranaaditya.hackinout.api

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("mobileNumber")
    var mobileNumber: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("password")
    var password: String
)