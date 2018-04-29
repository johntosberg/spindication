package spin.module

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import spin.endpoint.SearchEndpoint

class SpinModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SearchEndpoint).in(Scopes.SINGLETON)
    }
}
