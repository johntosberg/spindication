package spin.repository

import org.neo4j.ogm.session.Session

abstract class GenericRepository<T> implements Repository<T> {

    private static final int DEPTH_LIST = 0
    private static final int DEPTH_ENTITY = 1
    protected Session session

    GenericRepository(Session session) {
        this.session = session
    }

    @Override
    List<T> findAll() {
        return session.loadAll(getEntityType(), DEPTH_LIST)
    }

    @Override
    T find(String id, Integer depth) {
        session.clear() //TODO: read more about caching. I want depth to reloaded each time
        return session.load(getEntityType(), id, depth)
    }

    @Override
    void delete(String id) {
        session.delete(session.load(getEntityType(), id))
    }

    @Override
    T createOrUpdate(T entity) {
        session.save(entity, DEPTH_ENTITY)
        return find(entity.id, 1)
    }

    abstract Class<T> getEntityType()
}
