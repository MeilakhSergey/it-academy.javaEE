import dao.DAO;
import entities.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import util.EntityManagerUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/context.xml")
@Transactional
public class ExampleTest {

    @Autowired
    private DAO personDAO;
    @Autowired
    private EntityManagerUtil emUtil;

    @Test
    void pageTest() {
        personDAO.save(new Person(null, "name1", 10));
        personDAO.save(new Person(null, "name2", 12));
        personDAO.save(new Person(null, "name3", 12));
        personDAO.save(new Person(null, "name4", 14));
        personDAO.save(new Person(null, "name5", 16));
        personDAO.save(new Person(null, "name6", 16));
        personDAO.save(new Person(null, "name7", 16));

        emUtil.flush();
        emUtil.clear();

        Example<Person> example = Example.of(new Person(null, null, 12));

        assertEquals(2, personDAO.findAll(example).size());

        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                                                .withMatcher("name", startsWith())
                                                .withIgnoreCase();
        example = Example.of(new Person(null, "name", 0), matcher);

        assertEquals(7, personDAO.findAll(example).size());

    }
}
