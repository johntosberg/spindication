package com.osberg.spindication.domain

import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Property
import org.neo4j.ogm.annotation.Relationship
import spotify.dto.SpotifyTrack

@NodeEntity
class Track {

    @Id
    String id

    @Property(name = 'name')
    String name

    @Property(name = 'rawName')
    String rawName

    @Property(name = 'artist')
    Artist artist

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
}
