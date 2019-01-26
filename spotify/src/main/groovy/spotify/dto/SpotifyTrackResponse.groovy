package spotify.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class SpotifyTrackResponse {

    @JsonProperty('items')
    List<SpotifyTrack> trackItems

}
