package laptopShop.model.dtos.importShops;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class ShopImportDTO {

    @XmlElement
    @Size(min = 4)
    private String address;

    @XmlElement(name = "employee-count")
    @Min(1)
    @Max(50)
    private int employeeCount;
    @XmlElement
    @Min(20000)
    private BigDecimal income;
    @XmlElement
    @Size(min = 4)
    private String name;

    @XmlElement(name = "shop-area")
    @Min(150)
    private int shopArea;

    @XmlElement
    private TownNameDTO town;


    public String getAddress() {
        return address;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public String getName() {
        return name;
    }

    public int getShopArea() {
        return shopArea;
    }

    public TownNameDTO getTown() {
        return town;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShopArea(int shopArea) {
        this.shopArea = shopArea;
    }

    public void setTown(TownNameDTO town) {
        this.town = town;
    }
}
