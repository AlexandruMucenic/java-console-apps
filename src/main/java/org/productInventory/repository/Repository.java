package org.productInventory.repository;

import org.productInventory.domain.BaseEntity;

import java.util.Optional;

public interface Repository<ID, T extends BaseEntity<ID>> {
    Optional<T> findOne(ID id);

    Iterable<T> findAll();

    Optional<T> save(T entity) throws RuntimeException;

    Optional<T> delete(ID id);

    Optional<T> update(T entity) throws RuntimeException;
}