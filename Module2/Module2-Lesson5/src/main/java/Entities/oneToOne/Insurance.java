package Entities.oneToOne;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Insurance {
    @Id
    @GeneratedValue/*(generator = "oneToOne")
    @GenericGenerator(name = "oneToOne", strategy = "foreign",
                        parameters = @org.hibernate.annotations.Parameter(name = "property", value = "person"))*/
    private Long id;
    @Column
    private String insuranceNumber;
    @Column
    private String organization;

    @OneToOne(mappedBy = "insurance")
    @JoinColumn(name = "ID_person")
    //@PrimaryKeyJoinColumn
    private PersonParent person;

    /*public void setPerson(PersonParent person) {
        this.person = person;
        this.id = person.getId();
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Insurance insurance = (Insurance) o;
        return Objects.equals(id, insurance.id) &&
                Objects.equals(insuranceNumber, insurance.insuranceNumber) &&
                Objects.equals(organization, insurance.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, insuranceNumber, organization);
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "id=" + id +
                ", insuranceNumber='" + insuranceNumber + '\'' +
                ", organization='" + organization + '\'' +
                '}';
    }
}
