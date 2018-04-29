package spin.repository

import org.neo4j.ogm.session.Session
import spin.domain.Track

class TrackRepository extends GenericRepository<Track> {

    TrackRepository(Session session) {
        super(session)
    }

    @Override
    Class<Track> getEntityType() {
        return Track.class
    }

}
