package com.efedaniel.spotifystats.domain.manager

import com.efedaniel.spotifystats.domain.mapper.ArtistMapper
import com.efedaniel.spotifystats.domain.mapper.UserMapper
import com.efedaniel.spotifystats.domain.model.Artist
import com.efedaniel.spotifystats.domain.model.User
import com.efedaniel.spotifystats.network.service.UserApi
import com.efedaniel.spotifystats.persistence.cache.UserCache
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserDomainManager @Inject constructor(
    private val userApi: UserApi,
    private val cache: UserCache,
    private val userMapper: UserMapper,
    private val artistMapper: ArtistMapper,
) {

    fun fetchCurrentUser(): Single<User> = userApi
        .getCurrentUser()
        .map(userMapper::dtoToDomain)
        .doOnSuccess { cache.storeUser(userMapper.domainToEntity(it)) }

    fun retrieveCurrentUserFromCache(): Single<User> =
        cache
            .retrieveUser()
            ?.let { Single.just(userMapper.mapEntityToDomain(it)) }
            ?: Single.error(Exception("User does not exist in cache"))

    fun getArtist(id: String): Single<Artist> = userApi
        .getArtist(id)
        .map(artistMapper::dtoToDomain)
}