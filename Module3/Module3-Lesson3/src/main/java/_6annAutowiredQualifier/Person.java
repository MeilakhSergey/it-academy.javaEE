package _6annAutowiredQualifier;

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
    @Qualifier("oneString")
    private Doing doing;

}
