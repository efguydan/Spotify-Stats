package com.efedaniel.spotifystats.domain.mapper

import com.efedaniel.spotifystats.domain.model.User
import com.efedaniel.spotifystats.network.dto.UserResponse
import com.efedaniel.spotifystats.persistence.entity.UserEntity
import javax.inject.Inject

class UserMapper @Inject constructor() {

    fun dtoToDomain(dto: UserResponse): User = User(
        id = dto.id,
        countryIso = dto.country,
        displayName = dto.displayName,
        followers = dto.followers.total,
        imageUrl = dto.images.firstOrNull()?.url,
        spotifyUri = dto.uri
    )

    fun domainToEntity(domain: User): UserEntity = UserEntity(
        id = domain.id,
        countryIso = domain.countryIso,
        displayName = domain.displayName,
        followers = domain.followers,
        imageUrl = domain.imageUrl,
        spotifyUri = domain.spotifyUri,
    )

    fun mapEntityToDomain(entity: UserEntity): User = User(
        id = entity.id,
        countryIso = entity.countryIso,
        displayName = entity.displayName,
        followers = entity.followers,
        imageUrl = entity.imageUrl,
        spotifyUri = entity.spotifyUri
    )
}
