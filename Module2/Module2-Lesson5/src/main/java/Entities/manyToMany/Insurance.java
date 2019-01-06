package Entities.manyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "ManyToMany_Insurance")
public class Insurance {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String insuranceNumber;
    @Column
    private String organization;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "People_insurance", joinColumns = @JoinColumn(name = "ID_insurance"),
                inverseJoinColumns = @JoinColumn(name = "ID_person"))
    private List<PersonParent> person = new LinkedList<>();

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
