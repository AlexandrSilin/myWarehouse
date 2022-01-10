package ru.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.task.entity.Grade;
import ru.task.entity.Student;
import ru.task.entity.StudentListParams;
import ru.task.service.GradeService;
import ru.task.service.StudentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final GradeService gradeService;

    @Autowired
    public StudentController(StudentService studentService, GradeService gradeService) {
        this.studentService = studentService;
        this.gradeService = gradeService;
    }

    @GetMapping("/all")
    public List<Student> findAll(@RequestParam Optional<String> surname,
                                 @RequestParam Optional<Double> grade,
                                 @RequestParam Optional<String> subject,
                                 @RequestParam Optional<Integer> page,
                                 @RequestParam Optional<Integer> size,
                                 @RequestParam Optional<String> sortField) {
        StudentListParams params = new StudentListParams();
        surname.ifPresent(params::setSurname);
        grade.ifPresent(params::setGrade);
        subject.ifPresent(params::setSubject);
        page.ifPresent(params::setPage);
        size.ifPresent(params::setSize);
        sortField.ifPresent(params::setSortField);
        return studentService.findAll(params).getContent();
    }

    @GetMapping("/{id}")
    public Optional<Student> findById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        if (student.isPresent()) {
            for (Grade grade : student.get().getGrades()) {
                gradeService.deleteById(grade.getId());
            }
            studentService.deleteById(id);
        }
    }

    @PostMapping(produces = "application/json")
    public void save(@RequestBody Student student) {
        studentService.save(student);
    }
}
