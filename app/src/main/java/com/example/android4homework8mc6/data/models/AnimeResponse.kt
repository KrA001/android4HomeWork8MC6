package com.example.android4homework8mc6.data.models

import com.google.gson.annotations.SerializedName

data class AnimeResponse<T>(

    @SerializedName("links")
    val links: Links,

    @SerializedName("data")
    val data: List<T>
)