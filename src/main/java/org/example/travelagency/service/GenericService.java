package org.example.travelagency.service;

import java.util.List;

public interface GenericService<T, K> {
    T create(T t);

    T readById(K id);

    T update(T t);

    void delete(K id);

    List<T> getAll();
}
