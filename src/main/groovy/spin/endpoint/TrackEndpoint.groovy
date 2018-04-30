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
        path("track/:id/next_played/:id2") {
            byMethod {
                post {
                    String id = context.pathTokens['id']
                    String id2 = context.pathTokens['id2']

                    Track trackPlayed = repository.find(id, 1)
                    Track nextPlayed = repository.find(id2, 1)
                    if (trackPlayed && nextPlayed) {
                        trackPlayed.nextSongsPlayed.add(nextPlayed)
                    }
                    render json(repository.createOrUpdate(trackPlayed))
                }
            }
        }
        path("track/:id") {
            byMethod {
                get {
                    String id = context.pathTokens['id']
                    Integer depth = context.request.queryParams['depth'] as Integer
                    render json(repository.find(id, depth))
                }
            }
        }
        path("all") {
            byMethod {
                get {
                    render json(repository.findAll())
                }
            }
        }
    }
}
