package com.example.demo;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import persondal.dal.impl.PersonDaoImpl;
import persondal.dal.model.PersonDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaRepositories(basePackageClasses = PersonDaoImpl.class)
public class RepositoryTest {

   /* @Parameter
    public int id;
    @Parameter
    public int foundId;

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { 14, 14 }, {7, 7}, { 11, 11} };
        return Arrays.asList(data);
    }*/

    @Autowired
    private PersonDaoImpl tester;

    @Test(timeout = 100)
    public void shouldFindPersonById() {

        PersonDTO personDTO = tester.getPersonById(7);
        assertEquals( 7, personDTO.getId());
    }

    @Test(expected = NullPointerException.class)
    public void shouldNotFindPersonById() {

        PersonDTO personDTO = tester.getPersonById(200);
        assertEquals( 200, personDTO.getId());
    }
}



