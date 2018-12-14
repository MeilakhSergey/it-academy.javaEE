package Loader;

import Entities.User;
import Util.HibernateUtil;

import javax.persistence.EntityManager;

public class UserExample {
    public static EntityManager em;

    public static void main(String[] args) {
        em = HibernateUtil.getEntityManager();

        User user = new User("login1", "password1", "name1", "familyName1", "phone1");
        addUser(user);
        User user2 = new User("login2", "password2", "name2", "familyName2", "phone2");
        addUser(user2);

        findUser("login1");

        User user2New = new User("login2", "333", "333", "333", "333");
        updateUser(user2New);

        User user3 = new User("login3", "password3", "name3", "familyName3", "phone3");
        addUser(user3);
        findUser(user3.getLogin());
        deleteUser(user3);

        HibernateUtil.close();
    }

    public static void addUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public static User findUser(String login) {
        em.getTransaction().begin();
        User user2 = em.find(User.class, login);
        System.out.println(user2);
        em.getTransaction().commit();
        return user2;
    }

    public static void updateUser(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    public static void deleteUser(User user) {
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
    }
}
