package softuni.exam.models.dto;

import javax.persistence.Column;
import javax.validation.constraints.*;

public class ImportPartsDTO {

    @NotNull
    @Size(min = 2, max = 19)
    private String partName;

    @NotNull
    @DecimalMin(value = "10.0")
    @DecimalMax(value = "2000.0")
    private Double price;

    @NotNull
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
