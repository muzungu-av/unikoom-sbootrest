package ru.avv.unikoomapp.data.dao.person;

import ru.avv.unikoomapp.data.entity.Person;

/**
 * The implementing class must be able to provide a list of all users
 */
public interface PersonDAO {
    String findAll();
    Person findById(String id);
    Person addOne(Person person);
}
