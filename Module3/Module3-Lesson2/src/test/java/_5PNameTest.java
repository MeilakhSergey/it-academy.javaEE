import _5pNames.PName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class _5PNameTest {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        PName pName = (PName) context.getBean("pName");

        assertEquals("NAME", pName.getName());
        assertEquals(10, pName.getAge());
    }
}
