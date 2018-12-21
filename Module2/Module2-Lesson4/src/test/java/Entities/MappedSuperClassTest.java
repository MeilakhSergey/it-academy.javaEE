package Entities;

import Entities.Component.Address;
import Entities.Component.Person;
import Entities.MappedSuperClass.Child1;
import Entities.MappedSuperClass.Child2;
import Entities.MappedSuperClass.Item;
import Util.HibernateUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MappedSuperClassTest {
    public static EntityManager em;

/*    @AfterEach
    public void init() {
        em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Child1 c").executeUpdate();
        em.createQuery("DELETE FROM Child2 c").executeUpdate();
        em.getTransaction().commit();
        em.clear();
    }*/

    @Test
    public void testAddFind() {
        em = HibernateUtil.getEntityManager();

        Child1 child1 = new Child1(null, "ItemName1", "ItemNameColumn1", "childName1", "ChildFamilyName1", "childPhone1");
        Child1 child11 = new Child1(null, "ItemName1", "ItemNameColumn1", "childName1", "ChildFamilyName1", "childPhone1");
        Child2 child2 = new Child2(null, "ItemName2", "ItemNameColumn2", "childName2", "ChildFamilyName2", "childPhone2");
        em.getTransaction().begin();
        em.persist(child1);
        em.persist(child11);
        em.persist(child2);
        em.getTransaction().commit();

        Item item = em.find(Child1.class, 1L);
        assertEquals(item, child1);
        System.out.println(item);


        em.close();
    }

    @AfterAll
    public static void close() {
        HibernateUtil.close();
    }
}
