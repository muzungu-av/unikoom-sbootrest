package ru.avv.unikoomapp.data.dao;

import ru.avv.unikoomapp.data.entity.Person;

import java.util.List;

/**
 * The implementing class must be able to provide a list of all users
 */
public interface PersonDAO {
    List<Person> findAll();
    Person findById(String id);
    Person addOne(Person person);
}
