import _1factoryMethod.FactoryMethod;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class _1FactoryMethodTest {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        FactoryMethod factoryMethod = (FactoryMethod) context.getBean("factoryMethod");

        assertEquals("NAME", factoryMethod.getName());
    }
}
