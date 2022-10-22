package softuni.exam.models.dtos.importJSON;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class TownImportDTO {
    @Size(min = 2)
    private String name;

    @Positive
    private int population;

    private String guide;

    public TownImportDTO() {}

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getGuide() {
        return guide;
    }
}
