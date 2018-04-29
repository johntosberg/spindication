package spin.module

import com.google.inject.Provides
import ratpack.guice.ConfigurableModule
import spin.config.SpinConfig
import spin.repository.Neo4jSessionFactory
import spin.repository.TrackRepository

class RepositoryModule extends ConfigurableModule<SpinConfig> {

    @Override
    protected void configure() {}

    @Provides
    TrackRepository repository() {
        new TrackRepository(Neo4jSessionFactory.getInstance().getNeo4jSession())
    }
}
