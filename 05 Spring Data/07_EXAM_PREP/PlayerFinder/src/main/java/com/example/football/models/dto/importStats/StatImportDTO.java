package com.example.football.models.dto.importStats;

import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class StatImportDTO {
    @Positive
    @XmlElement
    private float passing;
    @Positive
    @XmlElement
    private float shooting;
    @Positive
    @XmlElement
    private float endurance;

    public float getPassing() {
        return passing;
    }

    public float getShooting() {
        return shooting;
    }

    public float getEndurance() {
        return endurance;
    }
}
