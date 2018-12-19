package Entities;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class UserID1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
