import _5pNames.PName;
import _7null.NullClass;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class _7NullTest {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        NullClass nullClass = (NullClass) context.getBean("null");

        assertNull(nullClass.getName());
    }
}
