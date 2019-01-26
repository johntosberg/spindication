package com.osberg.spindication.service

import com.osberg.spindication.domain.Track
import groovy.util.logging.Slf4j
import spotify.api.SpotifyApi
import spotify.dto.SpotifySearchResponse
import spotify.dto.SpotifyTrack

@Slf4j
class SetlistImporterService {

    SpotifyApi spotifyApi

    SetlistImporterService(SpotifyApi api) {
        this.spotifyApi = api
    }

    Track getTrackFromString(String trackString) {
        log.info "Searching for track ${trackString}"
        SpotifySearchResponse searchResponse = spotifyApi.searchForTrack(trackString, 5).blockingSingle()
        //This could probably use an algorithm of sorts
        List<SpotifyTrack> tracks = searchResponse.tracks?.trackItems
        if (tracks.size() > 0) {
            Track track = Track.fromSpotifyTrack(tracks.first())
            track.rawName = trackString
            log.info "Found track ${track.name}"
            return track
        }
        log.info "Unable to find track ${trackString}"
        null
    }

}
