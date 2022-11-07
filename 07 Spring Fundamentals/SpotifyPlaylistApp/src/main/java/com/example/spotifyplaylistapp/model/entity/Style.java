package com.example.spotifyplaylistapp.model.entity;

import com.example.spotifyplaylistapp.model.entity.enums.StyleName;

import javax.persistence.*;

@Table(name = "styles")
@Entity
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(unique = true, nullable = false)
    private StyleName name;

    private String description;


    public Style() {}

    public Style(StyleName name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StyleName getName() {
        return name;
    }

    public void setName(StyleName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
