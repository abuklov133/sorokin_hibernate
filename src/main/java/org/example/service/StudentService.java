package org.example.service;


import org.example.Student;
import org.example.TransactionHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public StudentService(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }


    public Student saveStudent(Student student) {
        return transactionHelper.executeInTransaction(session -> {
            session.persist(student);
            return student;
        });
    }


    public void deleteStudent(long id) {
        transactionHelper.executeInTransaction(session -> {
            Student studentForDeleting = session.get(Student.class, id);
            session.remove(studentForDeleting);
        });
    }

    public Student getById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Student.class, id);
        }
    }

    public List<Student> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("SELECT s FROM Student s", Student.class)
                    .list();
        }
    }


    public Student updateStudent(Student student) {
        return transactionHelper.executeInTransaction(session -> {
            return session.merge(student);
        });
    }
}
