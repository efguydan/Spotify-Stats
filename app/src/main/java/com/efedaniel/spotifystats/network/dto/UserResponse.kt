package com.efedaniel.spotifystats.network.dto


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("country")
    val country: String,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("followers")
    val followers: FollowersDto,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: List<ImageDto>,
    @SerializedName("uri")
    val uri: String,
)
