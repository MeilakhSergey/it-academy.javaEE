import _2constructor.Constructor;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class _2ConstructorTest {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Constructor constructor = (Constructor) context.getBean("constructor");

        assertEquals("NAME", constructor.getName());
        assertEquals(10, constructor.getAge());
    }
}
