package ru.avv.unikoomapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.avv.unikoomapp.data.dao.foto.FotoDAO;
import ru.avv.unikoomapp.data.dao.person.PersonDAO;
import ru.avv.unikoomapp.data.entity.Foto;
import ru.avv.unikoomapp.data.entity.Person;
import ru.avv.unikoomapp.exception.ErrorResponse;
import ru.avv.unikoomapp.service.FileService;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * Implements several rest-methods.
 */
@RestController
@RequestMapping("/api")
public class PeopleRestController {

    private PersonDAO personDAO;
    private FotoDAO fotoDAO;
    private FileService fileService;

    @Autowired
    public PeopleRestController(PersonDAO personDAO, FotoDAO fotoDAO, FileService fileService) {
        this.personDAO = personDAO;
        this.fotoDAO = fotoDAO;
        this.fileService = fileService;
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
                          @RequestParam String person_id) throws IOException {

        String hash = DigestUtils.md5DigestAsHex(file.getInputStream());
        if (!fileService.storeFile(file, hash).isEmpty()) {
            Foto foto = new Foto();
            foto.setPersonId(Long.decode(person_id));
            foto.setFileHash(hash);
            foto.setFileName(file.getOriginalFilename());
            fotoDAO.addOne(foto);
        }
        return hash;
    }

    @GetMapping(value = "/fotos/{person_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Foto> getFotosListDescription(@PathVariable String person_id) {
        List<Foto> fotos = fotoDAO.findAll(person_id);
        return fotos;
    }

    @GetMapping("/foto/{file_hash}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String file_hash, HttpServletRequest request) throws FileNotFoundException {
        Resource resource = fileService.loadFileAsResource(file_hash);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            ErrorResponse response = new ErrorResponse("IO Exception", ex.getMessage());
            return ResponseEntity.internalServerError().body(resource);
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}