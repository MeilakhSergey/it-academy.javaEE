package Entities;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @NaturalId
    private String login;
    @Column(updatable = false, nullable = false, length = 10)
    private String password;

    @Column
    @Access(AccessType.PROPERTY)
    private String name;

    @Column
    @Access(AccessType.PROPERTY)
    private String familyName;
    @Column(insertable = false)
    private String phone;

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


    public String getName() {
        return name + "+getter";
    }

    public String getFamilyName() {
        return familyName + "+getter";
    }

    public String getPhone() {
        return phone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
