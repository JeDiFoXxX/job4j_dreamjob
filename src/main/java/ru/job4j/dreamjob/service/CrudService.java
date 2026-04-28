package ru.job4j.dreamjob.service;

import java.util.Collection;
import java.util.Optional;

public interface CrudService<T> {

    T save(T object);

    boolean deleteById(int id);

    boolean update(T object);

    Optional<T> findById(int id);

    Collection<T> findAll();
}