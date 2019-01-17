package _3xmlAutowindByConstructor;

public class PersonPrint implements Doing {

    @Override
    public void execute(String str) {
        System.out.println(this.getClass().getSimpleName() + " - " + str);
    }
}
