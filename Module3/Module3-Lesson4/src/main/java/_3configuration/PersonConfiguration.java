package _3configuration;

import _3configuration.beans.Doing;
import _3configuration.beans.Person;
import _3configuration.beans.PersonPrint;
import _3configuration.beans.PersonPrint222;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "_3configuration.beans"

)
public class PersonConfiguration {

    @Bean(value = "person")
    public Person person() {
        return new Person();
    }

    @Bean(value = "personPrint")
//    @Qualifier("oneString")
//    @Primary
    public PersonPrint personPrint() {
        return new PersonPrint();
    }

    @Bean(value = "personPrint222")
//    @Qualifier("twoStrings")
    public PersonPrint222 personPrint222() {
        return new PersonPrint222();
    }
}
