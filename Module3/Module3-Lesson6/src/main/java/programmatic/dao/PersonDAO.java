package programmatic.dao;

import programmatic.entities.Person;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Repository
public class PersonDAO implements DAO<Person> {
    @PersistenceContext
    @Getter
    private EntityManager em;

    @Autowired
    TransactionTemplate transactionTemplate;

    @Override
    public Person add(Person person) {
        return transactionTemplate.execute(new TransactionCallback<Person>() {
            @Override
            public Person doInTransaction(TransactionStatus transactionStatus) {
                try {
                    em.persist(person);
                    return person;
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                }
                return null;
            }
        });
    }

    @Override
    public Person update(Person person) {
        throw new ArithmeticException();
//        return null;
    }

    @Override
    public Person get(Serializable id) {
        return transactionTemplate.execute(new TransactionCallback<Person>() {
            @Override
            public Person doInTransaction(TransactionStatus transactionStatus) {
                try {
                    return em.find(Person.class, id);
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                }
                return null;
            }
        });
    }

    @Override
    public void delete(Serializable id) {
        em.remove(get(id));
    }


}
