package Entities.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String familyName;
    @Column
    private String phone;


    @Embedded
    private Address address;

    @AttributeOverrides( {
            @AttributeOverride(name="city", column = @Column(name="home_city")),
            @AttributeOverride(name="street", column = @Column(name="home_street")),
            @AttributeOverride(name="building", column = @Column(name="home_building")),
            @AttributeOverride(name="flat", column = @Column(name="home_flat")),
    })
    @Embedded
    private Address home_address;
}
