import _3initMethod.InitMethod;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class _3InitMethodTest {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        InitMethod initMethod = (InitMethod) context.getBean("initMethod");

        assertEquals("NAME", initMethod.getName());

        ((ClassPathXmlApplicationContext) context).close();
    }
}
