package softuni.exam.service.impl;

import softuni.exam.domain.dtos.importJSON.PlayerPictureDTO;
import softuni.exam.domain.dtos.importJSON.TeamNameAndPictureDTO;
import softuni.exam.domain.entities.PlayerPosition;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class PlayersImportDTO {

    @NotNull
    private String firstName;
    @Size(min = 3, max = 15)
    private String lastName;

    @Min(1) @Max(99)
    private int number;

    @Min(0)
    private BigDecimal salary;

    @NotNull
    private PlayerPosition position;

    private PlayerPictureDTO picture;
    private TeamNameAndPictureDTO team;

    public PlayersImportDTO() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNumber() {
        return number;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public PlayerPictureDTO getPicture() {
        return picture;
    }

    public TeamNameAndPictureDTO getTeam() {
        return team;
    }
}
