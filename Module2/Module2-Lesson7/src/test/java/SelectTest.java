import Util.HibernateUtil;
import entities.Person;
import entities.PersonChild;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
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
        String name = "name1";

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
        Root<Person> root = criteria.from(Person.class);

        criteria.select(root).where(builder.equal(root.get("name"), name));

        List<Person> result = em.createQuery(criteria).getResultList();

        assertEquals(1, result.size());
    }

    @Test
    void findByNameAndAge() {
        EntityManager em = HibernateUtil.getEntityManager();
        String name = "name%";

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
        Root<Person> root = criteria.from(Person.class);

        Predicate predicate = builder.and(
                                builder.like(root.get("name"), name),
                                builder.greaterThan(builder.currentDate(), root.get("dateOfBirth"))
        );

        criteria.select(root).where(predicate);

        List<Person> result = em.createQuery(criteria).getResultList();

        assertEquals(4, result.size());

        em.close();
    }

    @Test
    void groupByNameAndLogin() {
        EntityManager em = HibernateUtil.getEntityManager();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
        Root<Person> root = criteria.from(Person.class);

        criteria.groupBy(root.get("name"), root.get("login"));
        criteria.select(root.get("name"));

        List<Person> result = em.createQuery(criteria).getResultList();

        assertEquals(5, result.size());

        em.close();
    }

    @Test
    void findPersonByChild() {
        EntityManager em = HibernateUtil.getEntityManager();
        String name = "child3";

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
        Root<Person> root = criteria.from(Person.class);
        Join<Person, PersonChild> join = root.join("personChild", JoinType.INNER);

        criteria.select(root).where(builder.equal(join.get("name"), name));

        List<Person> result = em.createQuery(criteria).getResultList();

        assertEquals(2, result.size());

        em.close();
    }

    //АГРЕГАТНЫЕ ФУНКЦИИ
    @Test
    void findMinDate() {
        EntityManager em = HibernateUtil.getEntityManager();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<LocalDate> criteria = builder.createQuery(LocalDate.class);
        Root<Person> root = criteria.from(Person.class);

        criteria.select(builder.min(root.get("dateOfBirth")).as(LocalDate.class));

        LocalDate result = em.createQuery(criteria).getSingleResult();

        assertEquals(LocalDate.of(1991, 4, 4), result);

        em.close();
    }

    @Test
    void findSumOfAges() {
        EntityManager em = HibernateUtil.getEntityManager();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<PersonChild> root = criteria.from(PersonChild.class);

        criteria.select(builder.sum(root.get("age")));

        Number result = em.createQuery(criteria).getSingleResult();

        assertEquals(191, result);

        em.close();
    }

    @Test
    void findAvgOfAges() {
        EntityManager em = HibernateUtil.getEntityManager();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Double> criteria = builder.createQuery(Double.class);
        Root<PersonChild> root = criteria.from(PersonChild.class);

        criteria.select(builder.avg(root.get("age")));

        Number result = em.createQuery(criteria).getSingleResult();

        assertEquals(38.2, result);

        em.close();
    }

    //PAGING OUTPUT
    @Test
    void pagingOutput2() {
        EntityManager em = HibernateUtil.getEntityManager();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaLong = builder.createQuery(Long.class);
        Root<Person> root = criteriaLong.from(Person.class);

        criteriaLong.select(builder.count(root));

        Number numberOfPerson = em.createQuery(criteriaLong).getSingleResult();     //находит количество записей


        CriteriaQuery<Person> criteriaPerson = builder.createQuery(Person.class);
        criteriaPerson.from(Person.class);

        TypedQuery<Person> typedQuery = em.createQuery(criteriaPerson);

        int maxResult = 2;                                  //Количество записей на странице
        typedQuery.setMaxResults(maxResult);

        for (int i = 0; i < numberOfPerson.intValue(); i += maxResult) {
            typedQuery.setFirstResult(i);

            List<Person> results = typedQuery.getResultList();
            for (Person p : results) {
                System.out.println("----------------------------------" + p);
            }
            System.out.println("-------------------Next Page");
        }

        em.close();
    }

    //SORTING
    @Test
    void testSorting() {
        EntityManager em = HibernateUtil.getEntityManager();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
        Root<Person> root = criteria.from(Person.class);

        criteria.select(root).orderBy(
                builder.asc(root.get("login")),
                builder.desc(root.get("name"))
        );

        List<Person> result = em.createQuery(criteria).getResultList();

        assertEquals("name4", result.get(1).getName());

        em.close();
    }

    @AfterAll
    static void closeEmFactory() {
        HibernateUtil.close();
    }
}
