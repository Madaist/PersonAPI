package com.example.demo.dal;

import com.example.demo.dal.model.generated.Person;
import com.example.demo.dal.utils.FileUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("TryWithIdenticalCatches")
public class CsvRepository {

    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("persons.csv"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] personDetails = line.split("[,]");
                Person person = new Person();
                person.setLastName(personDetails[1]);
                person.setFirstName(personDetails[2]);
                person.setAge(Integer.parseInt(personDetails[3]));
                person.setSocialSecurityCode(personDetails[4]);

                persons.add(person);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return persons;
    }


    public Person getPersonById(int id) {
        Person person = new Person();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("persons.csv"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] personDetails = line.split("[,]");
                if (Integer.parseInt(personDetails[0]) == id) {
                    person.setLastName(personDetails[1]);
                    person.setFirstName(personDetails[2]);
                    person.setAge(Integer.parseInt(personDetails[3]));
                    person.setSocialSecurityCode(personDetails[4]);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return person;
    }

    public void insertPerson(Person person) {

        List<Integer> ids = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("persons.csv"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] personDetails = line.split("[,]");
                ids.add(Integer.parseInt(personDetails[0]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int id = Collections.max(ids) + 1;

        StringBuilder stringBuilder = new StringBuilder(id + ",")
                .append(person.getLastName() + ",")
                .append(person.getFirstName() + ",")
                .append(person.getAge() + ",")
                .append(person.getSocialSecurityCode());


        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("persons.csv", true));
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {

            printWriter.println(stringBuilder.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deletePerson(int id) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("persons.csv"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] personDetails = line.split("[,]");
                if(Integer.parseInt(personDetails[0]) == id) {
                    FileUtils.removeLine(id + ",");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

