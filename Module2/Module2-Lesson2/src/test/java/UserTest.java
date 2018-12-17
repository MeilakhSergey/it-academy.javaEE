import Entities.User;
import Util.HibernateUtil;
import Util.HibernateUtilTest;
import org.hibernate.FlushMode;
import org.hibernate.LazyInitializationException;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

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
        User user = new User("login1", "password1", "name1", "familyName1", "111");
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        User userFind = em.find(User.class, "login1");
        em.getTransaction().commit();
        assertEquals(userFind, user);
    }

    @Test
    public void testFindAndLoad() {
        em = HibernateUtil.getEntityManager();

        em.getTransaction().begin();
        User userFind = em.find(User.class, "login1");
        em.getTransaction().commit();
        assertNull(userFind);

        em.getTransaction().begin();
        User userLoad = em.getReference(User.class, "login1");
        em.getTransaction().commit();
        assertThrows(EntityNotFoundException.class, () -> userLoad.getName());
    }

    @Test
    public void testFlushTransaction() {
        em = HibernateUtil.getEntityManager();
        User user = new User("login1", "password1", "name1", "familyName1", "111");
        em.getTransaction().begin();
        em.persist(user);
        user.setName("123123");
//        em.flush();
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        User userFind = em.find(User.class, "login1");
        em.getTransaction().commit();
        assertEquals(userFind.getName(), user.getName());
    }

    @Test
    public void testFlushWithoutTransaction() {
        em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        User user = new User("login1", "password1", "name1", "familyName1", "111");
        em.persist(user);
        user.setName("123123");
        Session session = em.unwrap( Session.class);
        session.setHibernateFlushMode( FlushMode.MANUAL );
        em.flush();
//        em.clear();

        User userFind = em.find(User.class, "login1");
        em.getTransaction().commit();
        assertEquals(userFind.getName(), user.getName());
    }

    @AfterAll
    public static void close() {
        HibernateUtil.close();
    }
}
