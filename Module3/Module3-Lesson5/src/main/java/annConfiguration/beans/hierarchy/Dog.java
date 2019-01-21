package annConfiguration.beans.hierarchy;

import org.springframework.stereotype.Component;

@Component
public class Dog extends Animal{
    public void print2() {
        System.out.println("Dog");
    }
}
