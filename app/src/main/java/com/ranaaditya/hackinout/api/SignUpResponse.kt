package com.ranaaditya.hackinout.api

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("sucess")
    var sucess: Boolean,

    @SerializedName("message")
    var msg: String
)