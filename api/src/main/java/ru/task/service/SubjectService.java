package ru.task.service;

import ru.task.entity.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    List<Subject> findAll();

    Optional<Subject> findById(Long id);

    void deleteById(Long id);

    void save(Subject subject);
}
