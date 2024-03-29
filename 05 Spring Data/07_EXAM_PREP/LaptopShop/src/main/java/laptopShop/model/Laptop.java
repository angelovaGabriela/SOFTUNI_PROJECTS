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
    private String macAddress;

    @Column(name = "cpu_speed", nullable = false)
    private double cpuSpeed;
    private int ram;

    private int storage;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal price;


    @Column(name = "warranty_type")
    @Enumerated
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("Laptop - %s", this.macAddress)).append(System.lineSeparator());
        builder.append(String.format("*Cpu speed - %.2f", this.cpuSpeed)).append(System.lineSeparator());
        builder.append(String.format("**Ram - %d", this.ram)).append(System.lineSeparator());
        builder.append(String.format("***Storage - %d", this.storage)).append(System.lineSeparator());
        builder.append(String.format("****Price - %.2f", this.price)).append(System.lineSeparator());
        builder.append(String.format("#Shop name - %s", this.getShop().getName())).append(System.lineSeparator());
        builder.append(String.format("##Town - %s", this.getShop().getTown().getName())).append(System.lineSeparator());

        return builder.toString();

    }
}
