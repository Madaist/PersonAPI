package com.example.demo.service;

import com.example.demo.dal.CsvRepository;
import com.example.demo.dal.model.generated.Person;
import com.example.demo.util.ErrorCodes;
import com.example.demo.util.ServiceException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persondal.dal.impl.PersonDaoImpl;
import persondal.dal.model.PersonDTO;

@Service
public class PersonService {

    private final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    CsvRepository csvRepository;
    @Autowired
    PersonDaoImpl personDao;

    public List<Person> getAllPersons() {

        logger.info("GET METHOD: getAllPersons");
        // List<Person> persons = csvRepository.getAllPersons();
        List<PersonDTO> personsDTO = personDao.getAllPersons();
        List<Person> persons = new ArrayList<>();
        for(PersonDTO p : personsDTO) {
            persons.add(mapDTOtoPerson(p));
        }
        logger.info("Method response: " + persons);
        return persons;

    }

    public Person getPersonById(int id) {

        logger.info(" GET METHOD: getPersonById where id = " + id);
        //Person person = csvRepository.getPersonById(id);
        //List<Integer> ids = personDao.getPersonsIds();
        //if(!ids.contains(id))
        //throw new ServiceException(ErrorCodes.ID_NOT_FOUND);
        PersonDTO personDTO = personDao.getPersonById(id);
        Person person = mapDTOtoPerson(personDTO);
        logger.info("Method response: " + person);
        return person;

    }

    public void insertPerson(Person person) {

        logger.info("PUT METHOD: insertPerson");
        logger.info("Person to be inserted: " + person);

        if (person.getAge() < 18)
            throw new ServiceException(ErrorCodes.BAD_REQUEST_AGE_FORMAT);
        if (! person.getLastName().matches("[a-zA-Z-]+"))
            throw new ServiceException(ErrorCodes.BAD_REQUEST_LASTNAME);
        if (! person.getFirstName().matches("[a-zA-Z-]+"))
            throw new ServiceException(ErrorCodes.BAD_REQUEST_FIRSTNAME);

        // csvRepository.insertPerson(person);
        PersonDTO personDTO = mapPersonToDTO(person, Collections.max(getPersonIds())+1);
        personDao.insertPerson(personDTO);

    }

    public void deletePerson(int id) {

        logger.info("DELETE METHOD: deletePerson by id " + id);
        // csvRepository.deletePerson(id);
        List<Integer> ids = personDao.getPersonsIds();
        if (!ids.contains(id))
            throw new ServiceException(ErrorCodes.ID_NOT_FOUND);
        personDao.deletePerson(id);
    }

    public List<Integer> getPersonIds() {
        logger.info("METHOD: getPersonIds");
        return personDao.getPersonsIds();

    }

    public void updatePerson(int id, Person person) {
        logger.info("UPDATE METHOD: updatePerson by id " + id);
        logger.info("Updated person is: " + person);
        List<Integer> ids = personDao.getPersonsIds();
        if (!ids.contains(id))
            throw new ServiceException(ErrorCodes.ID_NOT_FOUND);
        if (person.getAge() < 18)
            throw new ServiceException(ErrorCodes.BAD_REQUEST_AGE_FORMAT);
        if (! person.getLastName().matches("[a-zA-Z-]+"))
            throw new ServiceException(ErrorCodes.BAD_REQUEST_LASTNAME);
        if (! person.getFirstName().matches("[a-zA-Z-]+"))
            throw new ServiceException(ErrorCodes.BAD_REQUEST_FIRSTNAME);
        PersonDTO personDTO = mapPersonToDTO(person, id);
        personDao.updatePerson(id, personDTO);
    }

    public static Person mapDTOtoPerson(PersonDTO personDTO) {

        Person person = new Person();
        person.setLastName(personDTO.getLastName());
        person.setFirstName(personDTO.getFirstName());
        person.setAge(personDTO.getAge());
        person.setSocialSecurityCode(personDTO.getSocialSecurityCode());

        return person;
    }

    public PersonDTO mapPersonToDTO(Person person, int id) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(id);
        personDTO.setLastName(person.getLastName());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setAge(person.getAge());
        personDTO.setSocialSecurityCode(person.getSocialSecurityCode());

        return personDTO;
    }
}

