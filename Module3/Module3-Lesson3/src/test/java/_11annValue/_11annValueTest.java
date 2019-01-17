package _11annValue;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class _11annValueTest {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context_11.xml");
        Person person = (Person) context.getBean("person");

        person.getDoing().execute(person.getName());
    }
}
