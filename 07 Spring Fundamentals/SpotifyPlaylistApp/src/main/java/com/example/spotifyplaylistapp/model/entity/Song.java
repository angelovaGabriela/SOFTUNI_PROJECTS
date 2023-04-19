package com.example.spotifyplaylistapp.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "songs")
public class Song extends BaseEntity {

    @Column(nullable = false)
    private String performer;

    @Column(nullable = false)
    private String title;

    private Long duration;

    private LocalDate releaseDate;



    @Column(nullable = false)
    @ManyToMany(mappedBy = "playlist")
    private Set<User> users;

    @ManyToOne
    @JoinColumn(name = "style_id")
    private Style style;

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Song() {}

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }



}
