package com.example.football.models.dto.importPlayers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class TeamNameDTO {
    @XmlElement
    private String name;

    public String getName() {
        return name;
    }
}
