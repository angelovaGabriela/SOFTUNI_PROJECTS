package _02_sales_database.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


@Entity
@Table (name = "products")
public class Product {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int quantity;

    private BigDecimal price;

    @OneToMany(targetEntity = Sale.class, mappedBy = "product") // class, field
    private Set<Sale> sales;




    public Product() {}

    public Product(String name, int quantity, BigDecimal price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
