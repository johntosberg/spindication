package spin.endpoint

import ratpack.groovy.handling.GroovyChainAction
import spin.domain.Track
import spin.repository.TrackRepository

import javax.inject.Inject

import static ratpack.jackson.Jackson.json

class TrackEndpoint extends GroovyChainAction {

    TrackRepository repository

    @Inject
    TrackEndpoint(TrackRepository repository) {
        this.repository = repository
    }

    @Override
    void execute() throws Exception {
        path("track") {
            byMethod {
                post {
                    parse(Track).then {
                        Track track ->
                            render json(repository.createOrUpdate(track))
                    }
                }
            }
        }
        path("track/:id") {
            byMethod {
                get {
                    String id = context.pathTokens['id']
                    render json(repository.find(id))
                }
            }
        }
    }
}
