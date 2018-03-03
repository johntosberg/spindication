package app

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Request
import okhttp3.Response
import spotify.domain.SpotifySearchResponse
import spotify.retrofit.RetrofitClientBuilder
import spotify.api.SpotifyApi

class IndicationApplication {

    //TODO: change this to be the client ID and dynamically obtain the token on startup instead
    static final String SPOTIFY_BEARER = System.getProperty('spotify.token')

    static void main(String... args) {
        if (!SPOTIFY_BEARER) {
            throw new RuntimeException('No spotify bearer found. Set runtime java property \'spotify.token\' to your id')
        }
        SpotifyApi api = getApi()
        //Just to prove that this works until I write tests
        SpotifySearchResponse response = api.searchForTrack('get low', 3).blockingSingle()
        response.tracks.trackItems.size() > 0
    }

    //TODO: wrap these in a class
    static SpotifyApi getApi() {
        new RetrofitClientBuilder()
                .baseUrl('https://api.spotify.com/')
                .addInterceptor(new Interceptor() {
            @Override
            Response intercept(Chain chain) throws IOException {
                interceptChain(chain)
            }
        })
                .build()
                .create(SpotifyApi)
    }


    static Response interceptChain(Chain chain) {
        Request request = chain.request()
        request = request.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader('Content-Type', 'application/json')
                .addHeader("Authorization:", "Bearer ${SPOTIFY_BEARER}")
                .build()
        Response response = chain.proceed(request)
        return response
    }


}
