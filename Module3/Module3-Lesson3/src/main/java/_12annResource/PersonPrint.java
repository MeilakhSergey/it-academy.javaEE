package _12annResource;

public class PersonPrint implements Doing {

    @Override
    public void execute(String str) {
        System.out.println(this.getClass().getSimpleName() + " - " + str);
    }
}
