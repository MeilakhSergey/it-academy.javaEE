import programmatic.dao.DAO;
import programmatic.entities.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.TransactionRequiredException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:context_programmatic.xml")

public class ProgrammaticTest {

    @Autowired
    private DAO personDAO;

    @Test
    public void addTest() {
        Person person = new Person();
        person.setName("name1");
        person.setAge(11);

        personDAO.add(person);

        assertEquals(person,personDAO.get(person.getId()));

        assertThrows(TransactionRequiredException.class, () -> personDAO.delete(3L));
    }

}
