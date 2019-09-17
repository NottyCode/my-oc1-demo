package my.demo.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.microshed.testing.jupiter.MicroShedTest;
import org.microshed.testing.testcontainers.MicroProfileApplication;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import my.demo.Person;
import my.demo.PersonService;

@MicroShedTest
public class PersonServiceIT {
    
    @Container
    public static MicroProfileApplication app = new MicroProfileApplication()
                    .withAppContextRoot("/");
    
    @Inject
    public static PersonService personSvc;
    
    @Test
    public void testGetPerson() throws Exception {
        Long bobId = personSvc.createPerson("Bob", 24);
        Person bob = personSvc.getPerson(bobId);
        assertNotNull(bob, "Person should exist");
        assertEquals("Bob", bob.name);
        assertEquals(24, bob.age);
    }

    @Test
    public void testGetAllPeople() {
        Long person1Id = personSvc.createPerson("Person1", 1);
        Long person2Id = personSvc.createPerson("Person2", 2);

        Person expected1 = new Person("Person1", 1, person1Id);
        Person expected2 = new Person("Person2", 2, person2Id);

        Collection<Person> allPeople = personSvc.getAllPeople();
        assertTrue(allPeople.size() >= 2, "Expected at least 2 people to be registered, but there were only: " + allPeople);
        assertTrue(allPeople.contains(expected1), "Did not find person " + expected1 + " in all people: " + allPeople);
        assertTrue(allPeople.contains(expected2), "Did not find person " + expected2 + " in all people: " + allPeople);
    }

    @Test
    public void testDelete() {
        long deletablePerson = personSvc.createPerson("DeletablePerson", 5);

        personSvc.removePerson(deletablePerson);

        assertNull(personSvc.getPerson(deletablePerson), "DeletablePerson should not exist");
    }
}