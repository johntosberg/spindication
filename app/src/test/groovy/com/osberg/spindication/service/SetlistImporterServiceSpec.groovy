package com.osberg.spindication.service

import com.osberg.spindication.domain.Track
import io.reactivex.Observable
import spock.lang.Specification
import spotify.api.SpotifyApi
import spotify.dto.SpotifySearchResponse
import spotify.dto.SpotifyTrack
import spotify.dto.SpotifyTrackResponse

class SetlistImporterServiceSpec extends Specification {

    SetlistImporterService service
    SpotifyApi spotifyApi

    void setup() {
        spotifyApi = Mock()
        service = new SetlistImporterService(spotifyApi)
        0 * _
    }

    void 'Gets the first track and correctly maps it to a com.osberg.spindication.domain track'() {
        given:
        String trackString = 'My original song'
        String expectedId = UUID.randomUUID().toString()
        SpotifySearchResponse response =
                new SpotifySearchResponse(
                    tracks: new SpotifyTrackResponse(
                            trackItems: [
                                    new SpotifyTrack(
                                            id: expectedId,
                                            name: 'His original song'
                                    ),
                                    new SpotifyTrack(
                                            id: UUID.randomUUID().toString(),
                                            name: 'Almost the original song'
                                    ),
                                    new SpotifyTrack(
                                            id: UUID.randomUUID().toString(),
                                            name: 'Not quite the original song'
                                    )
                            ]
                    )
                )

        when:
        Track theTrack = service.getTrackFromString(trackString)

        then:
        1 * spotifyApi.searchForTrack(trackString, 5) >> Observable.just(response)

        and:
        theTrack.id == expectedId
    }
}
