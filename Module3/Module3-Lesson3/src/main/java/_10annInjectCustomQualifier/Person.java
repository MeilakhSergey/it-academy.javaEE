package _10annInjectCustomQualifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor @AllArgsConstructor
public class Person {
    private String name;

    @Autowired
    @DoSmth(amount = "two")
    private Doing doing;

}
