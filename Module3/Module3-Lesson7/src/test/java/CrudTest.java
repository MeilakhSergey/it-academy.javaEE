import dao.DAO;
import entities.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import util.EntityManagerUtil;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/context.xml")
@Transactional
public class CrudTest {

    @Autowired
    private DAO personDAO;
    @Autowired
    private EntityManagerUtil emUtil;

    @Test
    public void addTest() {
        Person person = new Person();
        person.setName("name1");
        person.setAge(11);

        personDAO.save(person);

        emUtil.clear();
        System.out.println("---------------------------");

        assertEquals(person,personDAO.findById(person.getId()).get());
        System.out.println("---------------------------");

        personDAO.deleteById(person.getId());
        emUtil.flush();
        emUtil.clear();
        System.out.println("---------------------------");

//        assertFalse(personDAO.existsById(person.getId()));

        assertFalse(personDAO.findById(person.getId()).isPresent());
        System.out.println("---------------------------");

        assertThrows(EmptyResultDataAccessException.class, () -> personDAO.deleteById(3L));
    }

    @Test
    void testFindBy() {
        personDAO.save(new Person(null, "name1", 10));
        personDAO.save(new Person(null, "name2", 12));
        personDAO.save(new Person(null, "name3", 12));
        personDAO.save(new Person(null, "name4", 14));

        emUtil.flush();
        emUtil.clear();

        assertEquals(3, personDAO.findByAgeLessThan(13).size());

        assertEquals(4, personDAO.findByNameLike("name%").size());

        assertEquals("name4", personDAO.findByAge(14).getName());

        assertEquals(14, personDAO.findByAgeAfter(13).get().getAge());
    }
}
