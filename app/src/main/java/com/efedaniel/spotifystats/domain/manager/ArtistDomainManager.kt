package com.efedaniel.spotifystats.domain.manager

import com.efedaniel.spotifystats.domain.mapper.AlbumMapper
import com.efedaniel.spotifystats.domain.mapper.ArtistMapper
import com.efedaniel.spotifystats.domain.model.Album
import com.efedaniel.spotifystats.domain.model.Artist
import com.efedaniel.spotifystats.network.dto.AlbumDto
import com.efedaniel.spotifystats.network.dto.PaginatedResponse
import com.efedaniel.spotifystats.network.service.ArtistApi
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class ArtistDomainManager @Inject constructor(
    private val artistAPi: ArtistApi,
    private val artistMapper: ArtistMapper,
    private val albumMapper: AlbumMapper,
) {

    fun getArtist(id: String): Single<Artist> = artistAPi
        .getArtist(id)
        .map(artistMapper::dtoToDomain)

    fun getArtistAlbum(id: String): Single<List<Album>> = artistAPi
        .getArtistAlbum(id)
        .map(PaginatedResponse<AlbumDto>::items)
        .map(albumMapper::dtoListToDomainList)

}