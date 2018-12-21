package Entities.SingleTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "SingleTable_Item")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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
