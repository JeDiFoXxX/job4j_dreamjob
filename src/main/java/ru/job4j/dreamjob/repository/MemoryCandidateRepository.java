package ru.job4j.dreamjob.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@ThreadSafe
public class MemoryCandidateRepository implements CrudRepository<Candidate> {

    private AtomicInteger nextId = new AtomicInteger(1);

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private MemoryCandidateRepository() {
        save(new Candidate(0, "Ivan Ivanov", "text",
                LocalDateTime.now(), false, 1, 0));
        save(new Candidate(0, "Petr Petrov", "text",
                LocalDateTime.now(), false, 1, 0));
        save(new Candidate(0, "Alexey Sidorov", "text",
                LocalDateTime.now(), false, 1, 0));
        save(new Candidate(0, "Sergey Smirnov", "text",
                LocalDateTime.now(), false, 1, 0));
        save(new Candidate(0, "Dmitry Kozlov", "text",
                LocalDateTime.now(), false, 1, 0));
        save(new Candidate(0, "Andrey Volkov", "text",
                LocalDateTime.now(), false, 1, 0));
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(nextId.incrementAndGet());
        candidates.put(candidate.getId(), candidate);
        return candidate;
    }

    @Override
    public boolean deleteById(int id) {
        return candidates.remove(id) != null;
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidates.computeIfPresent(candidate.getId(),
                (id, oldCandidate) -> new Candidate(oldCandidate.getId(),
                        candidate.getName(),
                        candidate.getDescription(),
                        candidate.getCreationDate(),
                        candidate.getVisible(),
                        candidate.getCityId(),
                        candidate.getFileId())) != null;
    }

    @Override
    public Optional<Candidate> findById(int id) {
        return Optional.ofNullable(candidates.get(id));
    }

    @Override
    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
