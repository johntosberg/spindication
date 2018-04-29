package spin.domain

import spock.lang.Specification

class TrackSpec extends Specification {

    void 'I can make a track and add the next song played'() {
        given:
        Track getLow = new Track(
                name: 'Get low',
                artist: new Artist(
                    name: 'Lil Jon and the East Side Boyz'
                ),
                popularity: 89,
                bpm: 100
        )
        Track yeah = new Track(
                name: 'Yeah!',
                artist: new Artist(
                        name: 'Usher'
                ),
                popularity: 92,
                bpm: 110,
                nextSongsPlayed: [getLow]
        )

        expect:
        yeah.nextSongsPlayed.contains(getLow)
    }

}
