import dao.Child;
import dao.DAO;
import dao.Parent;
import entities.OneToOneChild;
import entities.OneToOneParent;
import entities.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import util.EntityManagerUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/context.xml")
@Transactional
public class QueryTest {
    @Autowired
    private DAO personDAO;
    @Autowired
    private Parent parent;
    @Autowired
    private Child child;
    @Autowired
    private EntityManagerUtil emUtil;

    @Test
    void test() {
        personDAO.save(new Person(null, "name1", 10));
        personDAO.save(new Person(null, "name2", 12));
        personDAO.save(new Person(null, "name3", 12));
        personDAO.save(new Person(null, "name4", 14));

        emUtil.flush();
        emUtil.clear();

        assertEquals(1,personDAO.getByName("name1").size());
    }

    @Test
    void oneToOneTest() {
        OneToOneParent p1 = new OneToOneParent(null, "parent1", null);
        OneToOneParent p2 = new OneToOneParent(null, "parent2", null);
        OneToOneChild c1 = new OneToOneChild(null, "child1", null);
        OneToOneChild c2 = new OneToOneChild(null, "child2", null);

        p1.setChild(c1);
        c1.setParent(p1);
        p2.setChild(c2);
        c2.setParent(p2);

        parent.save(p1);
        parent.save(p2);
//        child.save(c1);
//        child.save(c2);

        emUtil.flush();
        emUtil.clear();

        assertEquals("child1", parent.getChild("parent1"));

        assertEquals("child2", parent.getNames().get(1));
    }
}
