package _3configuration.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;

//@Primary
@Qualifier("twoStrings")
public class PersonPrint222 implements Doing {

    @Override
    public void execute(String str) {
        System.out.println(this.getClass().getSimpleName() + " - " + str);
        System.out.println(this.getClass().getSimpleName() + " - " + str);
    }
}
