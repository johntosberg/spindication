package domain

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Property
import spotify.domain.SpotifyArtist

@NodeEntity
class Artist {

    @Id
    private String id

    @Property(name="name")
    String name

    static Artist fromSpotifyArtist(SpotifyArtist artist) {
        return new Artist(
                id: artist.id,
                name: artist.name
        )
    }
}
