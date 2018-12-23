package Entities;

import Entities.oneToOne.Insurance;
import Entities.oneToOne.PersonParent;
import Util.HibernateUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OneToOneTest {
    public static EntityManager em;

    @Test
    public void testAddFind() {
        em = HibernateUtil.getEntityManager();
        PersonParent personParent = new PersonParent(null, "name1", "phone1", null);
        Insurance insurance = new Insurance(null, "number1", "organization1", null);

        personParent.setInsurance(insurance);
        insurance.setPerson(personParent);

        em.getTransaction().begin();
        em.persist(personParent);
        em.getTransaction().commit();
        em.clear();

        PersonParent personParentFind = em.find(PersonParent.class, 1L);
        assertEquals(personParentFind, insurance.getPerson());

        Insurance insuranceFind = em.find(Insurance.class, 2L);
        assertEquals(personParent, insuranceFind.getPerson());

        em.close();
    }

    @AfterAll
    public static void close() {
        HibernateUtil.close();
    }
}
