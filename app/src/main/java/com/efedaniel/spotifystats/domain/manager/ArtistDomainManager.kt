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
import com.efedaniel.spotifystats.network.service.ArtistApi
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class ArtistDomainManager @Inject constructor(
    private val artistAPi: ArtistApi,
    private val artistMapper: ArtistMapper,
    private val albumMapper: AlbumMapper,
    private val artistTrackMapper: ArtistTrackMapper,
    private val severalArtistsMapper: SeveralArtistsMapper

) {

    fun getArtist(id: String): Single<Artist> = artistAPi
        .getArtist(id)
        .map(artistMapper::dtoToDomain)

    fun getArtistAlbum(id: String): Single<List<Album>> = artistAPi
        .getArtistAlbum(id)
        .map(PaginatedResponse<AlbumDto>::items)
        .map(albumMapper::dtoListToDomainList)


    fun getArtistTopTracks(id: String): Single<List<ArtistTrack>> = artistAPi
        .getArtistTopTracks(id)
        .map{it.track}
        .map{artistTrackMapper.dtoListToDomainList(it)}

    fun getSeveralArtists(): Single<List<Artist>> = artistAPi
        .getSeveralArtist()
        .map {it.artists}
        .map {severalArtistsMapper.dtoListToDomainList(it)}


}