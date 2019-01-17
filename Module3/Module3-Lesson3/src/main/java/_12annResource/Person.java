package _12annResource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

@Data
@NoArgsConstructor @AllArgsConstructor
public class Person {
    private String name;

    @Resource//(name = "oneString")
    private Doing doing;

}
