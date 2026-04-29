package ru.job4j.dreamjob.service;

import java.util.Optional;

public interface FileService<T, E> {

    T save(E object);

    Optional<E> getFileById(int id);

    void deleteById(int id);

    boolean update(int id, E object);
}
