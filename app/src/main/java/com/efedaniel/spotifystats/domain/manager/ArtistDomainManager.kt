package com.efedaniel.spotifystats.domain.manager

import com.efedaniel.spotifystats.domain.mapper.AlbumMapper
import com.efedaniel.spotifystats.domain.mapper.ArtistMapper
import com.efedaniel.spotifystats.domain.mapper.ArtistTopTracksMapper
import com.efedaniel.spotifystats.domain.mapper.RelatedArtistMapper
import com.efedaniel.spotifystats.domain.model.Album
import com.efedaniel.spotifystats.domain.model.Artist
import com.efedaniel.spotifystats.domain.model.ArtistTopTracks
import com.efedaniel.spotifystats.domain.model.RelatedArtist
import com.efedaniel.spotifystats.network.dto.AlbumDto
import com.efedaniel.spotifystats.network.dto.ArtistTopTracksDto
import com.efedaniel.spotifystats.network.dto.PaginatedResponse
import com.efedaniel.spotifystats.network.dto.RelatedArtistDto
import com.efedaniel.spotifystats.network.dto.TopAlbumDto
import com.efedaniel.spotifystats.network.dto.TopTrackDto
import com.efedaniel.spotifystats.network.service.ArtistApi
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class ArtistDomainManager @Inject constructor(
    private val artistAPi: ArtistApi,
    private val artistMapper: ArtistMapper,
    private val albumMapper: AlbumMapper,
    private val artistTopTracksMapper: ArtistTopTracksMapper,
    private val relatedArtistMapper: RelatedArtistMapper,
    private val artistTopTracksDto: ArtistTopTracksDto
) {

    fun getArtist(id: String): Single<Artist> = artistAPi
        .getArtist(id)
        .map(artistMapper::dtoToDomain)

    fun getArtistAlbum(id: String): Single<List<Album>> = artistAPi
        .getArtistAlbum(id)
        .map(PaginatedResponse<AlbumDto>::items)
        .map(albumMapper::dtoListToDomainList)


    fun getArtistTopTracks(id: String): Single<Album> = artistAPi
        .getArtistTopTracks(id)
        .map{it.album}
        .map{albumMapper.dtoToDomain(it, position = null)}

}