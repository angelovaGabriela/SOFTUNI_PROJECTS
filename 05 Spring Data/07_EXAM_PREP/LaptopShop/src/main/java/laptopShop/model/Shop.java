package laptopShop.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    // TODO: @Size(min = 4) IN DTO CLASS
    private String name;

    @Column(nullable = false)
    // TODO @Min(20000) IN DTO CLASS
    private BigDecimal income;

    @Column(nullable = false)
   // TODO @Size(min = 4) IN DTO CLASS
    private String address;

    @Column(name = "employee_count")
    /* TODO: @Min(1) @Max(50) IN DTO CLASS */
    private int employeeCount;

    @Column(name = "shop_area")
    // TODO @Min(150) IN DTO CLASS
    private int shopArea;

    @ManyToOne(optional = false)
    @JoinColumn(name = "town_id")
    private Town town;

    @OneToMany(targetEntity = Laptop.class, mappedBy = "shop")
    private Set<Laptop> laptops;

    public Set<Laptop> getLaptops() {
        return laptops;
    }

    public void setLaptops(Set<Laptop> laptops) {
        this.laptops = laptops;
    }

    public Shop() {}

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public int getShopArea() {
        return shopArea;
    }

    public void setShopArea(int shopArea) {
        this.shopArea = shopArea;
    }
}
