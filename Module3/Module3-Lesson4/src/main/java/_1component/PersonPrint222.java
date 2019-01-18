package _1component;

import org.springframework.stereotype.Component;

@Component("doing2")
public class PersonPrint222 implements Doing {

    @Override
    public void execute(String str) {
        System.out.println(this.getClass().getSimpleName() + " - " + str);
        System.out.println(this.getClass().getSimpleName() + " - " + str);
    }
}
