package spotify

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import spotify.api.SpotifyApi
import spotify.retrofit.RetrofitClientBuilder

class ApiProvider {

    //TODO: wrap these in a class
    static SpotifyApi getApi(String token) {
        new RetrofitClientBuilder()
                .baseUrl('https://api.spotify.com/')
                .addInterceptor(new Interceptor() {
            @Override
            Response intercept(Interceptor.Chain chain) throws IOException {
                interceptChain(chain, token)
            }
        })
                .build()
                .create(SpotifyApi)
    }


    static Response interceptChain(Interceptor.Chain chain, String token) {
        Request request = chain.request()
        request = request.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader('Content-Type', 'application/json')
                .addHeader("Authorization:", "Bearer ${token}")
                .build()
        Response response = chain.proceed(request)
        return response
    }
}
