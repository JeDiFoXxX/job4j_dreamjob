package ru.job4j.dreamjob.repository;

import java.util.Collection;
import java.util.Optional;

public interface CrudRepository<T> {

    T save(T o);

    boolean deleteById(int id);

    boolean update(T o);

    Optional<T> findById(int id);

    Collection<T> findAll();

}