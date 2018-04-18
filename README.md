[![CircleCI](https://circleci.com/gh/johntosberg/spindication.svg?style=svg)](https://circleci.com/gh/johntosberg/spindication)

# spindication
Spinndication is an application made by DJs for DJs. It is intended to help DJ's answer the question that comes up in every set "What was that one song I played after this one that people went crazy for?"

In the first iteration it will take in a DJ's Serato setlists, store them in a directed graph database, and query that database to find the next track (node).

## running
indication is a simple groovy application. The only runtime requirement is [docker.](https://www.docker.com/)

To start the application:

`docker-compose up -d`

`./gradlew run -Dspotify.token=${yourGeneratedSpotifyToken}`

## about me / contributing
I am relatively new to open source and application development. As such, I am open to contributions in both code and design. I'll be pretty bummed if you rip-off my idea and start a company with it, but my intention here is to make something I want to use, and learn along the way.

### [trello](https://trello.com/b/6Ut0l0Ag)
### [name credit](https://github.com/TheoKanning)
