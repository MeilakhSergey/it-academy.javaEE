package _4annAutowiredSimple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

public class PersonPrint implements Doing {

    @Override
    public void execute(String str) {
        System.out.println(this.getClass().getSimpleName() + " - " + str);
    }
}
