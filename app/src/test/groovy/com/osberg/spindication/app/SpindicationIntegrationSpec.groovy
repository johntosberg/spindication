package com.osberg.spindication.app

import com.osberg.spindication.domain.Track
import com.osberg.spindication.libs.importing.CsvTrackListImporter
import com.osberg.spindication.service.SetlistImporterService
import spock.lang.Ignore
import spock.lang.Specification
import spotify.ApiProvider
import spotify.api.SpotifyApi

@Ignore //Just a POC
class SpindicationIntegrationSpec extends Specification {

    String testFileName = '/testTracklist.csv'
    SetlistImporterService setlistService

    void setup() {
        SpotifyApi spotifyApi = ApiProvider.getApi()
        setlistService = new SetlistImporterService(spotifyApi)
    }

    void 'LETS GET THE TRACKS'() {
        expect:
        List<String> rawTrackList = CsvTrackListImporter.getTracksFromInputStream(this.class.getResourceAsStream(testFileName))
        Set<Track> theTrackMapV0 = []

        Track previousTrack
        boolean firstTrack = true
        //demo - this is what the app will eventually do with a healthy amount of UI for importing tracklists
        rawTrackList.each { String trackTitle ->
            Track discoveredTrack = setlistService.getTrackFromString(trackTitle)
            if (discoveredTrack) {
                if (firstTrack) {
                    firstTrack = false
                    previousTrack = discoveredTrack
                    theTrackMapV0.add(discoveredTrack)
                } else {
                    previousTrack.nextSongsPlayed.add(discoveredTrack)
                    previousTrack = discoveredTrack
                    theTrackMapV0.add(discoveredTrack)
                }
            }
        }

        theTrackMapV0.size() > 0
    }
}
