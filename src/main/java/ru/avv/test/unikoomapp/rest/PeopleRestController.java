package ru.avv.test.unikoomapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.avv.test.unikoomapp.data.dao.PersonDAO;
import ru.avv.test.unikoomapp.data.entity.Person;

import java.util.List;

/**
 * Implements several rest-methods.
 */
@RestController
@RequestMapping("/api")
public class PeopleRestController {
    private PersonDAO personDAO;

    @Autowired
    public PeopleRestController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/people")
    public List<Person> getPeople() {
        return personDAO.findAll();
    }
}
