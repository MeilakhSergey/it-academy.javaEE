package Entities;

import Entities.oneToMany.Insurance;
import Entities.oneToMany.PersonParent;
import Util.HibernateUtil;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OneToManyTest {
    public static EntityManager em;

    @Test
    public void testAddFind() {
        em = HibernateUtil.getEntityManager();
        PersonParent personParent = new PersonParent(null, "name1", "phone1", new LinkedList<>());
        Insurance insurance1 = new Insurance(null, "number1", "organization1", null);
        Insurance insurance2 = new Insurance(null, "number1", "organization1", null);
        Insurance insurance3 = new Insurance(null, "number1", "organization1", null);
        Insurance insurance4 = new Insurance(null, "number1", "organization1", null);
        Insurance insurance5 = new Insurance(null, "number1", "organization1", null);

        personParent.getInsurance().add(insurance1);
        personParent.getInsurance().add(insurance2);
        personParent.getInsurance().add(insurance3);
        personParent.getInsurance().add(insurance4);
        personParent.getInsurance().add(insurance5);
        insurance1.setPerson(personParent);
        insurance2.setPerson(personParent);
        insurance3.setPerson(personParent);
        insurance4.setPerson(personParent);
        insurance5.setPerson(personParent);

        em.getTransaction().begin();
        em.persist(personParent);
        em.getTransaction().commit();
        em.clear();

        PersonParent personParentFind3 = em.find(PersonParent.class, 1L);
        Insurance insuranceFind = em.find(Insurance.class, 2L);

        em.clear();
        em.close();
        assertThrows(LazyInitializationException.class, () -> personParentFind3.getInsurance().get(0));

        em = HibernateUtil.getEntityManager();

        PersonParent personParentFind2 = em.find(PersonParent.class, 1L);
        for (Insurance ins: personParentFind2.getInsurance()) {
        }
        em.close();

        assertEquals("number1", personParentFind2.getInsurance().get(0).getInsuranceNumber());
        assertEquals("name1", insuranceFind.getPerson().getName());
    }

    @AfterAll
    public static void close() {
        HibernateUtil.close();
    }
}
