package Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static final EntityManagerFactory emFactory;
    static {
        //emFactory = Persistence.createEntityManagerFactory("MySQL");          //использование MySQL
        emFactory = Persistence.createEntityManagerFactory("test");                     //использование h2
    }

    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    public static EntityManagerFactory getEmFactory() {return emFactory;}

    public static void close() {
        emFactory.close();
    }
}
