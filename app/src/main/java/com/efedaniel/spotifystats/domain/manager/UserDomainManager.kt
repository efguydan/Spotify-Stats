package com.efedaniel.spotifystats.domain.manager

import com.efedaniel.spotifystats.domain.mapper.AlbumMapper
import com.efedaniel.spotifystats.domain.mapper.ArtistMapper
import com.efedaniel.spotifystats.domain.mapper.TopArtistMapper
import com.efedaniel.spotifystats.domain.mapper.TopTrackMapper
import com.efedaniel.spotifystats.domain.mapper.UserMapper
import com.efedaniel.spotifystats.domain.model.Album
import com.efedaniel.spotifystats.domain.model.Artist
import com.efedaniel.spotifystats.domain.model.User
import com.efedaniel.spotifystats.network.dto.AlbumDto
import com.efedaniel.spotifystats.network.dto.PaginatedResponse
import com.efedaniel.spotifystats.network.dto.TopAlbumDto
import com.efedaniel.spotifystats.network.dto.TopArtistDto
import com.efedaniel.spotifystats.network.service.UserApi
import com.efedaniel.spotifystats.persistence.cache.UserCache
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.annotation.Signed
import javax.inject.Inject

class UserDomainManager @Inject constructor(
    private val userApi: UserApi,
    private val cache: UserCache,
    private val userMapper: UserMapper,
    private val artistMapper: ArtistMapper,
    private val albumMapper: AlbumMapper,

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

    fun getArtistAlbum(id: String): Single<List<Album>> = userApi
        .getArtistAlbum(id)
        .map(PaginatedResponse<AlbumDto>::items)
        .map(albumMapper::dtoListToDomainList)

}