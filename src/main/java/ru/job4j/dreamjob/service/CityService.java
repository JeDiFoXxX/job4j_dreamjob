package ru.job4j.dreamjob.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.repository.MemoryCityRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CityService implements  CrudService<City> {

    @Autowired
    private MemoryCityRepository memoryCityRepository;

    @Override
    public City save(City city) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public boolean update(City city) {
        return false;
    }

    @Override
    public Optional<City> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<City> findAll() {
        return memoryCityRepository.findAll();
    }
}
