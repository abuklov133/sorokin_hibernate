package org.example;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "grad_year", nullable = false)
    private long graduationYear;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private List<Student> studentList;

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Group() {
    }

    public Group(String name, long graduationYear) {
        this.name = name;
        this.graduationYear = graduationYear;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(long graduationYear) {
        this.graduationYear = graduationYear;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
