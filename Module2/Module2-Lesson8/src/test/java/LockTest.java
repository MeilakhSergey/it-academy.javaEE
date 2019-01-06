import Util.HibernateUtil;
import entities.Person;
import entities.PersonAll;
import entities.PersonDirty;
import entities.PersonVersion;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LockTest {
    @BeforeAll
    static void init() {
        EntityManager em = HibernateUtil.getEntityManager();

        Person person = new Person(null, "Person", "phone1");
        PersonVersion personVersion = new PersonVersion(null, "PersonVersion", "phone2", 0);
        PersonAll personAll = new PersonAll(null, "PersonAll", "phone3");
        PersonDirty personDirty = new PersonDirty(null, "PersonDirty", "phone4");


        em.getTransaction().begin();
        em.persist(person);
        em.persist(personVersion);
        em.persist(personAll);
        em.persist(personDirty);
        em.getTransaction().commit();
        em.clear();
        em.close();
    }

    @Test
    void testNoLock() throws InterruptedException {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        Person person = em.find(Person.class, 1L);
        person.setName("NewName");

        new Thread(() -> {
            EntityManager em2 = HibernateUtil.getEntityManager();
            em2.getTransaction().begin();
            Person person2 = em2.find(Person.class, 1L);
            person2.setName("NoName");
            em2.getTransaction().commit();
            em2.clear();
            em2.close();
        }).start();
        Thread.sleep(1000);

        em.getTransaction().commit();
        em.clear();

        Person personNew = em.find(Person.class, 1L);
        assertEquals("NewName", personNew.getName());

        em.close();
    }

    @Test
    void testLockOPTIMISTIC() throws InterruptedException {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        Person person = em.find(Person.class, 1L, LockModeType.OPTIMISTIC);
        person.setName("NewName");

        new Thread(() -> {
            EntityManager em2 = HibernateUtil.getEntityManager();
            em2.getTransaction().begin();
            Person person2 = em2.find(Person.class, 1L, LockModeType.OPTIMISTIC);
            person2.setName("NoName");
            assertThrows(RollbackException.class, () -> em2.getTransaction().commit());
            em2.getTransaction().rollback();
            em2.clear();
            em2.close();
        }).start();
        Thread.sleep(1000);

        assertThrows(RollbackException.class, () -> em.getTransaction().commit());
        em.getTransaction().rollback();
        em.clear();

        Person personNew = em.find(Person.class, 1L);
        assertEquals("Person", personNew.getName());

        em.close();
    }

    @Test
    void testLockVersion() throws InterruptedException {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        PersonVersion personVersion = em.find(PersonVersion.class, 1L);
        personVersion.setName("NewName");
        long version = personVersion.getVersion();

        new Thread(() -> {
            EntityManager em2 = HibernateUtil.getEntityManager();
            em2.getTransaction().begin();
            PersonVersion personVersion2 = em2.find(PersonVersion.class, 1L);
            personVersion2.setName("testLockVersion");
            em2.getTransaction().commit();
            em2.clear();
            em2.close();
        }).start();
        Thread.sleep(1000);

        assertThrows(RollbackException.class, () -> em.getTransaction().commit());
        em.getTransaction().rollback();
//        em.getTransaction().commit();
        em.clear();

        PersonVersion personVersionFind = em.find(PersonVersion.class, 1L);

        assertEquals(version + 1, personVersionFind.getVersion());
        assertEquals("testLockVersion", personVersionFind.getName());

        em.close();
    }

    @Test
    void testLockAll() throws InterruptedException {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        PersonAll personAll = em.find(PersonAll.class, 1L);
        personAll.setName("NewName");

        new Thread(() -> {
            EntityManager em2 = HibernateUtil.getEntityManager();
            em2.getTransaction().begin();
            PersonAll personAll2 = em2.find(PersonAll.class, 1L);
            personAll2.setName("testLockAll");
            em2.getTransaction().commit();
            em2.clear();
            em2.close();
        }).start();
        Thread.sleep(1000);

        assertThrows(RollbackException.class, () -> em.getTransaction().commit());
        em.getTransaction().rollback();

        em.clear();

        PersonAll personAllFind = em.find(PersonAll.class, 1L);

        assertEquals("testLockAll", personAllFind.getName());

        em.close();
    }

    @Test
    void testLockDirty() throws InterruptedException {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        PersonDirty personDirty = em.find(PersonDirty.class, 1L);
        personDirty.setName("NewName");

        new Thread(() -> {
            EntityManager em2 = HibernateUtil.getEntityManager();
            em2.getTransaction().begin();
            PersonDirty personDirty2 = em2.find(PersonDirty.class, 1L);
            personDirty2.setName("testLockDirty");
            em2.getTransaction().commit();
            em2.clear();
            em2.close();
        }).start();
        Thread.sleep(1000);

        assertThrows(RollbackException.class, () -> em.getTransaction().commit());
        em.getTransaction().rollback();

        em.clear();

        PersonDirty personDirtyFind = em.find(PersonDirty.class, 1L);

        assertEquals("testLockDirty", personDirtyFind.getName());

        em.close();
    }

    @Test
    void testLockVersionOPTIMISTIC() throws InterruptedException {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        PersonVersion personVersion = em.find(PersonVersion.class, 1L, LockModeType.OPTIMISTIC);
        personVersion.setName("NewName");
        long version = personVersion.getVersion();

        new Thread(() -> {
            EntityManager em2 = HibernateUtil.getEntityManager();
            em2.getTransaction().begin();
            PersonVersion personVersion2 = em2.find(PersonVersion.class, 1L, LockModeType.OPTIMISTIC);
            personVersion2.setName("testLockVersionOPTIMISTIC");
            em2.getTransaction().commit();
            em2.clear();
            em2.close();
        }).start();
        Thread.sleep(1000);

        assertThrows(RollbackException.class, () -> em.getTransaction().commit());
        em.getTransaction().rollback();

        em.clear();

        PersonVersion personVersionFind = em.find(PersonVersion.class, 1L);

        assertEquals(version + 1, personVersionFind.getVersion());
        assertEquals("testLockVersionOPTIMISTIC", personVersionFind.getName());

        em.close();
    }

    @Test
    void testLockVersionForceOPTIMISTIC() throws InterruptedException {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        PersonVersion personVersion = em.find(PersonVersion.class, 1L, LockModeType.OPTIMISTIC);
        personVersion.setPhone("NewPhone");
        long version = personVersion.getVersion();

        new Thread(() -> {
            EntityManager em2 = HibernateUtil.getEntityManager();
            em2.getTransaction().begin();
            PersonVersion personVersion2 = em2.find(PersonVersion.class, 1L, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            em2.getTransaction().commit();
            em2.clear();
            em2.close();
        }).start();
        Thread.sleep(1000);

        assertThrows(RollbackException.class, () -> em.getTransaction().commit());
        em.getTransaction().rollback();

        em.clear();

        PersonVersion personVersionFind = em.find(PersonVersion.class, 1L);

        assertEquals(version + 1, personVersionFind.getVersion());
        assertEquals("phone2", personVersionFind.getPhone());

        em.close();
    }

    @Test
    void testLockVersionForceOPTIMISTIClock() throws InterruptedException {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        PersonVersion personVersion = em.find(PersonVersion.class, 1L, LockModeType.OPTIMISTIC);
        personVersion.setPhone("NewPhone");
        long version = personVersion.getVersion();

        new Thread(() -> {
            EntityManager em2 = HibernateUtil.getEntityManager();
            em2.getTransaction().begin();
            PersonVersion personVersion2 = em2.find(PersonVersion.class, 1L);
            em2.lock(personVersion2, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            em2.getTransaction().commit();
            em2.clear();
            em2.close();
        }).start();
        Thread.sleep(1000);

        assertThrows(RollbackException.class, () -> em.getTransaction().commit());
        em.getTransaction().rollback();

        em.clear();

        PersonVersion personVersionFind = em.find(PersonVersion.class, 1L);

        assertEquals(version + 1, personVersionFind.getVersion());
        assertEquals("phone2", personVersionFind.getPhone());

        em.close();
    }

    @Test
    void testLockVersionPESSIMISTIC() throws InterruptedException {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        PersonVersion personVersion = em.find(PersonVersion.class, 1L, LockModeType.PESSIMISTIC_WRITE);
        personVersion.setName("pessimName");
        long version = personVersion.getVersion();

        new Thread(() -> {
            EntityManager em2 = HibernateUtil.getEntityManager();
            em2.getTransaction().begin();
            PersonVersion personVersion2 = em2.find(PersonVersion.class, 1L);
            personVersion2.setName("testLockVersionPESSIMISTIC");
            assertThrows(RollbackException.class, () -> em2.getTransaction().commit());
            em2.getTransaction().rollback();
            em2.close();
        }).start();
        Thread.sleep(1000);

//        assertThrows(RollbackException.class, () -> em.getTransaction().commit());
//        em.getTransaction().rollback();
        em.getTransaction().commit();
        em.clear();

        PersonVersion personVersionFind = em.find(PersonVersion.class, 1L);

        assertEquals(version + 1, personVersionFind.getVersion());
        assertEquals("pessimName", personVersionFind.getName());

        em.close();
    }

    //JPQL
    @Test
    void testLockVersionOPTIMISTICjpql() throws InterruptedException {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("FROM PersonVersion pv WHERE pv.id=1");
        PersonVersion personVersion = (PersonVersion) query.getSingleResult();
        em.lock(personVersion, LockModeType.NONE);
        personVersion.setName("NewName");
        long version = personVersion.getVersion();

        new Thread(() -> {
            EntityManager em2 = HibernateUtil.getEntityManager();
            em2.getTransaction().begin();
            Query query2 = em2.createQuery("FROM PersonVersion pv WHERE pv.id=1");
            PersonVersion personVersion2 = (PersonVersion) query2.getSingleResult();
            personVersion2.setName("testLockVersionOPTIMISTICjpql");
            em2.getTransaction().commit();
            em2.clear();
            em2.close();
        }).start();
        Thread.sleep(1000);

        assertThrows(RollbackException.class, () -> em.getTransaction().commit());
        em.getTransaction().rollback();
        em.clear();

        PersonVersion personVersionFind = em.find(PersonVersion.class, 1L);

        assertEquals(version + 1, personVersionFind.getVersion());
        assertEquals("testLockVersionOPTIMISTICjpql", personVersionFind.getName());

        em.close();
    }
    @Test
    void testLockVersionForceOPTIMISTICjpql() throws InterruptedException {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("FROM PersonVersion pv WHERE pv.id=1");
        PersonVersion personVersion = (PersonVersion) query.getSingleResult();
        em.lock(personVersion, LockModeType.NONE);
        personVersion.setPhone("NewPhone");
        long version = personVersion.getVersion();

        new Thread(() -> {
            EntityManager em2 = HibernateUtil.getEntityManager();
            em2.getTransaction().begin();
            Query query2 = em2.createQuery("FROM PersonVersion pv WHERE pv.id=1");
            PersonVersion personVersion2 = (PersonVersion) query2.getSingleResult();
            em2.lock(personVersion2, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            em2.getTransaction().commit();
            em2.clear();
            em2.close();
        }).start();
        Thread.sleep(1000);

        assertThrows(RollbackException.class, () -> em.getTransaction().commit());
        em.getTransaction().rollback();
        em.clear();

        PersonVersion personVersionFind = em.find(PersonVersion.class, 1L);

        assertEquals(version + 1, personVersionFind.getVersion());
        assertEquals("phone2", personVersionFind.getPhone());

        em.close();
    }
    @Test
    void testLockVersionPESSIMISTICjpql() throws InterruptedException {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("FROM PersonVersion pv WHERE pv.id=1");
        PersonVersion personVersion = (PersonVersion) query.getSingleResult();
        em.lock(personVersion, LockModeType.PESSIMISTIC_WRITE);
        personVersion.setName("pessimName");
        long version = personVersion.getVersion();

        new Thread(() -> {
            EntityManager em2 = HibernateUtil.getEntityManager();
            em2.getTransaction().begin();
            Query query2 = em2.createQuery("FROM PersonVersion pv WHERE pv.id=1");
            PersonVersion personVersion2 = (PersonVersion) query2.getSingleResult();
            assertThrows(OptimisticLockException.class, () -> em2.lock(personVersion2, LockModeType.PESSIMISTIC_WRITE));
            em2.getTransaction().rollback();
            em2.close();
        }).start();
        Thread.sleep(1000);

//        assertThrows(RollbackException.class, () -> em.getTransaction().commit());
//        em.getTransaction().rollback();
        em.getTransaction().commit();
        em.clear();

        PersonVersion personVersionFind = em.find(PersonVersion.class, 1L);

        assertEquals(version + 1, personVersionFind.getVersion());
        assertEquals("pessimName", personVersionFind.getName());

        em.close();
    }
}
