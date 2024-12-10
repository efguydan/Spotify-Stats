package com.efedaniel.spotifystats.domain.model

import com.efedaniel.spotifystats.network.dto.FollowersDto

data class RelatedArtist(
    val id: String = "",
    val genres: List<String> = emptyList(),  // Fixme: Change to ImmutableList
    val imageUrl: String? = null,
    val name: String = "",
    val popularity: Int = 0,
    val followersDto: FollowersDto? = null,

)
