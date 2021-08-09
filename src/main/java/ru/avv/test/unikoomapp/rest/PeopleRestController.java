package ru.avv.test.unikoomapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/person/{id}")
    public String getPeople(@PathVariable String id) {
        Person person = personDAO.findById(id);
        return person.toString();
    }

    @PostMapping(value = "/person",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String addPeople(@RequestBody Person person) {
        if (personDAO.addOne(person)) {
            return "Person was created.";
        } else
            return "The operation doesn't successful!";
    }
}
