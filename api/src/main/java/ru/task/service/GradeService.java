package ru.task.service;

import ru.task.entity.Grade;

import java.util.List;
import java.util.Optional;

public interface GradeService {
    List<Grade> findAll();

    Optional<Grade> findById(Long id);

    void deleteById(Long id);

    void save(Grade grade);
}
