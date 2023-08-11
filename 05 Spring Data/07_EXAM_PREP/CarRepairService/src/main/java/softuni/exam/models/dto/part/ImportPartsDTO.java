package softuni.exam.models.dto.part;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ImportPartsDTO {

    @Size(min = 2, max = 19)
    private String partName;

    @Column(nullable = false)
    @Min(10)
    @Max(2000)
    private Double price;

    @Positive
    private Integer quantity;

    public ImportPartsDTO() {}

    public String getPartName() {
        return partName;
    }


    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
