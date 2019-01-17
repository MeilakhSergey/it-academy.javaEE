package _11annValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

@Data
@NoArgsConstructor @AllArgsConstructor
public class Person {
    @Value("#{'NAME' + T(Math).random()}")
    private String name;

    @Value("#{twoStrings}")
    private Doing doing;

}
