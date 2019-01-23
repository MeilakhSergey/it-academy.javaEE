package declarative.dao;

import declarative.entities.Person;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.sql.SQLException;

@Repository
public class PersonDAO implements DAO<Person> {
    @PersistenceContext
    @Getter
    private EntityManager em;

    @Override
    public Person add(Person person) {
        em.persist(person);

        return person;
    }

    @Override
    public Person update(Person person) {
        throw new ArithmeticException();
//        return null;
    }

    @Override
    public Person get(Serializable id) {
        return em.find(Person.class, id);
    }

    @Override
    public void delete(Serializable id) {
        em.remove(get(id));
    }

    @Override
    public void getExcept(Serializable id) throws SQLException {
        System.out.println("-------------------------" + TransactionSynchronizationManager.isActualTransactionActive());
        em.find(Person.class, id);
        throw new SQLException();
    }
}
