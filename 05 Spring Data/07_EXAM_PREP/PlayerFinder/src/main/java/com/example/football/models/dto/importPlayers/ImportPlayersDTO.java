package com.example.football.models.dto.importPlayers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "players")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPlayersDTO {



    @XmlElement(name = "player")
    private List<PlayerImportDTO> players;

    public ImportPlayersDTO() {}

    public List<PlayerImportDTO> getPlayers() {
        return players;
    }

}
