import Util.HibernateUtil;
import entities.Meeting;
import entities.NoCache;
import entities.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.QueryHints;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class CacheTest {
    @BeforeAll
    static void init() {
        EntityManager em = HibernateUtil.getEntityManager();

        Person person1 = new Person(null, "Person1", "phone1", new LinkedList<>());
        Person person2 = new Person(null, "Person2", "phone2", new LinkedList<>());
        Person person3 = new Person(null, "Person3", "phone3", new LinkedList<>());
        Meeting meeting1 = new Meeting(null, "subject1", new LinkedList<>(), new LinkedList<>());
        Meeting meeting2 = new Meeting(null, "subject2", new LinkedList<>(), new LinkedList<>());
        NoCache noCache = new NoCache(null, "name", new LinkedList<>());

        person1.getMeetings().add(meeting1);
        person1.getMeetings().add(meeting2);
        person2.getMeetings().add(meeting1);
        person2.getMeetings().add(meeting2);
        person3.getMeetings().add(meeting2);

        meeting1.getPeople().add(person1);
        meeting1.getPeople().add(person2);
        meeting2.getPeople().add(person1);
        meeting2.getPeople().add(person2);
        meeting2.getPeople().add(person3);

        meeting1.getNoCache().add(noCache);
        noCache.getMeetings().add(meeting1);

        em.getTransaction().begin();
        em.persist(person1);
        em.persist(person2);
        em.persist(person3);
        em.persist(meeting1);
        em.persist(meeting2);
        em.persist(noCache);
        em.getTransaction().commit();
        em.clear();
        em.close();
    }

    @Test
    void testSimple() {
        EntityManager em = HibernateUtil.getEntityManager();

        System.out.println("-------------------------------First query:" +
                HibernateUtil.getEmFactory().unwrap(SessionFactory.class).getStatistics().getSecondLevelCacheHitCount());
        Meeting meeting = em.find(Meeting.class, 1L);

        System.out.println("-------------------------------No query:" +
                HibernateUtil.getEmFactory().unwrap(SessionFactory.class).getStatistics().getSecondLevelCacheHitCount());
        em.clear();
        em.close();

        em = HibernateUtil.getEntityManager();
        meeting = em.find(Meeting.class, 1L);                                           //Использование кэша 2го уровня

        System.out.println("-------------------------------No query:" +
                HibernateUtil.getEmFactory().unwrap(SessionFactory.class).getStatistics().getSecondLevelCacheHitCount());
        HibernateUtil.getEmFactory().getCache().evictAll();                                 //Очистка кэша 2го уровня
        meeting = em.find(Meeting.class, 1L);                                            //Использование кэша 1го уровня

        System.out.println("-------------------------------New query:" +
                HibernateUtil.getEmFactory().unwrap(SessionFactory.class).getStatistics().getSecondLevelCacheHitCount());
        em.clear();
        em.close();
        em = HibernateUtil.getEntityManager();
        meeting = em.find(Meeting.class, 1L);                                             //Новый запрос

        System.out.println("-------------------------------Finish test");
    }

    @Test
    void testSession() {
        EntityManager em = HibernateUtil.getEntityManager();
        Session session = em.unwrap(Session.class);

        System.out.println("-------------------------------First query:" +
                HibernateUtil.getEmFactory().unwrap(SessionFactory.class).getStatistics().getSecondLevelCacheHitCount());
        Meeting meeting = session.get(Meeting.class, 1L);
        session.clear();

        System.out.println("-------------------------------No query:" +
                HibernateUtil.getEmFactory().unwrap(SessionFactory.class).getStatistics().getSecondLevelCacheHitCount());
        session.beginTransaction();                                                                                         //??????????????????????
        meeting = session.get(Meeting.class, 1L);                                   //Использование кэша 2го уровня
        session.getTransaction().commit();                                                                                         //??????????????????????

        HibernateUtil.getEmFactory().unwrap(SessionFactory.class).getCache().evictAll();       //Очистка кэша 2го уровня

        System.out.println("-------------------------------No query:" +
                HibernateUtil.getEmFactory().unwrap(SessionFactory.class).getStatistics().getSecondLevelCacheHitCount());
        meeting = session.get(Meeting.class, 1L);                                   //Использование кэша 1го уровня

        session.clear();

        System.out.println("-------------------------------New query:" +
                HibernateUtil.getEmFactory().unwrap(SessionFactory.class).getStatistics().getSecondLevelCacheHitCount());
        meeting = session.get(Meeting.class, 1L);                                   //Новый запрос

        System.out.println("-------------------------------Finish test");
    }

    @Test
    void testQueryCacheEM() {
        EntityManager em = HibernateUtil.getEntityManager();

        System.out.println("-------------------------------First query:");
        Query query = em.createQuery("SELECT m FROM Meeting m WHERE m.id = 1");
        query.setHint("org.hibernate.cacheable", "true");
//        query.setHint( QueryHints.HINT_CACHEABLE, "true")
//                .setHint( "javax.persistence.cache.retrieveMode " , CacheRetrieveMode.USE )
//                .setHint( "javax.persistence.cache.storeMode" , CacheStoreMode.REFRESH );
        Meeting meeting = (Meeting) query.getSingleResult();
        em.clear();

        em.close();
        em = HibernateUtil.getEntityManager();

        System.out.println("-------------------------------No query:");
        query = em.createQuery("SELECT m FROM Meeting m WHERE m.id = 1");
        query.setHint("org.hibernate.cacheable", "true");
//        query.setHint( QueryHints.HINT_CACHEABLE, "true")
//                .setHint( "javax.persistence.cache.retrieveMode " , CacheRetrieveMode.USE )
//                .setHint( "javax.persistence.cache.storeMode" , CacheStoreMode.REFRESH );
        meeting = (Meeting) query.getSingleResult();
//        meeting = em.find(Meeting.class, 1L);
        assertEquals("subject1", meeting.getSubject());

        System.out.println("-------------------------------New query:");
        query = em.createQuery("SELECT m FROM Meeting m WHERE m.subject = 'subject1'");
        query.setHint("org.hibernate.cacheable", "true");
        Meeting meetingNew = (Meeting) query.getSingleResult();

        assertEquals(meeting, meetingNew);

        System.out.println("-------------------------------Finish test");
    }

    @Test
    void testQueryCacheSession() {
        EntityManager em = HibernateUtil.getEntityManager();
        Session session = em.unwrap(Session.class);

        System.out.println("-------------------------------First query:");
        org.hibernate.query.Query query = session.createQuery("SELECT m FROM Meeting m WHERE m.id = 1");
        query.setCacheable(true);
        Meeting meeting = (Meeting) query.getSingleResult();

        session.clear();

        em = HibernateUtil.getEntityManager();                                                              //????????????????
        session = em.unwrap(Session.class);

        System.out.println("-------------------------------No query:");
        org.hibernate.query.Query query2 = session.createQuery("SELECT m FROM Meeting m WHERE m.id = 1");
        query2.setCacheable(true);
        meeting = (Meeting) query2.getSingleResult();
        assertEquals("subject1", meeting.getSubject());

        System.out.println("-------------------------------Finish test");
    }

    @Test
    void testManyToMany() {
        EntityManager em = HibernateUtil.getEntityManager();

        System.out.println("-------------------------------First query:");
        Meeting meeting = em.find(Meeting.class, 1L);
        meeting.getPeople().size();

        em.clear();
        em.close();
        em = HibernateUtil.getEntityManager();

        System.out.println("-------------------------------No query:");
        Meeting meetingNew = em.find(Meeting.class, 1L);

        System.out.println("-------------------------------No query:");
        assertEquals(2, meetingNew.getPeople().size());

        em.clear();
        em.close();
        em = HibernateUtil.getEntityManager();

        System.out.println("-------------------------------No query:");
        Meeting meetingNew2 = em.find(Meeting.class, 1L);
        em.getTransaction().begin();
        System.out.println("-------------------------------Update query:");
        meetingNew2.getPeople().get(0).setPhone("111111");
        em.getTransaction().commit();

        em.clear();
        em.close();
        em = HibernateUtil.getEntityManager();

        System.out.println("-------------------------------No query:");
        Meeting meetingNew3 = em.find(Meeting.class, 1L);

        System.out.println("-------------------------------No query:");
        assertEquals("111111", meetingNew3.getPeople().get(0).getPhone());

        System.out.println("-------------------------------Finish test");
    }

    @Test
    void testManyToMany2() {
        EntityManager em = HibernateUtil.getEntityManager();

        System.out.println("-------------------------------First query:");
        Meeting meeting = em.find(Meeting.class, 1L);
        System.out.println("-------------------------------Collection query:");
        meeting.getNoCache().size();

        em.clear();
        em.close();
        em = HibernateUtil.getEntityManager();

        System.out.println("-------------------------------No query:");
        meeting = em.find(Meeting.class, 1L);

        System.out.println("-------------------------------Collection query:");
        NoCache noCache = meeting.getNoCache().get(0);
        System.out.println("-------------------------------No query:");
        assertEquals("name", noCache.getName());

        System.out.println("-------------------------------Finish test");
    }
}
