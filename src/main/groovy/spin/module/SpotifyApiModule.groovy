package spin.module

import com.google.inject.Provides
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
import ratpack.guice.ConfigurableModule
import spin.config.SpinConfig
import spotify.ApiProvider
import spotify.api.SpotifyApi

class SpotifyApiModule extends ConfigurableModule<SpinConfig> {

    @Override
    protected void configure() {}

    @Provides
    SpotifyApi spotifyApi(SpinConfig config) {
        String clientId = config.clientId
        String clientSecret = config.clientSecret
        //This is a gross way to get the bearer, but I don't have a great pattern yet.
        HttpPost httpPost = new HttpPost("https://accounts.spotify.com/api/token")

        String base64ClientIdSecret = (clientId + ":" + clientSecret).bytes.encodeBase64()
        Header header = new BasicHeader("Authorization", "Basic ${base64ClientIdSecret}")
        List<NameValuePair> params = []
        params.add(new BasicNameValuePair("grant_type","client_credentials"))

        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"))
        httpPost.addHeader(header)

        HttpResponse response = HttpClients.createDefault().execute(httpPost)
        HttpEntity entity = response.getEntity()
        Map<String, String> jsonResponse = new JsonSlurper().parse(entity.getContent())
        String token = jsonResponse.get("access_token")

        new ApiProvider().getApi(token)
    }
}
