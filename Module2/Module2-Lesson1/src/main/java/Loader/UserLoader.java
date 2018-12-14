package Loader;

import Entities.User;
import Util.HibernateUtil;

import javax.persistence.EntityManager;

public class UserLoader {
    public static void main(String[] args) {
        User user = new User("login1", "password1", "name1", "familyName1", "phone1");
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        HibernateUtil.close();
    }
}
