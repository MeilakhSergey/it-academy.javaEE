package _4annAutowired;

import _6annAutowiredQualifier.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNull;

public class _6annAutowiredQualifierTest {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context_6.xml");
        Person person = (Person) context.getBean("person");

        person.getDoing().execute(person.getName());
    }
}
