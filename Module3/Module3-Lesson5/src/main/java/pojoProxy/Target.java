package pojoProxy;

public class Target {
    public void doSmth() {
        System.out.println("Doing smth in " + this.getClass());
    }
}
