package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Vacancy;
import ru.job4j.dreamjob.repository.MemoryVacancyRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@ThreadSafe
public class VacancyService implements CrudService<Vacancy> {

    @Autowired
    private MemoryVacancyRepository memoryVacancyRepository;

    @Autowired
    private SimpleFileService fileService;

    private VacancyService() {
    }

    @Override
    public Vacancy save(Vacancy vacancy) {
        return memoryVacancyRepository.save(vacancy);
    }

    @Override
    public boolean deleteById(int id) {
        var rsl = false;
        var fileOptional = findById(id);
        if (fileOptional.isPresent()) {
            rsl = memoryVacancyRepository.deleteById(id);
            fileService.deleteById(fileOptional.get().getFileId());
        }
        return rsl;
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
