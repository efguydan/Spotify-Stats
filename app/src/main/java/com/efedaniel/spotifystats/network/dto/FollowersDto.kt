package com.efedaniel.spotifystats.network.dto


import com.google.gson.annotations.SerializedName

data class FollowersDto(
    @SerializedName("total")
    val total: Int
)