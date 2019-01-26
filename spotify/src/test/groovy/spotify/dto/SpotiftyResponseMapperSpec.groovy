package spotify.dto

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Specification

class SpotiftyResponseMapperSpec extends Specification {

    static ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    void "can map a json response from spotify to the object"() {
        expect:
        String json = getJsonFromFile('/spotify_search_response.json')

        SpotifySearchResponse response = classFromJson(json, SpotifySearchResponse)
        response.tracks.trackItems.id.contains('2syQ0f4SAQdzvlAewPl1DJ')
    }

    String getJsonFromFile(String fileName) {
        this.class.getResource(fileName).text
    }

    static <T> T classFromJson(String json, Class<T> toTheClass) {
        return objectMapper.readValue(json, toTheClass)
    }
}
