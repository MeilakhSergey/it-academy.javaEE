package Entities.MappedSuperClass;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "MappedSuperClass_Child1")
public class Child1 extends Item {
    @Column
    private String childName1;
    @Column
    private String childFamilyName1;
    @Column
    private String childPhone1;

    public Child1(Long id, String itemName, String itemNameColumn, String childName1, String childFamilyName1, String childPhone1) {
        super(id, itemName, itemNameColumn);
        this.childName1 = childName1;
        this.childFamilyName1 = childFamilyName1;
        this.childPhone1 = childPhone1;
    }

    @Override
    public String toString() {
        return "Child1{" +
                "childName1='" + childName1 + '\'' +
                ", childFamilyName1='" + childFamilyName1 + '\'' +
                ", childPhone1='" + childPhone1 + '\'' +
                "} " + super.toString();
    }
}
