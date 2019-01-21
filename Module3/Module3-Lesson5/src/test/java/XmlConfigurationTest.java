import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xmlConfiguration.beans.Person;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class XmlConfigurationTest {
    @Autowired
    static Person person;

    static {
        ApplicationContext context = new ClassPathXmlApplicationContext("context_1.xml");
        person = (Person) context.getBean("person");
    }

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
}
