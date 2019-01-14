import _6Collections.Collectionsss;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class _6Collections {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Collectionsss col = (Collectionsss) context.getBean("collections");

        System.out.println(col.getList().getClass().getSimpleName() + col.getList().toString());
        System.out.println(col.getSet().getClass().getSimpleName() + col.getSet().toString());
        System.out.println(col.getMap().getClass().getSimpleName() + col.getMap().toString());
        System.out.println(col.getProperties().getClass().getSimpleName() + col.getProperties().toString());

        assertEquals(3, col.getList().size());
        assertEquals(2, col.getSet().size());
        assertEquals(2, col.getMap().size());
        assertEquals(2, col.getProperties().size());
    }
}
