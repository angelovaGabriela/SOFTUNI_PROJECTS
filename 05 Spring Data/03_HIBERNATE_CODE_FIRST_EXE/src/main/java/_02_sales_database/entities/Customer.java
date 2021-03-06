package _02_sales_database.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name = "customers")
public class Customer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;


    private String name;

    private String email;

    @Column (name  = "credit_card_number", nullable = false)
    private String creditCardNumber;

    @OneToMany (targetEntity = Sale.class, mappedBy = "customer")
    private Set<Sale> sale;

    public Customer () {}

    public Customer(String name, String email, String creditCardNumber) {
        this.name = name;
        this.email = email;
        this.creditCardNumber = creditCardNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
}
