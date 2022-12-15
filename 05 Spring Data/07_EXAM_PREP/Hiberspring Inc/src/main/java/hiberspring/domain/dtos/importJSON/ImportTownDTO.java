package hiberspring.domain.dtos.importJSON;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class ImportTownDTO {

    @NotNull
    private String name;

    private int population;

    public ImportTownDTO() {}

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }
}
