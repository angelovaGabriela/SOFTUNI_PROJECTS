package com.likebookapp.model.entity;

import com.likebookapp.model.enums.MoodNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "moods")
public class Mood extends BaseEntity {
    
    @Enumerated(EnumType.STRING)
    @Column(name = "mood_name", nullable = false, unique = true)
    private MoodNameEnum moodName;

    private String description;

    public Mood() {
    }


    public MoodNameEnum getMoodName() {
        return moodName;
    }

    public void setMoodName(MoodNameEnum moodName) {
        this.moodName = moodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
