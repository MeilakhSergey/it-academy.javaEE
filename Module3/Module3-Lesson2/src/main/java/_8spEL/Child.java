package _8spEL;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Child {
    private String name;
    private int age;
    private String line;
    private LocalDate date;
    private int longNumber;

    public int settingAge(int n) {
        System.out.println("Initialization age: " + n);
        return n;
    }

}
