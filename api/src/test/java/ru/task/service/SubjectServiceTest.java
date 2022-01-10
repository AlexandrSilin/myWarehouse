package ru.task.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.task.entity.Subject;
import ru.task.persist.SubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SubjectServiceTest {
    private SubjectService subjectService;
    private SubjectRepository subjectRepository;

    @BeforeEach
    public void init() {
        subjectRepository = mock(SubjectRepository.class);
        subjectService = new SubjectServiceImpl(subjectRepository);
    }

    @Test
    public void testFindAll() {
        List<Subject> subjectList = new ArrayList<>();
        subjectList.add(new Subject(1L, "test1"));
        subjectList.add(new Subject(2L, "test2"));
        when(subjectRepository.findAll()).thenReturn(subjectList);
        List<Subject> actualSubjects = subjectService.findAll();
        assertNotNull(actualSubjects);
        assertEquals(subjectList, actualSubjects);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L})
    public void testFindById(Long id) {
        Subject expectedSubject = new Subject(id, "test_" + id);
        when(subjectRepository.findById(id)).thenReturn(Optional.of(new Subject(id, "test_" + id)));
        Subject actualSubject = subjectService.findById(id).get();
        assertNotNull(actualSubject);
        assertEquals(expectedSubject, actualSubject);
    }
}
