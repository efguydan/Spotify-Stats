package com.efedaniel.spotifystats.domain.manager

import com.efedaniel.spotifystats.domain.mapper.AlbumMapper
import com.efedaniel.spotifystats.domain.mapper.ArtistMapper
import com.efedaniel.spotifystats.domain.mapper.ArtistTrackMapper
import com.efedaniel.spotifystats.domain.mapper.SeveralArtistsMapper
import com.efedaniel.spotifystats.domain.model.Album
import com.efedaniel.spotifystats.domain.model.Artist
import com.efedaniel.spotifystats.domain.model.ArtistTrack
import com.efedaniel.spotifystats.network.dto.AlbumDto
import com.efedaniel.spotifystats.network.dto.PaginatedResponse
import com.efedaniel.spotifystats.network.dto.SeveralArtistsDto
import com.efedaniel.spotifystats.network.service.ArtistApi
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ArtistDomainManager @Inject constructor(
    private val artistApi: ArtistApi,
    private val artistMapper: ArtistMapper,
    private val albumMapper: AlbumMapper,
    private val artistTrackMapper: ArtistTrackMapper,
    private val severalArtistsMapper: SeveralArtistsMapper,
) {

    fun getArtist(id: String): Single<Artist> = artistApi
        .getArtist(id)
        .map(artistMapper::dtoToDomain)

    fun getArtistAlbum(id: String): Single<List<Album>> = artistApi
        .getArtistAlbum(id)
        .map(PaginatedResponse<AlbumDto>::items)
        .map(albumMapper::dtoListToDomainList)

    fun getArtistTopTracks(id: String): Single<List<ArtistTrack>> = artistApi
        .getArtistTopTracks(id)
        .map { it.track }
        .map { artistTrackMapper.dtoListToDomainList(it) }

    fun getSeveralArtists(ids: List<String>): Single<List<Artist>> = artistApi
        .getSeveralArtist(ids)
        .map (SeveralArtistsDto::artists)
        .map {severalArtistsMapper.dtoListToDomainList(it)}
}