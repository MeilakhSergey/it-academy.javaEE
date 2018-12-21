package Entities.MappedSuperClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@AllArgsConstructor

@MappedSuperclass
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String itemName;
    @Column
    private String itemNameColumn;
}
