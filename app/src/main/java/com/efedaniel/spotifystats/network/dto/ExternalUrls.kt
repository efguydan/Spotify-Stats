package com.efedaniel.spotifystats.network.dto


import com.google.gson.annotations.SerializedName

data class ExternalUrls(
    @SerializedName("spotify")
    val spotify: String
)