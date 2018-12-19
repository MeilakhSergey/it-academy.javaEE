package Entities;

import Util.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

public class UserIDTest {
    public static EntityManager em;

    @BeforeEach
    public void init() {
        em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM UserID1 u").executeUpdate();
        em.createQuery("DELETE FROM UserID2 u").executeUpdate();
        em.createQuery("DELETE FROM UserID3 u").executeUpdate();
        em.createQuery("DELETE FROM UserID4 u").executeUpdate();
        em.createQuery("DELETE FROM UserID5 u").executeUpdate();
        em.getTransaction().commit();
        em.clear();
    }

    @Test
    public void testAddFind() {
        em = HibernateUtil.getEntityManager();
        UserID1 user1 = new UserID1(null, "login1", "password1", "name1", "familyName1", "111");
        UserID2 user2 = new UserID2(null, "login1", "password1", "name1", "familyName1", "111");
        UserID3 user3 = new UserID3(null, "login1", "password1", "name1", "familyName1", "111");
        UserID4 user4 = new UserID4(null, "login1", "password1", "name1", "familyName1", "111");
        UserID5 user5 = new UserID5(null, "login1", "password1", "name1", "familyName1", "111");
        em.getTransaction().begin();
        em.persist(user1);
        em.persist(user2);
        em.persist(user3);
        em.persist(user4);
        em.persist(user5);
        em.getTransaction().commit();
        em.clear();

        user1 = em.unwrap(Session.class).byNaturalId(UserID1.class).using(UserID1_.LOGIN, "login1").load();
        user2 = em.unwrap(Session.class).byNaturalId(UserID2.class).using(UserID2_.LOGIN, "login1").load();
        user3 = em.unwrap(Session.class).byNaturalId(UserID3.class).using(UserID3_.LOGIN, "login1").load();
        user4 = em.unwrap(Session.class).byNaturalId(UserID4.class).using(UserID4_.LOGIN, "login1").load();
        user5 = em.unwrap(Session.class).byNaturalId(UserID5.class).using(UserID5_.LOGIN, "login1").load();
        System.out.println("ID user1: " + user1.getId() +
                            "\n\r ID user2:" + user2.getId() +
                            "\n\r ID user3:" + user3.getId() +
                            "\n\r ID user4:" + user4.getId() +
                            "\n\r ID user5:" + user5.getId()
                            );

        UserID1 user11 = new UserID1(null, "login11", "password1", "name1", "familyName1", "111");
        UserID2 user12 = new UserID2(null, "login11", "password1", "name1", "familyName1", "111");
        UserID3 user13 = new UserID3(null, "login11", "password1", "name1", "familyName1", "111");
        UserID4 user14 = new UserID4(null, "login11", "password1", "name1", "familyName1", "111");
        UserID5 user15 = new UserID5(null, "login11", "password1", "name1", "familyName1", "111");
        em.getTransaction().begin();
        em.persist(user11);
        em.persist(user12);
        em.persist(user13);
        em.persist(user14);
        em.persist(user15);
        em.getTransaction().commit();
        em.clear();

        user11 = em.unwrap(Session.class).byNaturalId(UserID1.class).using(UserID1_.LOGIN, "login11").load();
        user12 = em.unwrap(Session.class).byNaturalId(UserID2.class).using(UserID2_.LOGIN, "login11").load();
        user13 = em.unwrap(Session.class).byNaturalId(UserID3.class).using(UserID3_.LOGIN, "login11").load();
        user14 = em.unwrap(Session.class).byNaturalId(UserID4.class).using(UserID4_.LOGIN, "login11").load();
        user15 = em.unwrap(Session.class).byNaturalId(UserID5.class).using(UserID5_.LOGIN, "login11").load();
        System.out.println("ID user11: " + user11.getId() +
                        "\n\r ID user12:" + user12.getId() +
                        "\n\r ID user13:" + user13.getId() +
                        "\n\r ID user14:" + user14.getId() +
                        "\n\r ID user15:" + user15.getId()
                        );


        em.close();
    }

    @AfterAll
    public static void close() {
        HibernateUtil.close();
    }
}
