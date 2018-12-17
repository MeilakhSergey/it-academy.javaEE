import Entities.User;
import Util.HibernateUtil;
import Util.HibernateUtilTest;
import Util.SessionFactoryUtil;
import Util.SessionFactoryUtilTest;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTestSession {
    public static Session session;

    @BeforeEach
    public void init() {
        session = SessionFactoryUtil.getSession();

        session.getTransaction().begin();
        session.createQuery("DELETE FROM User u").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testAddFind() {
        session = SessionFactoryUtil.getSession();

        User user = new User("login1", "password1", "name1", "familyName1", "111");
        session.getTransaction().begin();
        session.save(user);
        session.getTransaction().commit();
        session.clear();

        session.getTransaction().begin();
        User userFind = session.get(User.class, "login1");
        session.getTransaction().commit();
        assertEquals(userFind, user);
    }

    @Test
    public void testFindAndLoad() {
        session = SessionFactoryUtil.getSession();

        session.getTransaction().begin();
        User userFind = session.get(User.class, "login1");
        session.getTransaction().commit();
        assertNull(userFind);

        session.getTransaction().begin();
        User userLoad = session.load(User.class, "login1");
        session.getTransaction().commit();
        assertThrows(ObjectNotFoundException.class, () -> userLoad.getName());
    }

    @AfterAll
    public static void close() {
        HibernateUtil.close();
    }
}
