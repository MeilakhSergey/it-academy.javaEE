package Entities.TablePerClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "TablePerClass_Item")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "ITEM_TYPE")
@DiscriminatorValue("Item")
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String itemName;
    @Column
    private String itemNameColumn;
}
