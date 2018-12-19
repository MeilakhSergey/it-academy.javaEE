package Entities;

import Util.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserNamingStrategyTest {
    public static EntityManager em;

    @BeforeEach
    public void init() {
        em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM User u").executeUpdate();
        em.getTransaction().commit();
        em.clear();
    }

    @Test
    public void testAddFind() {
        em = HibernateUtil.getEntityManager();
        User user = new User(null, "login1", "password1", "name1", "familyName1", "111");
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.clear();

        em.close();
    }

    @AfterAll
    public static void close() {
        HibernateUtil.close();
    }
}
