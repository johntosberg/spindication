package spin.module

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import spin.endpoint.SearchEndpoint
import spin.endpoint.TrackEndpoint
import spin.repository.TrackRepository

class SpinModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SearchEndpoint).in(Scopes.SINGLETON)
        bind(TrackEndpoint).in(Scopes.SINGLETON)
    }
}
