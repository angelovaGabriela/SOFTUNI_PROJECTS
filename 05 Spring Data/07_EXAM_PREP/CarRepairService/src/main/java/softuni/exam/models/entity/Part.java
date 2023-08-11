package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;


@Entity
@Table(name = "parts")
public class Part extends BaseEntity {

    @Column(name = "part_name", nullable = false, unique = true)

    private String partName;

    @Column(nullable = false)
    //@Min(10)
    //@Max(2000)
    private Double price;

    @Column(nullable = false)
    //@Positive
    private Integer quantity;


    public Part() {}


    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
