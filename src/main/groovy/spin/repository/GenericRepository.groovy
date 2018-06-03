package spin.repository

import org.neo4j.ogm.session.Session
import ratpack.exec.Blocking
import ratpack.exec.Operation
import ratpack.exec.Promise

abstract class GenericRepository<T> implements Repository<T> {

    private static final int DEPTH_LIST = 0
    private static final int DEPTH_ENTITY = 1
    protected Session session

    GenericRepository(Session session) {
        this.session = session
    }

    @Override
    Promise<List<T>> findAll() {
        Blocking.get{
            session.loadAll(getEntityType(), DEPTH_LIST).toList()
        }
    }

    @Override
    Promise<T> find(String id, Integer depth = 1) {
        Blocking.get {
            session.clear() //TODO: read more about caching. I want depth to reloaded each time
            session.load(getEntityType(), id, depth)
        }
    }

    @Override
    Operation delete(String id) {
        Blocking.op {
            session.delete(session.load(getEntityType(), id))
        }
    }

    @Override
    Promise<T> createOrUpdate(T entity) {
        Blocking.get {
            session.save(entity, DEPTH_ENTITY)
            entity
        }
    }

    abstract Class<T> getEntityType()
}
