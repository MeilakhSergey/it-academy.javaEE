import declarative.dao.DAO;
import declarative.entities.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:context_declarative.xml")

public class DeclarativeTest {

    @Autowired
    private DAO personDAO;

    @Test
    public void addTest() {
        Person person = new Person();
        person.setName("name1");
        person.setAge(11);

        personDAO.add(person);

        assertEquals(person,personDAO.get(person.getId()));

        assertThrows(IllegalArgumentException.class, () -> personDAO.delete(3L));
    }

    @Test                   //Checked exception
    public void except() {
        System.out.println(TransactionSynchronizationManager.isActualTransactionActive());

        assertThrows(IllegalArgumentException.class, () -> personDAO.delete(3L));

        System.out.println("-------------------------" + TransactionSynchronizationManager.isActualTransactionActive());

        Person person = new Person();
        person.setName("name1");
        person.setAge(11);
        personDAO.add(person);
        assertThrows(SQLException.class, () -> personDAO.getExcept(1L));

        System.out.println("-------------------------" + TransactionSynchronizationManager.isActualTransactionActive());
    }
}
