package spotify.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class SpotifySearchResponse {

    @JsonProperty('tracks')
    SpotifyTrackResponse tracks

    @JsonProperty('artists')
    SpotifyArtistResponse artists
}
