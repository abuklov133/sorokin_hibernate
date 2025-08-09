package org.example.service;

import org.example.Group;
import org.example.TransactionHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public GroupService(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public Group saveGroup(
            String number,
            Long gradYear
    ) {
        return transactionHelper.executeInTransaction(session -> {
            var group = new Group(number, gradYear);
            session.persist(group);
            return group;
        });

    }

    public List<Group> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("""                                  
                                    SELECT g FROM Group g
                                    left join fetch g.studentList
                            """, Group.class)
                    .list();
        }
    }
}

