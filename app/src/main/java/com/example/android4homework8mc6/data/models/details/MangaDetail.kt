package com.example.android5kitsuapiteamwork.data.models.details

import com.example.android4homework8mc6.data.models.AnimeModel
import com.google.gson.annotations.SerializedName

data class MangaDetail(

    @SerializedName("data")
    val animeModel: AnimeModel
)
