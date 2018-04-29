package spin.repository

interface Repository<T> {

    T find(String id)

    T createOrUpdate(T object)

    void delete(String id)
}
