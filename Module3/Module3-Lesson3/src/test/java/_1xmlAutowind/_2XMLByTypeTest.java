package _1xmlAutowind;

import _2xmlAutowindByType.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class _2XMLByTypeTest {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context_2.xml");
        Person person = (Person) context.getBean("person");

        person.getDoing().execute(person.getName());
    }
}
