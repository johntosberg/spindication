package domain

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Property

@NodeEntity
class Artist {

    @Id @GeneratedValue
    private Long id

    @Property(name="name")
    String name

}
