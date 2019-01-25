import dao.DAO;
import entities.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import util.EntityManagerUtil;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/context.xml")
@Transactional
public class JpaTest {

    @Autowired
    private DAO personDAO;
    @Autowired
    private EntityManagerUtil emUtil;

    @Test
    public void addTest() {
        Person person = new Person();
        person.setName("name1");
        person.setAge(11);

        personDAO.saveAndFlush(person);

        emUtil.clear();
        System.out.println("---------------------------");

        assertEquals(person,personDAO.getOne(person.getId()));
        System.out.println("---------------------------");

        List<Person> list = new ArrayList<>();
        list.add(person);
        personDAO.deleteInBatch(list);
        emUtil.flush();
        emUtil.clear();
        System.out.println("---------------------------");

        assertFalse(personDAO.existsById(person.getId()));

        Person pp = personDAO.getOne(person.getId());
        System.out.println(pp.getName());
        assertThrows(EntityNotFoundException.class, () -> personDAO.getOne(1L));        //????????????????
        System.out.println("---------------------------");

    }


}
