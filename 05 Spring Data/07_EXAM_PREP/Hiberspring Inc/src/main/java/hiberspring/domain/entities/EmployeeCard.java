package hiberspring.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "employees_cards")
public class EmployeeCard extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String number;

    @OneToOne(mappedBy = "card")
    private Employee employee;

    public EmployeeCard() {}


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
