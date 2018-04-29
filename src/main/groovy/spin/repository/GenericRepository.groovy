package spin.repository

import org.neo4j.ogm.session.Session

abstract class GenericRepository<T> implements Repository<T> {

    private static final int DEPTH_ENTITY = 1
    protected Session session

    GenericRepository(Session session) {
        this.session = session
    }

    @Override
    T find(String id) {
        return session.load(getEntityType(), id, DEPTH_ENTITY)
    }

    @Override
    void delete(String id) {
        session.delete(session.load(getEntityType(), id))
    }

    @Override
    T createOrUpdate(T entity) {
        session.save(entity, DEPTH_ENTITY)
        return find(entity.id)
    }

    abstract Class<T> getEntityType()
}
