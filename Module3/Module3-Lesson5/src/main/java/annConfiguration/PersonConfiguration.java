package annConfiguration;

import annConfiguration.beans.Person;
import annConfiguration.beans.PersonPrint;
import annConfiguration.beans.PersonPrint222;
import annConfiguration.beans.hierarchy.Animal;
import annConfiguration.beans.hierarchy.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"annConfiguration.beans", "annConfiguration.aspects"}

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

    @Bean(value = "animal")
    public Animal animal() {
        return new Animal();
    }

    @Bean(value = "dog")
    public Dog dog() {
        return new Dog();
    }
}
