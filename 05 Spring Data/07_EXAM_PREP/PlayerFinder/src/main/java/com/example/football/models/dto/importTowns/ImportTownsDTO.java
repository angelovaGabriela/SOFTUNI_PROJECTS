package com.example.football.models.dto.importTowns;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ImportTownsDTO {
    @Size(min = 2)
    private String name;

    @Positive
    private int population;

    @Size(min = 10)
    private String travelGuide;

    public ImportTownsDTO() {}

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getTravelGuide() {
        return travelGuide;
    }
}
