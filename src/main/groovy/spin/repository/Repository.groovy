package spin.repository

import ratpack.exec.Operation
import ratpack.exec.Promise

interface Repository<T> {

    Promise<List<T>> findAll()

    Promise<T> find(String id, Integer depth)

    Promise<T> createOrUpdate(T object)

    Operation delete(String id)

}
