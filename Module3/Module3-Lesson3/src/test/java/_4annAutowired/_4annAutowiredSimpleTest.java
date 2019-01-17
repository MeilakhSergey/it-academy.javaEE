package _4annAutowired;

import _4annAutowiredSimple.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class _4annAutowiredSimpleTest {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context_4.xml");
        Person person = (Person) context.getBean("person");

        person.getDoing().execute(person.getName());
    }
}
