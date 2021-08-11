package ru.avv.unikoomapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.avv.unikoomapp.data.dao.foto.FotoDAO;
import ru.avv.unikoomapp.data.dao.person.PersonDAO;
import ru.avv.unikoomapp.data.entity.Foto;
import ru.avv.unikoomapp.data.entity.Person;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Implements several rest-methods.
 */
@RestController
@RequestMapping("/api")
public class PeopleRestController {

    private PersonDAO personDAO;
    private FotoDAO fotoDAO;

    @Autowired
    public PeopleRestController(PersonDAO personDAO, FotoDAO fotoDAO) {
        this.personDAO = personDAO;
        this.fotoDAO = fotoDAO;
    }

    @GetMapping(value = "/people", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllPeople() {
        return personDAO.findAll();
    }

    @GetMapping(value = "/person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getOnePerson(@PathVariable String id) {
        Person person = personDAO.findById(id);
        return person.toJsonString();
    }

    @PostMapping(value = "/person",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person addOnePerson(@RequestBody Person person) {
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

    @PostMapping(value = "/foto")
    public String addFoto(@RequestParam MultipartFile file,
                          @RequestParam String user_id) throws IOException {
        String hash = DigestUtils.md5DigestAsHex(file.getInputStream());
        InputStream in = file.getInputStream();
        String filename = "files" + File.separator + hash;
        File dir = new File(filename);
        dir.getParentFile().mkdirs();

        FileOutputStream f = new FileOutputStream(dir.getAbsolutePath());
        int ch = 0;
        while ((ch = in.read()) != -1) {
            f.write(ch);
        }
        f.flush();
        f.close();

        return file.getName();
    }

    @GetMapping(value = "/fotos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Foto> getFotosListDescription(@PathVariable String person_id) {
        List<Foto> fotos = fotoDAO.findAll(person_id);
        return fotos;
    }

}