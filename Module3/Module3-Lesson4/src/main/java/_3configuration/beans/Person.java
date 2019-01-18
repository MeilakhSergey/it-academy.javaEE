package _3configuration.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

@Data
@NoArgsConstructor @AllArgsConstructor

public class Person {

    @Value("NAME_3")
    private String name;

    @Autowired(required = false)
//    @Qualifier("oneString")
//    @Value("#{personPrint222}")
    private Doing doing;

    public Person(Doing doing) {
        this.doing = doing;
    }
}
