package ru.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.task.entity.Grade;
import ru.task.service.GradeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grades")
public class GradeController {
    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/all")
    public List<Grade> findAll() {
        return gradeService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Grade> findById(@PathVariable Long id) {
        return gradeService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        gradeService.deleteById(id);
    }

    @PostMapping(produces = "application/json")
    public void save(@RequestBody Grade grade) {
        gradeService.save(grade);
    }
}
