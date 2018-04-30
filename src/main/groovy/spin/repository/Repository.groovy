package spin.repository

interface Repository<T> {

    List<T> findAll()

    T find(String id, Integer depth)

    T createOrUpdate(T object)

    void delete(String id)

}
