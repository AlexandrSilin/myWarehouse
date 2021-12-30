package ru.task.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.task.entity.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
