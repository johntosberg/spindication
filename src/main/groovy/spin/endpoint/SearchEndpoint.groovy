package spin.endpoint

import ratpack.groovy.handling.GroovyChainAction
import spotify.api.SpotifyApi

import static ratpack.jackson.Jackson.json

import javax.inject.Inject

class SearchEndpoint extends GroovyChainAction {

    SpotifyApi spotifyApi

    @Inject
    SearchEndpoint(SpotifyApi spotifyApi) {
        this.spotifyApi = spotifyApi
    }

    @Override
    void execute() throws Exception {
        path("search/:track") {
            byMethod {
                get {
                    render json(spotifyApi.searchForTrack(context.pathTokens['track'], 5).blockingSingle())
                }
            }
        }
    }
}
