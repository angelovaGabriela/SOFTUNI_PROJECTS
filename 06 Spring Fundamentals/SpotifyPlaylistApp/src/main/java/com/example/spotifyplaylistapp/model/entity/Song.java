package com.example.spotifyplaylistapp.model.entity;

import jdk.jfr.Timespan;


import javax.persistence.*;

import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Table(name = "songs")
@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(unique = true, nullable = false)
    private String performer;

    @Column(nullable = false)
    private String title;

    @Timespan
    @Positive
    @Column(nullable = false)
    private float duration;


    private LocalDate releaseDate;

    @ManyToOne
    private Style style;

    public Song(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}
