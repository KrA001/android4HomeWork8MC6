package com.example.android4homework8mc6.data.models

import com.google.gson.annotations.SerializedName

data class Links(

    @SerializedName("first")
    val first: String,

    @SerializedName("prev")
    val prev: String,

    @SerializedName("next")
    val next: String,

    @SerializedName("last")
    val last: String,
)
