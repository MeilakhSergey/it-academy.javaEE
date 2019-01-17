package _4annAutowired;

import _7annAutowiredCustomQualifier.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class _7annAutowiredCustomQualifierTest {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context_7.xml");
        Person person = (Person) context.getBean("person");

        person.getDoing().execute(person.getName());
    }
}
