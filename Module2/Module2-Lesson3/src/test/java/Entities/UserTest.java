package Entities;

import Util.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
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

        //user.setName(user.getName()+"+getter");
        //user.setFamilyName(user.getFamilyName()+"+getter");
        User userFind = new User(Long.valueOf(1), "login1", "password1", "name1+getter", "familyName1+getter", null);
        em.getTransaction().begin();
        //User userFind = em.find(User.class, "login1");
        user = em.unwrap(Session.class).byNaturalId(User.class).using(User_.LOGIN, "login1").load();
        //User userFind = em.unwrap(Session.class).bySimpleNaturalId(User.class).load("login1");
        em.getTransaction().commit();
        assertEquals(userFind, user);

        em.close();
    }

    @Test
    public void testPasswordUpdatable() {
        em = HibernateUtil.getEntityManager();
        User user = new User(null, "login1", "password1", "name1", "familyName1", "111");
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        user = em.unwrap(Session.class).byNaturalId(User.class).using(User_.LOGIN, "login1").load();
        user.setPassword("123");
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        user = em.unwrap(Session.class).byNaturalId(User.class).using(User_.LOGIN, "login1").load();
        em.getTransaction().commit();

        assertNotEquals(user.getPassword(), "123");
        em.close();
    }

    @Test
    public void testPasswordNullAndLength() {
        em = HibernateUtil.getEntityManager();
        User user = new User(null, "login1", "123456789012345", "name1", "familyName1", "111");
        em.getTransaction().begin();
        em.persist(user);
        assertThrows(RollbackException.class, () -> em.getTransaction().commit());

        em.clear();
        User user2 = new User(null, "login1", null, "name1", "familyName1", "111");
        assertThrows(PersistenceException.class, () -> em.persist(user2));

        em.close();
    }

    @Test
    public void testInsertPhone() {
        em = HibernateUtil.getEntityManager();
        User user = new User(null, "login1", "password1", "name1", "familyName1", "111");
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.clear();

        user = em.unwrap(Session.class).byNaturalId(User.class).using(User_.LOGIN, "login1").load();
        assertEquals(null, user.getPhone());

        user.setPhone("12345");
        em.getTransaction().begin();
        em.getTransaction().commit();
        em.clear();

        user = em.unwrap(Session.class).byNaturalId(User.class).using(User_.LOGIN, "login1").load();
        assertEquals("12345", user.getPhone());

        em.close();
    }

    @Test
    public void testGetterAccess() {
        em = HibernateUtil.getEntityManager();
        User user = new User(null, "login1", "password1", "name1", "familyName1", "111");
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.clear();

        user = em.unwrap(Session.class).byNaturalId(User.class).using(User_.LOGIN, "login1").load();
        assertEquals("name1+getter+getter", user.getName());

        em.close();
    }

    @AfterAll
    public static void close() {
        HibernateUtil.close();
    }
}
