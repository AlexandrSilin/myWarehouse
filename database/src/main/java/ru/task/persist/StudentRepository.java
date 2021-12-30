package ru.task.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.task.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
