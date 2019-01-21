import annConfiguration.Configuration;
import annConfiguration.beans.Person;
import annConfiguration.beans.hierarchy.Animal;
import annConfiguration.beans.hierarchy.Dog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Configuration.class})
public class AnnConfigurationTest {
    @Autowired
    Person person;
    @Autowired
    Animal animal;
    @Autowired
    Dog dog;

    @Test
    void test() {

        person.getDoing().execute(person.getName());

        System.out.println("-----------------------");

        person.getDoing2().execute(person.getName());

        System.out.println("-----------------------");

    }

    @Test
    void exceptionMethod() {
        System.out.println("-----------------------");
        System.out.println(person.division(1));

        System.out.println("-----------------------");
        assertThrows(ArithmeticException.class, () -> person.division(0));

    }

    @Test
    void concating() {
        System.out.println(person.concating("123"));
    }

    @Test
    void print() {
        List<String> col = new ArrayList<>();
        col.add("111");
        col.add("222");
        col.add("333");
        person.print(col);
    }

    @Test
    void animal() {
        System.out.println("--------------");
        animal.print1();
        System.out.println("--------------");
        dog.print2();
        System.out.println("--------------");
    }
}
