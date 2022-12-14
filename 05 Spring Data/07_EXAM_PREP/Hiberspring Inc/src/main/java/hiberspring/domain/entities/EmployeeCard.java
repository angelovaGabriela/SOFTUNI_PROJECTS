package hiberspring.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "employees_cards")
public class EmployeeCard extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String number;

    public EmployeeCard() {}


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
