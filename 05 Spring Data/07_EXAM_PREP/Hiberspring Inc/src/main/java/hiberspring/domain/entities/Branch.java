package hiberspring.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "branches")
public class Branch extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "town_id")
    private Town town;

    @OneToMany
            (targetEntity = Employee.class, mappedBy = "branch", fetch = FetchType.EAGER)
    private Set<Employee> employees;

    @OneToMany
            (targetEntity = Product.class, mappedBy = "branch", fetch = FetchType.EAGER)
    private Set<Product> products;

    public Branch() {}

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
