package _4defaultInitMethod;

public class DefaultInitMethod {
    private String name;

    public void initDefault() {
        name = "NAME";
    }

    public void destroyDefault() {
        System.out.println("Destroying in DefaultInitMethod");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
