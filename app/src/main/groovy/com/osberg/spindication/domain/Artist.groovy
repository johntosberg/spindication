package com.osberg.spindication.domain

import spotify.dto.SpotifyArtist

class Artist {

    String id
    String name

    static Artist fromSpotifyArtist(SpotifyArtist artist) {
        return new Artist(
                id: artist.id,
                name: artist.name
        )
    }
}
