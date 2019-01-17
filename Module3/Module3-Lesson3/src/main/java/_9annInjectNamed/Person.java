package _9annInjectNamed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class Person {
    private String name;

    @Inject
    @Named("personPrint")
    private Doing doing;

}
