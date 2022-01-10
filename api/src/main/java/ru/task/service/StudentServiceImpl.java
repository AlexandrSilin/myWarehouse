package ru.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.task.entity.Student;
import ru.task.entity.StudentListParams;
import ru.task.entity.StudentSpecification;
import ru.task.persist.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Page<Student> findAll(StudentListParams params) {
        Specification<Student> specification = Specification.where(null);
        Optional.ofNullable(params.getSurname()).ifPresent(s -> specification.and(StudentSpecification.bySurname(s)));
        Optional.ofNullable(params.getGrade()).ifPresent(g -> specification.and(StudentSpecification.byGrade(g)));
        Optional.ofNullable(params.getSubject()).ifPresent(s -> specification.and(StudentSpecification.bySubject(s)));
        return studentRepository.findAll(specification, PageRequest.of(
                Optional.ofNullable(params.getPage()).orElse(1) - 1,
                Optional.ofNullable(params.getSize()).orElse(10),
                Sort.by(Optional.ofNullable(params.getSortField()).filter(f -> !f.isBlank()).orElse("id"))
        ));
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }
}
