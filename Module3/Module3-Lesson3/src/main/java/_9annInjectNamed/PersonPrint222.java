package _9annInjectNamed;

public class PersonPrint222 implements Doing {

    @Override
    public void execute(String str) {
        System.out.println(this.getClass().getSimpleName() + " - " + str);
        System.out.println(this.getClass().getSimpleName() + " - " + str);
    }
}
