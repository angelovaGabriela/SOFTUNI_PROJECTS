package hiberspring.domain.entities;

import javax.persistence.*;


@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {


    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String position;



    @OneToOne(optional = false, cascade =CascadeType.ALL)
    @JoinColumn(name = "card_id", referencedColumnName = "id" ,unique = true)
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
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Name: %s %s%n", this.getFirstName(), this.getLastName()));
        builder.append(String.format("Position: %s%n", this.getPosition()));
        builder.append(String.format("Card Number: %s%n", this.getCard().getNumber()));
        builder.append("----------------------------------");

        return builder.toString();
    }
}
