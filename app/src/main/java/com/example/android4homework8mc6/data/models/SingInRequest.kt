package com.example.android4homework8mc6.data.models

import com.google.gson.annotations.SerializedName

data class SingInRequest(
    @SerializedName("grant_type")
    val grant_type: String,
    @SerializedName("username")
    val email: String,
    @SerializedName("password")
    val password: String,
)