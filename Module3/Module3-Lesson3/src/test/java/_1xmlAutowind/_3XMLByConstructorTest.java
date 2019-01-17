package _1xmlAutowind;

import _3xmlAutowindByConstructor.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class _3XMLByConstructorTest {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context_3.xml");
        Person person = (Person) context.getBean("person");

        person.getDoing().execute(person.getName());
    }
}
