package com.hectorg13.thesimpsons.models

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("quote")
    val phrase: String,
    @SerializedName("character")
    val character: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("characterDirection")
    val characterAddress: String
)
