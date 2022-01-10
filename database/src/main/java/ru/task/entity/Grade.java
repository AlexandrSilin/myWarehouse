package ru.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "grades")
public class Grade implements Comparable<Grade> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NotNull
    @JsonIgnore
    private Student student;

    @Column(name = "grade")
    private @Range(min = 2, max = 5) double grade;

    @OneToOne
    @NotNull
    private Subject subject;

    public Grade() {

    }

    public Grade(Long id, Student student, double grade, Subject subject) {
        this.id = id;
        this.student = student;
        this.grade = grade;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(@Range(min = 2, max = 5) double grade) {
        this.grade = grade;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public int compareTo(Grade o) {
        return this.subject.getTitle().trim().compareTo(o.subject.getTitle().trim());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade1 = (Grade) o;
        return Double.compare(grade1.grade, grade) == 0 && Objects.equals(id, grade1.id) && Objects.equals(student, grade1.student) && Objects.equals(subject, grade1.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, grade, subject);
    }
}
