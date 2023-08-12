package softuni.exam.models.dto;

import softuni.exam.models.entity.StarType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class StarImportDTO {

    @NotNull
    @Size(min = 6)
    private String description;
    @NotNull
    @Positive
    private Double lightYears;
    @NotNull
    @Size(min = 2, max = 30)
    private String name;
    @NotNull
    @Enumerated(EnumType.STRING)
    private StarType starType;
    @NotNull
    private Long constellation;

    public StarImportDTO() {}

    public String getDescription() {
        return description;
    }

    public Double getLightYears() {
        return lightYears;
    }

    public String getName() {
        return name;
    }

    public StarType getStarType() {
        return starType;
    }

    public Long getConstellation() {
        return constellation;
    }
}
