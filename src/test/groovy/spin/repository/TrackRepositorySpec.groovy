package spin.repository

import ratpack.test.exec.ExecHarness
import spin.RepositoryIntegrationSpec
import spin.domain.Track
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class TrackRepositorySpec extends RepositoryIntegrationSpec {

    @Shared
    TrackRepository repository

    @Shared
    Track riversAndRoads

    @AutoCleanup
    ExecHarness execHarness = ExecHarness.harness()

    void setupSpec() {
        repository = new TrackRepository(neo4jSession)
        riversAndRoads = new Track(
                name: 'Rivers and Roads',
                id: UUID.randomUUID().toString(),
                artist: "The Head and the Heart"
        )
    }

    void 'The track repository is not null'() {
        expect:
        repository
    }

    void 'Can get and add individual tracks'() {
        when:
        execHarness.yield { repository.createOrUpdate(riversAndRoads) }
        Track theFetchedTrack = execHarness.yield { repository.find(riversAndRoads.id) }.value

        then:
        theFetchedTrack == riversAndRoads
    }

    void 'Deleting the track will delete it'() {
        when:
        execHarness.execute { repository.delete(riversAndRoads.id) }

        then:
        execHarness.yield { repository.find(riversAndRoads.id) }.value == null
    }

}
