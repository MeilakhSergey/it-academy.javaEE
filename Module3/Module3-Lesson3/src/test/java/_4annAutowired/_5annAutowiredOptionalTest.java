package _4annAutowired;

import _5annAutowiredOptional.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNull;

public class _5annAutowiredOptionalTest {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context_5.xml");
        Person person = (Person) context.getBean("person");

        assertNull(person.getDoing());
    }
}
