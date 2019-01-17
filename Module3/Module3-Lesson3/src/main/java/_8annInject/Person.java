package _8annInject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.inject.Inject;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class Person {
    private String name;

    @Inject
    private List<Doing> doing;

}
