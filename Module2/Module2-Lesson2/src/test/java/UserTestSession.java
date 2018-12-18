import Entities.User;
import Util.SessionFactoryUtilTest;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;


import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTestSession {                                  //Тестирование с использованием SessionFactory
//    public static SessionFactoryUtil factory;                 //задание свойств SessionFactory в классе
    public static SessionFactoryUtilTest factory;               //задание свойств SessionFactory в файле 123hibernate.cfg.xml
    public static Session session;

    @AfterEach
    public void init() {
        session = factory.getSession();

        session.getTransaction().begin();
        session.createQuery("DELETE FROM User u").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testAddFind() {
        session = factory.getSession();

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
        session = factory.getSession();

        session.getTransaction().begin();
        User userFind = session.get(User.class, "login1");
        session.getTransaction().commit();
        assertNull(userFind);

        session.getTransaction().begin();
        User userLoad = session.load(User.class, "login1");
        session.getTransaction().commit();
        assertThrows(ObjectNotFoundException.class, () -> userLoad.getName());
    }

    /*@Test
    public void testFlushWithoutTransaction() {
        session = factory.getSession();

        User user = new User("login1", "password1", "name1", "familyName1", "111");
        session.getTransaction().begin();
        session.save(user);
        session.getTransaction().commit();
        session.clear();

        user = session.get(User.class, "login1");
        System.out.println(user);
        user.setName("12312312");
        System.out.println(user);
        session.flush();
        System.out.println(user);
    }*/

    @AfterAll
    public static void close() {
        factory.closeSessionFactory();
    }
}
