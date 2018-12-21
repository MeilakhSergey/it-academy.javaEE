package Entities.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class Address {
    @Column
    private String city;
    @Column
    private String street;
    @Column
    private String building;
    @Column
    private String flat;
}
