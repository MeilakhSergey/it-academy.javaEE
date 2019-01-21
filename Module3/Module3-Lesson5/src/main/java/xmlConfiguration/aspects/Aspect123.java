package xmlConfiguration.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Aspect123 {

    public void before() {
        System.out.println("BEFORE");
    }

    public void afterReturn(Integer refValue) {
        System.out.println("Should return: " + refValue);
        refValue += 1000000;
    }

    public void mistake() {
        System.out.println("There was an exception");
    }

    public void after() {
        System.out.println("It should be written in any case");
    }

    public String around(ProceedingJoinPoint pjp, String str) throws Throwable {
        str = "QWE";
        String refValue = (String) pjp.proceed(new Object[]{str});
        return "+++" + refValue + "+++";
    }

    public void afterCollection() {
        System.out.println("It was collection");
    }

    public void hierarchy1() {
        System.out.println("Within");
    }

    public void hierarchy2() {
        System.out.println("Target");
    }
}
