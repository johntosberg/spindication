package app

import groovy.json.JsonSlurper
import org.apache.http.Header
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.NameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicHeader
import org.apache.http.message.BasicNameValuePair
import spotify.ApiProvider
import spotify.api.SpotifyApi


class IndicationApplication {
    static final String SPOTIFY_CLIENT = System.getProperty('client.id')
    static final String SPOTIFY_SECRET = System.getProperty('client.secret')

    static void main(String... args) {
        if (!(SPOTIFY_CLIENT && SPOTIFY_SECRET)) {
            throw new RuntimeException('No spotify client found. Set runtime java property \'spotify.clientId\' and  \'spotify.clientSecret\'')
        }

        //This is a gross way to get the bearer, but I don't have a great pattern yet.
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

        SpotifyApi spotifyApi = new ApiProvider().getApi(token)

        //Just to prove it works:
        spotifyApi.searchForTrack('Everything is Awesome', 5).blockingSingle()

    }


}
