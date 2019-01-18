import _2componentFilters.Person;
import _2componentFilters.PersonPrint;
import _2componentFilters.PersonPrint222;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class _2ComponentFilterTest {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context_2.xml");
//        ApplicationContext context = new AnnotationConfigApplicationContext(Person.class, PersonPrint.class, PersonPrint222.class);
                Person person = (Person) context.getBean("person");

        person.getDoing().execute(person.getName());
    }
}
