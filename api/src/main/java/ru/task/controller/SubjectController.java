package ru.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.task.entity.Subject;
import ru.task.service.SubjectService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/all")
    public List<Subject> findAll() {
        return subjectService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Subject> findById(@PathVariable Long id) {
        return subjectService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        subjectService.deleteById(id);
    }

    @PostMapping(produces = "application/json")
    public void save(@RequestBody Subject subject) {
        subjectService.save(subject);
    }
}
