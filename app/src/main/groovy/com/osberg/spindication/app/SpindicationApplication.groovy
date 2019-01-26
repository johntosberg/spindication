package com.osberg.spindication.app

import groovy.util.logging.Slf4j
import spotify.ApiProvider
import spotify.api.SpotifyApi
import spotify.dto.SpotifySearchResponse

@Slf4j
class SpindicationApplication {


    static void main(String... args) {
        SpotifyApi spotifyApi = new ApiProvider().getApi()

        //Just to prove it works:
        SpotifySearchResponse response = spotifyApi.searchForTrack('Everything is Awesome', 5).blockingSingle()

        log.info("Got track ${response.tracks.trackItems.first().name}")
    }


}
