package Entities;

import Util.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

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

        User userFind = new User(Long.valueOf(1), "login1", "password1", "name1+getter", "familyName1+getter", null);
        user = em.unwrap(Session.class).byNaturalId(User.class).using(User_.LOGIN, "login1").load();

        assertEquals(userFind, user);

        em.close();
    }

    @AfterAll
    public static void close() {
        HibernateUtil.close();
    }
}
