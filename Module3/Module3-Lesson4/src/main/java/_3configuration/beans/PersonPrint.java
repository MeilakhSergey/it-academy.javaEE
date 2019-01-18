package _3configuration.beans;

import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("oneString")
public class PersonPrint implements Doing {

    @Override
    public void execute(String str) {
        System.out.println(this.getClass().getSimpleName() + " - " + str);
    }
}
