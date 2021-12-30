package ru.task.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.task.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
