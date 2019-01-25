import dao.DAO;
import entities.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import util.EntityManagerUtil;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/context.xml")
@Transactional
public class PageTest {

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

        assertEquals("name6", personDAO.findByAge(16, PageRequest.of(0, 2, Sort.Direction.DESC, "name")).get(1).getName());

        assertEquals("name7", personDAO.findByAge(16, PageRequest.of(1, 2)).get(0).getName());

    }
}
