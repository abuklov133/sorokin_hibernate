package org.example;

import org.example.service.ProfileService;
import org.example.service.StudentService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext("org.example");
        SessionFactory sessionFactory = context.getBean(SessionFactory.class);
        StudentService studentService = context.getBean(StudentService.class);
        ProfileService profileService = context.getBean(ProfileService.class);

        Student student = new Student("Vasya", 34);
        Student student1 = new Student("Bill", 22);

        studentService.saveStudent(student);
        studentService.saveStudent(student1);

        Profile profile = new Profile("My bio", LocalDateTime.now(), student);

        profileService.saveProfile(profile);

    }
}