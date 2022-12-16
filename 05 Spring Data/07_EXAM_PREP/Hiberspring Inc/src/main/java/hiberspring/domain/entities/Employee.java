package hiberspring.domain.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {


    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String position;



    @OneToOne(optional = false)
    @JoinColumn(name = "card_id", unique = true)
    private EmployeeCard card;


    @ManyToOne(optional = false)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    public Employee() {}


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public EmployeeCard getCard() {
        return card;
    }

    public void setCard(EmployeeCard card) {
        this.card = card;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return firstName.equals(employee.firstName) && lastName.equals(employee.lastName) && position.equals(employee.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, position);
    }
}
