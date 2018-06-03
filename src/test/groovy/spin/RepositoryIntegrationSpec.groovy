package spin

import org.neo4j.ogm.session.Session
import spin.repository.Neo4jSessionFactory
import spock.lang.Shared
import spock.lang.Specification

class RepositoryIntegrationSpec extends Specification {

    @Shared
    Session neo4jSession

    void setupSpec() {
        neo4jSession = Neo4jSessionFactory.instance.getNeo4jSession()
    }

}
