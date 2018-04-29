package spotify

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.json.JsonSlurper
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.apache.http.Header
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.NameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicHeader
import org.apache.http.message.BasicNameValuePair
import spotify.api.SpotifyApi
import spotify.retrofit.RetrofitClientBuilder

class ApiProvider {

    SpotifyApi getApi(String bearer) {
        new RetrofitClientBuilder()
                .baseUrl('https://api.spotify.com/')
                .addInterceptor(new Interceptor() {
                @Override
                Response intercept(Interceptor.Chain chain) throws IOException {
                    interceptChain(chain, bearer)
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
