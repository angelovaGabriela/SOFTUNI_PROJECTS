package softuni.exam.domain.entities;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "players")
public class Player extends  BaseEntity{

    @Column(name = "first_name", nullable = false)
    private String firstName;


    @Column(name = "last_name", nullable = false)
    private String lastName;


    @Column(nullable = false)
    private int number;


    @Column(nullable = false)
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlayerPosition position;


    @ManyToOne
    @JoinColumn(name = "picture_id", nullable = false)
    private Picture picture;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    public Player() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(PlayerPosition position) {
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }


    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return number == player.number && Objects.equals(firstName, player.firstName) && Objects.equals(lastName, player.lastName) && Objects.equals(salary, player.salary) && position == player.position && Objects.equals(picture, player.picture) && Objects.equals(team, player.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, number, salary, position, picture, team);
    }

    @Override
    public String toString() {


        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Player name: %s %s%n", this.getFirstName(), this.getLastName()));
        builder.append(String.format("Number: %d%n", this.getNumber()));
        builder.append(String.format("Salary: %.2f%n", this.getSalary()));
        builder.append(String.format("Team: %s", this.getTeam().getName()));




        return builder.toString();
    }
}
