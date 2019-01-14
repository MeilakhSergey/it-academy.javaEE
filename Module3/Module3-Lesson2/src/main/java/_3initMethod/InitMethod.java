package _3initMethod;

public class InitMethod {
    private String name;

    public void init() {
        name = "NAME";
    }

    public void destroy() {
        System.out.println("Destroying in InitMethod");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
