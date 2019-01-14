package _1factoryMethod;

public class FactoryMethod {
    private String name;
//    private static FactoryMethod factoryMethod = new FactoryMethod();

    public static FactoryMethod getInstance() {
        return new FactoryMethod("NAME");
    }

    private FactoryMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
