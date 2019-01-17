package _8Inject;

import _10annInjectCustomQualifier.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class _10annInjectCustomQualifierTest {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context_10.xml");
        Person person = (Person) context.getBean("person");

        person.getDoing().execute(person.getName());
    }
}
