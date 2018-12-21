package Entities;

import Entities.SingleTable.Child1;
import Entities.SingleTable.Child2;
import Entities.SingleTable.Item;
import Util.HibernateUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleTableTest {
    public static EntityManager em;

   /* @AfterEach
    public void init() {
        em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Item i").executeUpdate();
        em.getTransaction().commit();
        em.clear();
    }*/

    @Test
    public void testAddFind() {
        em = HibernateUtil.getEntityManager();

        Child1 child1 = new Child1(null, "ItemName1", "ItemNameColumn1", "childName1", "ChildFamilyName1", "childPhone1");
        Child1 child11 = new Child1(null, "ItemName1", "ItemNameColumn1", "childName1", "ChildFamilyName1", "childPhone1");
        Child2 child2 = new Child2(null, "ItemName2", "ItemNameColumn2", "childName2", "ChildFamilyName2", "childPhone2");
        Item item = new Item(null, "Item1", "Item2");
        em.getTransaction().begin();
        em.persist(child1);
        em.persist(child11);
        em.persist(child2);
        em.persist(item);
        em.getTransaction().commit();
        em.clear();

        Item itemFind = em.find(Child1.class, 1L);
        assertEquals(itemFind, child1);
        System.out.println(itemFind);

        em.close();
    }

    @AfterAll
    public static void close() {
        HibernateUtil.close();
    }
}
