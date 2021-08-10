package ru.avv.unikoomapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.avv.unikoomapp.data.dao.PersonDAO;
import ru.avv.unikoomapp.data.entity.Person;

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
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person addPeople(@RequestBody Person person) {
        boolean checkFields = person.getBirthDate() == null
                || person.getEmail() == null || person.getEmail().isEmpty()
                || person.getFio() == null || person.getFio().isEmpty()
                || person.getUserName() == null || person.getUserName().isEmpty()
                || person.isSex() == null;
        if (checkFields) {
            throw new IllegalArgumentException("One or more field is empty.");
        } else {
            personDAO.addOne(person);
            return person;
        }
    }
}
