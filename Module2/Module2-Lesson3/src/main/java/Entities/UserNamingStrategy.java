package Entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class UserNamingStrategy {
    @Id
    @GeneratedValue
    private Long id;
    @NaturalId
    private String login;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String familyName;
    @Column
    private String phone;
}
