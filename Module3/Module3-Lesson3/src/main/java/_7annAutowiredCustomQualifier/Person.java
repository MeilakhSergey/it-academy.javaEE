package _7annAutowiredCustomQualifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Data
@NoArgsConstructor @AllArgsConstructor
public class Person {
    private String name;

    @Autowired
    @DoSmth(amount = "two")
    private Doing doing;

}
