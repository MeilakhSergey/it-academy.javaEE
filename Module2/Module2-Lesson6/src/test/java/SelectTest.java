import Util.HibernateUtil;
import entities.Person;
import entities.PersonChild;
import entities.PersonWrapper;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SelectTest {
    @BeforeAll
    static void insertEntities() {
        EntityManager em = HibernateUtil.getEntityManager();
        Person person1 = new Person(null, "login1", "name1", LocalDate.of(2001, 1, 1), new LinkedList<>());
        Person person2 = new Person(null, "login2", "name2", LocalDate.of(2001, 1, 1), new LinkedList<>());
        Person person3 = new Person(null, "login2", "name2", LocalDate.of(2001, 3, 3), new LinkedList<>());
        Person person4 = new Person(null, "login2", "name4", LocalDate.of(1991, 4, 4), new LinkedList<>());
        Person person5 = new Person(null, "login5", "name5", LocalDate.of(2020, 5, 5), new LinkedList<>());
        Person person6 = new Person(null, "login6", "Name6", LocalDate.of(1991, 6, 6), new LinkedList<>());

        PersonChild personChild1 = new PersonChild(null, "child1", 20, person1);
        PersonChild personChild2 = new PersonChild(null, "child2", 8, person1);
        PersonChild personChild3 = new PersonChild(null, "child3", 120, person1);
        PersonChild personChild4 = new PersonChild(null, "child3", 25, person2);
        PersonChild personChild5 = new PersonChild(null, "child4", 18, person2);

        person1.getPersonChild().add(personChild1);
        person1.getPersonChild().add(personChild2);
        person1.getPersonChild().add(personChild3);
        person2.getPersonChild().add(personChild4);
        person2.getPersonChild().add(personChild5);

        em.getTransaction().begin();
        em.persist(person1);
        em.persist(person2);
        em.persist(person3);
        em.persist(person4);
        em.persist(person5);
        em.persist(person6);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    void findByName() {
        EntityManager em = HibernateUtil.getEntityManager();

        String jpql = "SELECT p FROM Person p WHERE p.name='name2'";        //Находит 2 сущности по имени
        Query query = em.createQuery(jpql);
        List<Person> results = query.getResultList();
        for (Person p: results) {
            System.out.println("----------------------------------" + p);
        }

        jpql = "SELECT p FROM Person p WHERE p.name=:name";        //Находит 1 сущность по имени (Именнованный параметр)
        query = em.createQuery(jpql);
        query.setParameter("name", "name1");
        Person result = (Person) query.getSingleResult();
        System.out.println("----------------------------------" + result);

        em.close();
    }

    @Test
    void findByNameAndAge() {
        EntityManager em = HibernateUtil.getEntityManager();

        String jpql = "SELECT p FROM Person p WHERE p.name LIKE :name AND current_date() > p.dateOfBirth";      //находит по имени и дате
        Query query = em.createQuery(jpql);
        query.setParameter("name", "name%");

        List<Person> results = query.getResultList();
        for (Person p: results) {
            System.out.println("----------------------------------" + p);
        }

        em.close();
    }

    @Test
    void groupByNameAndLogin() {
        EntityManager em = HibernateUtil.getEntityManager();

        String jpql = "SELECT count(p), p.name, p.login FROM Person p GROUP BY p.name, p.login ORDER BY p.login";      //находит количество встречающихся имен и логинов
        Query query = em.createQuery(jpql);

        List<Object[]> results = query.getResultList();
        for (Object[] p: results) {
            System.out.println("----------------------------------" + p[1] + " - name and "+ p[2] + " - login, " + p[0] + " times");
        }

        em.close();
    }

    @Test
    void findPersonByChild() {
        EntityManager em = HibernateUtil.getEntityManager();

        String jpql = "SELECT distinct p.id FROM Person p join p.personChild pc ON pc.name='child3'";      //находит родителей по ребенку
        Query query = em.createQuery(jpql);

        List<Long> results = query.getResultList();
        for (Long p: results) {
            System.out.println("---------------------------------- ID=" + p);
        }
        em.close();
    }

    @Test
    void useTransformer() {
        EntityManager em = HibernateUtil.getEntityManager();
        Session session = em.unwrap(Session.class);
        String jpql = "SELECT p.login as login, p.name as name FROM Person p WHERE p.name=:name";      //трансформирует Person в PersonWrapper
        org.hibernate.query.Query query = session.createSQLQuery(jpql);
        ((NativeQuery) query).addScalar("login", StandardBasicTypes.STRING);
        ((NativeQuery) query).addScalar("name", StandardBasicTypes.STRING);
        query.setParameter("name", "name2");

        query.setResultTransformer(Transformers.aliasToBean(PersonWrapper.class));

        List<PersonWrapper> results = query.list();

        for (PersonWrapper p: results) {
            System.out.println("---------------------------------" + p);
        }
        em.close();
    }

    //АГРЕГАТНЫЕ ФУНКЦИИ
    @Test
    void findMinDate() {
        EntityManager em = HibernateUtil.getEntityManager();

        String jpql = "SELECT min(p.dateOfBirth) FROM Person p";      //находит минимальную дату
        Query query = em.createQuery(jpql);

        LocalDate result = (LocalDate) query.getSingleResult();
        System.out.println("----------------------------------" + result);

        em.close();
    }

    @Test
    void findSumOfAges() {
        EntityManager em = HibernateUtil.getEntityManager();

        String jpql = "SELECT sum(pc.age) FROM PersonChild pc WHERE pc.person.id < 2";      //находит сумму age для определенных person
        Query query = em.createQuery(jpql);

        Long result = (Long) query.getSingleResult();
        System.out.println("----------------------------------" + result);

        em.close();
    }

    @Test
    void findAvgOfAges() {
        EntityManager em = HibernateUtil.getEntityManager();

        String jpql = "SELECT avg(pc.age) FROM PersonChild pc";      //находит седнее age
        Query query = em.createQuery(jpql);

        Double result = (Double) query.getSingleResult();
        System.out.println("----------------------------------" + result);

        em.close();
    }

    //ВСТАВКА И УДАЛЕНИЕ
    @Test
    void insertNewPersonAndDelete() {
        EntityManager em = HibernateUtil.getEntityManager();

        String jpql = "INSERT INTO Person (login, name, dateOfBirth) " +
                        "SELECT login, name, dateOfBirth FROM Person p WHERE p.id=:id";
        Query query = em.createQuery(jpql);
        query.setParameter("id", 5L);

        em.getTransaction().begin();
        int i = query.executeUpdate();
        System.out.println("-----------------------------Inserted persons: " + i);
        em.getTransaction().commit();
        em.clear();

        Person person7 = em.find(Person.class, 7L);
        assertEquals("login5", person7.getLogin());             //Проверка вставки

        jpql = "DELETE FROM Person p WHERE p.id=:id";
        query = em.createQuery(jpql);
        query.setParameter("id", 7L);

        em.getTransaction().begin();
        i = query.executeUpdate();
        System.out.println("-----------------------------Deleted persons: " + i);
        em.getTransaction().commit();
        em.clear();

        Person personDelete = em.find(Person.class, 7L);            //проверка удаления
        assertNull(personDelete);

        em.close();
    }

    @Test
    void updatePerson() {
        EntityManager em = HibernateUtil.getEntityManager();

        String jpql = "UPDATE Person p SET p.name=:NewName WHERE p.id=:id";      //находит минимальную дату
        Query query = em.createQuery(jpql);
        query.setParameter("NewName", "1234567890");
        query.setParameter("id", 6L);

        em.getTransaction().begin();
        int i = query.executeUpdate();
        System.out.println("-----------------------------Updated persons: " + i);
        em.getTransaction().commit();
        em.clear();

        Person personNew = em.find(Person.class, 6L);
        assertEquals("1234567890", personNew.getName());

        em.close();
    }

    //PAGING OUTPUT
    @Test
    void pagingOutput2() {
        EntityManager em = HibernateUtil.getEntityManager();

        String jpql = "SELECT count(p) FROM Person p";              //находит количество записей
        Query query = em.createQuery(jpql);

        Long numberOfPerson = (Long) query.getSingleResult();

        jpql = "FROM Person p";                                  //находит всех
        query = em.createQuery(jpql);

        int maxResult = 2;                                  //Количество записей на странице
        query.setMaxResults(maxResult);

        for (int i = 0; i < numberOfPerson; i += maxResult) {
            query.setFirstResult(i);

            List<Person> results = query.getResultList();
            for (Person p : results) {
                System.out.println("----------------------------------" + p);
            }
            System.out.println("-------------------Next Page");
        }

        em.close();
    }

    @AfterAll
    static void closeEmFactory() {
        HibernateUtil.close();
    }
}
