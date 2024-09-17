package com.example.android4homework8mc6.data.models.attributes

import com.google.gson.annotations.SerializedName

data class Attributes(

    @SerializedName("synopsis")
    val detail: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("titles")
    val title: Titles,

    @SerializedName("posterImage")
    val posterImage: PosterImage

)
