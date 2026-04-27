package ru.job4j.dreamjob.repository;

import ru.job4j.dreamjob.model.Candidate;

import java.util.Collection;

public interface CandidateRepository {

    Candidate save(Candidate candidate);

    Collection<Candidate> findAll();
}
