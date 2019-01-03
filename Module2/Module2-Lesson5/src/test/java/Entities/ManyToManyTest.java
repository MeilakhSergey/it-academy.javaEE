package Entities;

import Entities.manyToMany.Insurance;
import Entities.manyToMany.PersonParent;
import Util.HibernateUtil;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ManyToManyTest {
    public static EntityManager em;

    @Test
    public void testAddFind() {
        em = HibernateUtil.getEntityManager();

        PersonParent personParent1 = new PersonParent(null, "name1", "phone1", new LinkedList<>());
        PersonParent personParent2 = new PersonParent(null, "name2", "phone2", new LinkedList<>());
        PersonParent personParent3 = new PersonParent(null, "name3", "phone3", new LinkedList<>());
        Insurance insurance1 = new Insurance(null, "number1", "organization2", new LinkedList<>());
        Insurance insurance2 = new Insurance(null, "number2", "organization2", new LinkedList<>());
        Insurance insurance3 = new Insurance(null, "number3", "organization3", new LinkedList<>());

        personParent1.getInsurance().add(insurance1);
        personParent1.getInsurance().add(insurance2);
        personParent2.getInsurance().add(insurance1);
        personParent2.getInsurance().add(insurance2);
        personParent3.getInsurance().add(insurance1);
        personParent3.getInsurance().add(insurance3);

        insurance1.getPerson().add(personParent1);
        insurance1.getPerson().add(personParent2);
        insurance1.getPerson().add(personParent3);
        insurance2.getPerson().add(personParent1);
        insurance2.getPerson().add(personParent2);
        insurance3.getPerson().add(personParent3);

        em.getTransaction().begin();
        em.persist(personParent1);
        em.persist(personParent2);
        em.persist(personParent3);
        em.getTransaction().commit();
        em.clear();

        PersonParent personParentFind1 = em.find(PersonParent.class, 1L);
        Insurance insuranceFind1 = em.find(Insurance.class, 2L);

        em.clear();
        em.close();
        assertThrows(LazyInitializationException.class, () -> personParentFind1.getInsurance().get(0));

        em = HibernateUtil.getEntityManager();

        PersonParent personParentFind2 = em.find(PersonParent.class, 1L);
        Insurance insuranceFind2 = em.find(Insurance.class, 2L);
        for (Insurance ins: personParentFind2.getInsurance()) {
        }
        for (PersonParent pp: insuranceFind2.getPerson()) {

        }
        em.close();

        assertEquals("number1", personParentFind2.getInsurance().get(0).getInsuranceNumber());
        assertEquals("name1", insuranceFind2.getPerson().get(0).getName());
    }

    @Test
    void testDeleteBond() {
        em = HibernateUtil.getEntityManager();

        PersonParent personParent1 = new PersonParent(null, "name1", "phone1", new LinkedList<>());
        Insurance insurance1 = new Insurance(null, "number1", "organization2", new LinkedList<>());

        personParent1.getInsurance().add(insurance1);
        insurance1.getPerson().add(personParent1);

        em.getTransaction().begin();
        em.persist(personParent1);
        em.getTransaction().commit();
        em.clear();

        PersonParent personParentFind = em.find(PersonParent.class, 1L);
        Insurance insuranceFind = em.find(Insurance.class, 2L);

        personParentFind.setInsurance(new LinkedList<>());
        insuranceFind.setPerson(new LinkedList<>());

        em.getTransaction().begin();
//        em.merge(personParent1);
//        em.merge(personParent2);
//        em.merge(personParent3);
        em.merge(insuranceFind);
//        em.merge(insurance2);
//        em.merge(insurance3);
        em.getTransaction().commit();
        em.clear();

        PersonParent personParentFind2 = em.find(PersonParent.class, 1L);
        assertEquals(0, personParentFind2.getInsurance().size());
    }

    @AfterAll
    public static void close() {
        HibernateUtil.close();
    }
}
