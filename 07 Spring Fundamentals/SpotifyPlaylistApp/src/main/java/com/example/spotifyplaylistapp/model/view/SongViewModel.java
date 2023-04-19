package com.example.spotifyplaylistapp.model.view;

import com.example.spotifyplaylistapp.model.entity.Style;

public class SongViewModel {

    private Long id;
    private String performer;
    private String title;
    private Long duration;

    private Style style;

    public SongViewModel() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public String durationInMinutes() {
        Long hours = this.duration / 3600;
        Long minutes = (this.duration % 3600) / 60;
        Long seconds = this.duration % 60;

        if (hours <= 0) {
            return String.format("%02d:%02d",minutes, seconds);
        } else {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }
    }


}
