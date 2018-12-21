package Entities.TablePerSubclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "TablePerSubclass_Item")
@Inheritance(strategy = InheritanceType.JOINED)
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
