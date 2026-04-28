package ru.job4j.dreamjob.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Vacancy;
import ru.job4j.dreamjob.repository.MemoryVacancyRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class VacancyCrudService implements CrudService<Vacancy> {

    @Autowired
    private MemoryVacancyRepository memoryVacancyRepository;

    private VacancyCrudService() { }

    @Override
    public Vacancy save(Vacancy vacancy) {
        return memoryVacancyRepository.save(vacancy);
    }

    @Override
    public boolean deleteById(int id) {
        return memoryVacancyRepository.deleteById(id);
    }

    @Override
    public boolean update(Vacancy vacancy) {
        return memoryVacancyRepository.update(vacancy);
    }

    @Override
    public Optional<Vacancy> findById(int id) {
        return memoryVacancyRepository.findById(id);
    }

    @Override
    public Collection<Vacancy> findAll() {
        return memoryVacancyRepository.findAll();
    }
}
