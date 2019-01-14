package simpleProject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
@AllArgsConstructor
public class PrintScreen implements IPrint{

    @Override
    public void printMessage(String message) {
        System.out.println("PrintScreen: " + message);
    }
}
