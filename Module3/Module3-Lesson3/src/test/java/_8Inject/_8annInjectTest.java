package _8Inject;

import _8annInject.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class _8annInjectTest {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context_8.xml");
        Person person = (Person) context.getBean("person");

        assertEquals(2, person.getDoing().size());
    }
}
