package ru.task.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.cloud.context.named.NamedContextFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.task.entity.Grade;
import ru.task.entity.Student;
import ru.task.entity.StudentListParams;
import ru.task.entity.Subject;
import ru.task.persist.StudentRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentServiceTest {
    private StudentService studentService;
    private StudentRepository studentRepository;

    @BeforeEach
    public void init() {
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentServiceImpl(studentRepository);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L})
    public void testFindById(Long id) {
        Student expectedStudent = getExpectedStudent(id);
        when(studentRepository.findById(id)).thenReturn(Optional.of(getExpectedStudent(id)));
        Student actualStudent = studentService.findById(id).get();
        assertNotNull(actualStudent);
        assertEquals(expectedStudent, actualStudent);
    }

    private Student getExpectedStudent(Long id) {
        Student student = new Student();
        student.setId(id);
        student.setSurname("Test_" + id);
        Set<Grade> grades = new HashSet<>();
        grades.add(new Grade(1L, student, 5d, new Subject(1L, "test_subj_" + id)));
        grades.add(new Grade(2L, student, 4d, new Subject(2L, "test_subj_" + id)));
        student.setGrades(grades);
        return student;
    }
}
