package service

import spock.lang.Specification

class TracklistImporterSpec extends Specification {

    void 'We can get a sanitized track string'() {
        given:
        String crappyName = "\"1. Seating Music\",\"5:55:38 PM\",\"5:56:31 PM\",\"00:00:53\",\"1\",\"\",\"1. Seating Music.mp3\""

        when:
        String betterString = TracklistImporter.getTrackNameFromString(crappyName)

        then:
        betterString == "1. Seating Music"
    }

    void 'We can get a list of just the track names'() {
        given:
        InputStream theCsvFile = this.class.getResourceAsStream("/testTracklist.csv")

        when:
        List<String> rawTrackStrings = TracklistImporter.getTracksFromInputStream(theCsvFile)

        then:
        rawTrackStrings.size() > 0
    }
}
