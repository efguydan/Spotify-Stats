package com.efedaniel.spotifystats.network.dto

import com.google.gson.annotations.SerializedName

data class PlaybackDto(
    @SerializedName("is_playing")
    val isPlaying: Boolean,
)
