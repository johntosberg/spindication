package domain

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Property
import org.neo4j.ogm.annotation.Relationship

@NodeEntity
class Track {

    @Id @GeneratedValue
    private Long id;

    @Property(name = 'name')
    String name

    @Property(name = 'artist')
    Artist artist

    @Property(name = 'popularity')
    Integer popularity

    @Property(name = 'bpm')
    Integer bpm

    @Relationship(type = 'NEXT_SONG_PLAYED', direction = Relationship.OUTGOING)
    List<Track> nextSongPlayed

}
