import _3initMethod.InitMethod;
import _4defaultInitMethod.DefaultInitMethod;
import _4defaultInitMethod.NoDefaultInitMethod;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class _4DefaultInitMethodTest {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        DefaultInitMethod defaultInitMethod = (DefaultInitMethod) context.getBean("defaultInitMethod");

        assertEquals("NAME", defaultInitMethod.getName());

        NoDefaultInitMethod noDefaultInitMethod = (NoDefaultInitMethod) context.getBean("noDefaultInitMethod");
        assertNull(noDefaultInitMethod.getName());

        ((ClassPathXmlApplicationContext) context).close();
    }
}
