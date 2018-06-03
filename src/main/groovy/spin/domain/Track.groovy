package spin.domain

import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Property
import org.neo4j.ogm.annotation.Relationship
import spotify.domain.SpotifyTrack

@NodeEntity
class Track {

    @Id
    String id

    @Property(name = 'name')
    String name

    @Property(name = 'rawName')
    String rawName

    @Property(name = 'artist')
    String artist

    @Property(name = 'popularity')
    Integer popularity

    @Property(name = 'bpm')
    Integer bpm

    @Property(name = 'playCount')
    Integer playCount

    @Relationship(type = 'NEXT_SONG_PLAYED', direction = Relationship.OUTGOING)
    List<Track> nextSongsPlayed = []

    static Track fromSpotifyTrack(SpotifyTrack track) {
        return new Track(
                id: track.id,
                name: track.name,
                artist: track.artists ? Artist.fromSpotifyArtist(track.artists.first()) : null,
                popularity: track.popularity,
                bpm: 0, // TODO
        )
    }

    @Override
    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Track track = (Track) o

        if (artist != track.artist) return false
        if (bpm != track.bpm) return false
        if (id != track.id) return false
        if (name != track.name) return false
        if (playCount != track.playCount) return false
        if (popularity != track.popularity) return false
        if (rawName != track.rawName) return false

        return true
    }

    @Override
    int hashCode() {
        int result
        result = id.hashCode()
        result = 31 * result + (name != null ? name.hashCode() : 0)
        result = 31 * result + (rawName != null ? rawName.hashCode() : 0)
        result = 31 * result + (artist != null ? artist.hashCode() : 0)
        result = 31 * result + (popularity != null ? popularity.hashCode() : 0)
        result = 31 * result + (bpm != null ? bpm.hashCode() : 0)
        result = 31 * result + (playCount != null ? playCount.hashCode() : 0)
        return result
    }
}
