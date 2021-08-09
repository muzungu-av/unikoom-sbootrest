package ru.avv.test.unikoomapp.data.dao;

import ru.avv.test.unikoomapp.data.entity.Person;

import java.util.List;

/**
 * The implementing class must be able to provide a list of all users
 */
public interface PersonDAO {
    public List<Person> findAll();
}
