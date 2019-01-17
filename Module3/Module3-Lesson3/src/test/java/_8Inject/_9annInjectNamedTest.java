package _8Inject;

import _9annInjectNamed.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class _9annInjectNamedTest {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context_9.xml");
        Person person = (Person) context.getBean("person");

        person.getDoing().execute(person.getName());
    }
}
