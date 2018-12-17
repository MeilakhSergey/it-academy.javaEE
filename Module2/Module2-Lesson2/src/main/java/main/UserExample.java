package main;

import Entities.User;
import Util.HibernateUtil;

import javax.persistence.EntityManager;

public class UserExample {
    public static EntityManager em;

    public static void main(String[] args) {
        em = HibernateUtil.getEntityManager();

        /*User user = new User("login1", "password1", "name1", "familyName1", "phone1");
        addUser(user);
        User user2 = new User("login2", "password2", "name2", "familyName2", "phone2");
        addUser(user2);*/

        User user = findUser("login1");
        try {
            System.out.println("Find: " + user.getName());
        } catch (Exception e) {
            System.out.println("Method find crushed");
        }

        User user2 = loadUser("login2");
        try {
            System.out.println("Load: " + user2.getName());
        } catch (Exception e) {
            System.out.println("Method getReference crushed");
        }

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

    public static User loadUser(String login) {
        em.getTransaction().begin();
        User user2 = em.getReference(User.class, login);
        em.getTransaction().commit();
        return user2;
    }
}
