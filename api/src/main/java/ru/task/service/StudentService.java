package ru.task.service;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;
import ru.task.entity.Student;
import ru.task.entity.StudentListParams;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Page<Student> findAll(StudentListParams params);

    Optional<Student> findById(Long id);

    void deleteById(Long id);

    void save(Student student);
}
