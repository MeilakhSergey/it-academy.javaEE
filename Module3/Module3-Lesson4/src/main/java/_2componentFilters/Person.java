package _2componentFilters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Data
@NoArgsConstructor @AllArgsConstructor

@Component("person")
public class Person {
    @Value("NAME_2")
    private String name;
    @Autowired
//    @Qualifier("twoStrings")
    private Doing doing;
}
