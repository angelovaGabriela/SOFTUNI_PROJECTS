package softuni.exam.domain.entities;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "teams")
public class Team extends BaseEntity {


    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "picture_id", nullable = false)
    private Picture picture;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private Set<Player> players;

    public Team() {}

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
