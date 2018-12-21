package Entities.TablePerClass;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "TablePerSubclass2_Child2")
@DiscriminatorValue("Child2")
public class Child2 extends Item {
    @Column
    private String childName2;
    @Column
    private String childFamilyName2;
    @Column
    private String childPhone2;

    public Child2(Long id, String itemName, String itemNameColumn, String childName2, String childFamilyName2, String childPhone2) {
        super(id, itemName, itemNameColumn);
        this.childName2 = childName2;
        this.childFamilyName2 = childFamilyName2;
        this.childPhone2 = childPhone2;
    }

    @Override
    public String toString() {
        return "Child2{" +
                "childName2='" + childName2 + '\'' +
                ", childFamilyName2='" + childFamilyName2 + '\'' +
                ", childPhone2='" + childPhone2 + '\'' +
                "} " + super.toString();
    }
}
