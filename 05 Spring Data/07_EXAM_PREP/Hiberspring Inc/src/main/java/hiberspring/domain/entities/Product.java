package hiberspring.domain.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Column(nullable = false)
    private String name;


    private int clients;


    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    public Product() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClients() {
        return clients;
    }

    public void setClients(int clients) {
        this.clients = clients;
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
        Product product = (Product) o;
        return clients == product.clients && name.equals(product.name) && branch.equals(product.branch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, clients, branch);
    }
}
