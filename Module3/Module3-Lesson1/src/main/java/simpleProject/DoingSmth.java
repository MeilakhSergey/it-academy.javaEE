package simpleProject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoingSmth {
    private IPrint print;

    public void doSmth(String line) {
        print.printMessage(line);
    }
}
