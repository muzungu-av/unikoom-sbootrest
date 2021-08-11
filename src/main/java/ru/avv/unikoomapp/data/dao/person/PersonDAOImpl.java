package ru.avv.unikoomapp.data.dao.person;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.avv.unikoomapp.data.entity.Person;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Data storage and access mechanism layer the Person table
 */
@Repository
public class PersonDAOImpl implements PersonDAO {

    private EntityManager entityManager;

    @Autowired
    public PersonDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public String findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query<Person> query = session.createQuery("from Person", Person.class);
        List<Person> people = query.getResultList();
        String peopleStringAccum = people.stream()
                .map(person ->  person.toShortJsonString())
                .collect(Collectors.joining(",", "[", "]") );
        return peopleStringAccum;
    }

    @Transactional
    public Person findById(String id) {
        Session session = entityManager.unwrap(Session.class);
        Query<Person> query = session.createQuery("from Person where id = :id");
        query.setParameter("id", Long.decode(id));
        Person person = query.getSingleResult();
        return person;
    }

    @Transactional
    public Person addOne(Person person) {
        Session session = entityManager.unwrap(Session.class);
        session.save(person);
        return person;
    }
}
