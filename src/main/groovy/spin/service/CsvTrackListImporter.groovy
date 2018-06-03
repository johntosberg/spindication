package spin.service

class CsvTrackListImporter {

    static List<String> getTracksFromInputStream(InputStream stream) {
        if (!stream) { return [] }
        List<String> rawTracks = []
        stream.eachLine {
            rawTracks << getTrackNameFromString(it)
        }
        return rawTracks
    }

    static String getTrackNameFromString(String trackString) {
        trackString = trackString.replaceAll("\"","") //Strip the quotes
        Integer songEnd = trackString.indexOf(",") //who cares about the other columns
        trackString[0..(songEnd-1)] // Strip the other columns
    }
}
