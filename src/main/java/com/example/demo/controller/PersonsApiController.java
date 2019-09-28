package com.example.demo.controller;

import com.example.demo.controller.generated.PersonsApi;
import com.example.demo.dal.model.generated.Person;
import com.example.demo.service.PersonService;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonsApiController implements PersonsApi {

    private final Logger logger = LoggerFactory.getLogger(PersonsApiController.class);

    @Autowired
    private PersonService personService;

    @Override
    public ResponseEntity<List<Person>> getAllPersons() {

        List<Person> persons = personService.getAllPersons();
        if (persons == null) {
            logger.info("No persons were found in database");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Person> getPersonById(@PathVariable Integer id) {

        List<Integer> personIds = personService.getPersonIds();
        Person person = personService.getPersonById(id);
        if (person == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> insertPerson(@Valid Person person) {

        personService.insertPerson(person);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deletePerson(Integer id) {

        List<Integer> personIds = personService.getPersonIds();
        if (!personIds.contains(id)) {
            logger.info("Id " + id + " does not exist in database");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updatePerson(Integer id, @Valid Person person) {

        // personService.deletePerson(id);
        //personService.insertPerson(person);
        personService.updatePerson(id, person);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

