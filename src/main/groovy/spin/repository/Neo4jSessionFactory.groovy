package spin.repository

import org.neo4j.ogm.config.ClasspathConfigurationSource
import org.neo4j.ogm.config.Configuration
import org.neo4j.ogm.session.Session
import org.neo4j.ogm.session.SessionFactory


class Neo4jSessionFactory {

    private static ClasspathConfigurationSource configurationSource =
            new ClasspathConfigurationSource("ogm.properties")
    private static Configuration configuration = new Configuration.Builder(configurationSource).build()
    private static SessionFactory sessionFactory = new SessionFactory(configuration, "spin.domain")

    static Neo4jSessionFactory getInstance() {
        return new Neo4jSessionFactory()
    }

    private Neo4jSessionFactory() {
    }

    static Session getNeo4jSession() {
        return sessionFactory.openSession()
    }
}

