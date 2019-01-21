package annConfiguration.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Aspect123 {

    @Pointcut("execution(* annConfiguration.beans.*.*(..))")
    public void all(){}

    @Pointcut("execution(* annConfiguration.beans.Person.division(..))")
    public void division() {}

    @Before("all()")
    public void before() {
        System.out.println("BEFORE");
    }

    @AfterReturning(value = "division()",
                returning = "refValue")
    public void afterReturn(Integer refValue) {
        System.out.println("Should return: " + refValue);
        refValue += 1000000;
    }

    @AfterThrowing(value = "division()")
    public void mistake() {
        System.out.println("There was an exception");
    }

    @After(value = "division()")
    public void after() {
        System.out.println("It should be written in any case");
    }

    @Around(value = "args(str)")                                                //Changing input and return value
    public String around(ProceedingJoinPoint pjp, String str) throws Throwable {
        str = "QWE";
        String refValue = (String) pjp.proceed(new Object[]{str});
        return "+++" + refValue + "+++";
    }

    @After(value = "args(java.util.Collection)")                                    //Checking Person.print()
//    @After(value = "execution(* *(java.util.Collection))")
    public void afterCollection() {
        System.out.println("It was collection");
    }

    @After(value = "within(annConfiguration.beans.hierarchy.Animal)")           //Checking within-target
//    @After(value = "within(annConfiguration.beans.hierarchy.Dog)")
    public void hierarchy1() {
        System.out.println("Within");
    }

    @After(value = "target(annConfiguration.beans.hierarchy.Animal)")
//    @After(value = "target(annConfiguration.beans.hierarchy.Dog)")
    public void hierarchy2() {
        System.out.println("Target");
    }
}
