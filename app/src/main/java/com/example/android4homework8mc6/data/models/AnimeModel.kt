package com.example.android4homework8mc6.data.models

import com.example.android4homework8mc6.data.models.attributes.Attributes
import com.google.gson.annotations.SerializedName

data class AnimeModel(

    @SerializedName("id")
    val id: String,

    @SerializedName("attributes")
    val attributes: Attributes
)