import Entities.User;
import Loader.UserExample;
import Util.HibernateUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

public class UserExampleTest {

    @Test
    void addAndFindTest() {
        UserExample.em = HibernateUtil.getEntityManager();
        User user = new User("login1", "password1", "name1", "familyName1", "phone1");
        UserExample.addUser(user);
        User user2 = new User("login2", "password2", "name2", "familyName2", "phone2");
        UserExample.addUser(user2);

        User userFind = UserExample.findUser(user.getLogin());
        assertEquals(userFind, user);

        User user2New = new User("login2", "333", "333", "333", "333");
        UserExample.updateUser(user2New);

        userFind = UserExample.findUser(user2New.getLogin());
        assertEquals(userFind, user2New);

        UserExample.deleteUser(user2);
        userFind = UserExample.findUser(user2New.getLogin());
        assertEquals(null, userFind);
    }


    @AfterAll
    static void clean() {
        HibernateUtil.close();
    }
}
