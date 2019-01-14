package _8spEL;

import lombok.Data;

import java.util.Collection;
import java.util.Map;

@Data
public class Collect {
    private Collection list;
    private Collection set;
    private String element;

    @Override
    public String toString() {
        return "Collect{" +
                "list(" + list.getClass().getSimpleName() + ")=" + list +
                ", set(" + set.getClass().getSimpleName() + ")=" + set +
                ", element='" + element + '\'' +
                '}';
    }
}
