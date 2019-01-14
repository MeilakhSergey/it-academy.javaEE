import _7null.NullClass;
import _8spEL.Child;
import _8spEL.Collect;
import _8spEL.Parent;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNull;

public class _8SpELTest {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Parent parent = (Parent) context.getBean("parent");
        Child child = (Child) context.getBean("child");
        Collect collect = (Collect) context.getBean("collect");

        System.out.println(parent);
        System.out.println(child);
        System.out.println(collect);
    }
}
