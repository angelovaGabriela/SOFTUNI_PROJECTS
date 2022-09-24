package laptopShop.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "laptops")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "mac_address", nullable = false, unique = true)
   // TODO  @Size(min = 9) IN DTO CLASS
    private String macAddress;

    @Column(name = "cpu_speed", nullable = false)
    // TODO: @Positive
    private double cpuSpeed;

    /* TODO: @Min(8) @Max(128) IN DTO CLASS*/
    private int ram;

    /* TODO: @Min(128) @Max(1024) IN DTO CLASS*/
    private int storage;

    @Column(nullable = false, columnDefinition = "TEXT")
   // TODO: @Size(min = 10)
    private String description;

    @Column(nullable = false)
    // TODO: @Positive
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "warranty_type", nullable = false)
    private WarrantyType warrantyType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    public Laptop() {}

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public double getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public WarrantyType getWarrantyType() {
        return warrantyType;
    }

    public void setWarrantyType(WarrantyType warrantyType) {
        this.warrantyType = warrantyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return id == laptop.id && Objects.equals(macAddress, laptop.macAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, macAddress);
    }
}
