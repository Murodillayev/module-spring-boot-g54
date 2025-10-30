package uz.pdp.todo.service;

import java.util.List;


/**
 * @param <D>
 * @param <CD>
 * @param <UD>
 * @param <K>
 */
public interface CRUDService<D, CD, UD, K> {
    D create(CD dto);

    D update(K id, UD dto);

    D get(K id);

    List<D> getAll();

    void delete(K id);
}
