package com.example.football.models.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ImportTownDTO {

    @Size(min = 2) // по-голямо или реавно на 2
    private String name;

    @Positive // трябва да е положително число
    private int population;

    @Size(min = 10) // с най-малко 10 символа
    private String travelGuide;

    public ImportTownDTO() {}


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
