package Entities;

import Entities.Component.Address;
import Entities.Component.Person;
import Util.HibernateUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComponentTest {
    public static EntityManager em;

/*    @AfterEach
    public void init() {
        em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Person p").executeUpdate();
        em.getTransaction().commit();
        em.clear();
    }*/

    @Test
    public void testAddFind() {
        em = HibernateUtil.getEntityManager();
        Address address = new Address("City1", "Street1", "Building1", "Flat1");
        Address homeAddress = new Address("City000", "Street000", "Building000", "Flat000");
        Person person = new Person(null, "Name1", "FamilyName1", "Phone1", address, homeAddress);
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.clear();

        Person personFind = em.find(Person.class, 1L);
        System.out.println(personFind);
        assertEquals(personFind, person);

        em.close();
    }

    @AfterAll
    public static void close() {
        HibernateUtil.close();
    }
}
