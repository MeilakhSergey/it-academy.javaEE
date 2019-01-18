import _3configuration.Configuration;
import _3configuration.PersonConfiguration;
import _3configuration.beans.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Configuration.class})
public class _3ConfigurationTest {
    @Autowired
    Person person;

    @Test
    void test() {
//        ApplicationContext context = new ClassPathXmlApplicationContext("context_2.xml");
//        ApplicationContext context = new AnnotationConfigApplicationContext(PersonConfiguration.class);
//                Person person = (Person) context.getBean("person");

        person.getDoing().execute(person.getName());
    }
}
