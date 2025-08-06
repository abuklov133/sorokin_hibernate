package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext("org.example");
        SessionFactory sessionFactory = context.getBean(SessionFactory.class);
        Session session = sessionFactory.openSession();
        Student student = new Student("Vasya", 34);
        Student student1 = new Student("Bill", 22);
        session.beginTransaction();
        session.persist(student);
        session.persist(student1);
        session.getTransaction().commit();


        Student studentById1 = session.get(Student.class, 1L);
        Student studentById2 = session.createQuery(
                        "SELECT s from Student s where s.id = :id", Student.class)
                .setParameter("id", 2)
                .getSingleResult();

        System.out.println(studentById1.toString());
        System.out.println(studentById2.toString());

        session.beginTransaction();
        Student studentForUpdate = session.get(Student.class, 1L);
        studentForUpdate.setAge(20);
        studentForUpdate.setName("Foma");
        session.getTransaction().commit();

        session.beginTransaction();
      /*  Student studentForDeleting = session.get(Student.class, 2L);
        session.remove(studentForDeleting);*/

      /*  session.createQuery("DELETE FROM Student s where s.id = 2")
                .executeUpdate();*/

      /*  session.createNativeQuery("delete from students s where s.id = 2")
                .executeUpdate();

        session.getTransaction().commit();*/

        List<Student> allStudents = session
                .createQuery("SELECT s FROM Student s", Student.class)
                .list();

        Student studentByName = session.createQuery(
                        "SELECT s from Student s where s.name = :name", Student.class)
                .setParameter("name", "Foma")
                .getSingleResult();
        System.out.println("Student by name " + studentByName.toString());

        session.beginTransaction();
        Student student3 = new Student("Bill", 22);
        session.persist(student3);
        session.getTransaction().commit();

        session.close();
    }
}