package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ConstellationImportDTO {

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @NotNull
    @Size(min = 5)
    private String description;

    public ConstellationImportDTO() {}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
