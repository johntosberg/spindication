package spotify

import com.osberg.spindication.libs.api.retrofit.RetrofitClientBuilder
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

class ApiProvider {

    static final String SPOTIFY_CLIENT = System.getenv('SPOTIFY_ID')
    static final String SPOTIFY_SECRET = System.getenv('SPOTIFY_SECRET')

    static SpotifyApi getApi() {
        String bearer = getAuth()
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

    private static String getAuth() {
        HttpPost httpPost = new HttpPost("https://accounts.spotify.com/api/token")

        String base64ClientIdSecret = (SPOTIFY_CLIENT + ":" + SPOTIFY_SECRET).bytes.encodeBase64()
        Header header = new BasicHeader("Authorization", "Basic ${base64ClientIdSecret}")
        List<NameValuePair> params = []
        params.add(new BasicNameValuePair("grant_type","client_credentials"))

        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"))
        httpPost.addHeader(header)

        HttpResponse response = HttpClients.createDefault().execute(httpPost)
        HttpEntity entity = response.getEntity()
        Map<String, String> jsonResponse = new JsonSlurper().parse(entity.getContent())
        String token = jsonResponse.get("access_token")
        return token
    }
}
