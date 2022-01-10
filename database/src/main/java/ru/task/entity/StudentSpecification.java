package ru.task.entity;

import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {
    public static Specification<Student> bySurname(String surname) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("surname"), "%" + surname + "%"));
    }

    public static Specification<Student> byGrade(double grade) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.ge(root.join("grades").get("grade"), grade);
    }

    public static Specification<Student> bySubject(String subject) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.join("grades").get("subject"),
                "%" + subject + "%");
    }
}
