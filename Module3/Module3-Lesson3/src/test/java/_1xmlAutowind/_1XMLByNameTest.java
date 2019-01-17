package _1xmlAutowind;

import _1xmlAutowindByName.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class _1XMLByNameTest {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context_1.xml");
        Person person = (Person) context.getBean("person");

        person.getDoing().execute(person.getName());
    }
}
