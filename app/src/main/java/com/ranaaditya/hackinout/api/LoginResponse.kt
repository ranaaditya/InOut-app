package com.ranaaditya.hackinout.api

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("sucess")
    var sucess: Boolean,

    @SerializedName("message")
    var msg: String
)