package xmlConfiguration.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor

@Component
public class Person {

    @Value("NAME_3")
    private String name;

    @Autowired(required = false)
    @Qualifier("oneString")
    private Doing doing;

    @Autowired(required = false)
    @Qualifier("twoStrings")
    private Doing doing2;

    public Integer division(int a) {
        return 15 / a;
    }

    public String concating(String str) {
        return str + str;
    }

    public void print(List<String> collection) {
        collection.forEach(System.out::println);
    }
}
