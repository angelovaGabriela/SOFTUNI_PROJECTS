package com.example.football.models.dto.importPlayers;

import com.example.football.models.entity.Position;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType
public class PlayerImportDTO {
    @XmlElement(name = "first-name")
    @Size(min = 2)
    private String firstName;

    @Size(min = 2)
    @XmlElement(name = "last-name")
    private String lastName;

    @Email
    @XmlElement
    private String email;
    @XmlElement(name = "birth-date")
    private String birthDate;

    @XmlElement
    private Position position;

    @XmlElement
    private TownNameDTO town;
    @XmlElement
    private TeamNameDTO team;
    @XmlElement
    private StatIdDTO stat;

    public PlayerImportDTO() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Position getPosition() {
        return position;
    }

    public TownNameDTO getTown() {
        return town;
    }

    public TeamNameDTO getTeam() {
        return team;
    }

    public StatIdDTO getStat() {
        return stat;
    }
}
