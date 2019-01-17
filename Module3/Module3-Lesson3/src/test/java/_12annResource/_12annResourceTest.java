package _12annResource;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class _12annResourceTest {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context_12.xml");
        Person person = (Person) context.getBean("person");

        person.getDoing().execute(person.getName());
    }
}
