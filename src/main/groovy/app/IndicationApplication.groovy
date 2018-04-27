package app


class IndicationApplication {

    //TODO: change this to be the client ID and dynamically obtain the token on startup instead
    static final String SPOTIFY_BEARER = System.getProperty('spotify.token')

    static void main(String... args) {
        if (!SPOTIFY_BEARER) {
            throw new RuntimeException('No spotify bearer found. Set runtime java property \'spotify.token\' to your id')
        }
    }


}
