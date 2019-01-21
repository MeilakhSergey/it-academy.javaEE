package pojoProxy;


import org.springframework.aop.framework.ProxyFactory;

public class Main {
    private static Target target;
    private static Target proxy;

   static {
       target = new Target();
       ProxyFactory factory = new ProxyFactory();
       factory.addAdvice(new Aspect());
       factory.setTarget(target);
       proxy = (Target) factory.getProxy();
   }

    public static void main(String[] args) {
        target.doSmth();
        System.out.println("-------------------");
        proxy.doSmth();
    }
}
