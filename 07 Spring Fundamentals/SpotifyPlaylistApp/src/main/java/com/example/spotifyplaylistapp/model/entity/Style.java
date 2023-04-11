package com.example.spotifyplaylistapp.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "styles")
public class Style extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private StyleNameEnum styleName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "style", fetch = FetchType.LAZY)
    private Set<Song> songs;

    public Style() {}

    public StyleNameEnum getStyleName() {
        return styleName;
    }

    public void setStyleName(StyleNameEnum styleName) {
        this.styleName = styleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
