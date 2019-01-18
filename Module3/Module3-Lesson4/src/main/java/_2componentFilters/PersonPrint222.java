package _2componentFilters;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("personPrint222")
@Qualifier("twoStrings")
public class PersonPrint222 implements Doing {

    @Override
    public void execute(String str) {
        System.out.println(this.getClass().getSimpleName() + " - " + str);
        System.out.println(this.getClass().getSimpleName() + " - " + str);
    }
}
