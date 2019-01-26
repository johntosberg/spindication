package spotify.dto

import com.fasterxml.jackson.annotation.JsonProperty

class SpotifyTrack {

    @JsonProperty('id')
    String id

    @JsonProperty('name')
    String name

    @JsonProperty('popularity')
    Integer popularity

    @JsonProperty('artists')
    List<SpotifyArtist> artists
}
