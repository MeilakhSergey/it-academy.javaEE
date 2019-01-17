package _3xmlAutowindByConstructor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class Person {
    private String name;
    private Doing doing;

    public Person(Doing doing) {
        this.doing = doing;
    }
}
