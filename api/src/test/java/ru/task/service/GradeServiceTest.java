package ru.task.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.task.entity.Grade;
import ru.task.entity.Student;
import ru.task.entity.Subject;
import ru.task.persist.GradeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GradeServiceTest {
    private GradeService gradeService;
    private GradeRepository gradeRepository;

    @BeforeEach
    public void init() {
        gradeRepository = mock(GradeRepository.class);
        gradeService = new GradeServiceImpl(gradeRepository);
    }

    @Test
    public void testFindAll() {
        List<Grade> expectedGrades = new ArrayList<>();
        expectedGrades.add(new Grade(1L, new Student(), 4D, new Subject()));
        expectedGrades.add(new Grade(2L, new Student(), 4.5D, new Subject()));
        when(gradeRepository.findAll()).thenReturn(expectedGrades);
        List<Grade> actualGrades = gradeService.findAll();
        assertNotNull(actualGrades);
        assertEquals(expectedGrades, actualGrades);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L})
    public void testFindById(Long id) {
        Grade expectedGrade = new Grade(id, new Student(), 5D, new Subject());
        when(gradeRepository.findById(id)).thenReturn(Optional.of(expectedGrade));
        Grade actualGrade = gradeService.findById(id).get();
        assertNotNull(actualGrade);
        assertEquals(expectedGrade, actualGrade);
    }
}
