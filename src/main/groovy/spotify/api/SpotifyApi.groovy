package spotify.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import spotify.domain.SpotifySearchResponse

interface SpotifyApi {

    @GET('/v1/search?type=track')
    Observable<SpotifySearchResponse> searchForTrack(@Query('q') String trackString,
                                                          @Query('limit') Integer limit)


}