package com.example.android4homework8mc6.data.models.attributes

import com.google.gson.annotations.SerializedName

data class Titles(

    @SerializedName("ja_jp")
    val nameInJapanese: String,

    @SerializedName("en_jp")
    val nameInEnglish: String
)