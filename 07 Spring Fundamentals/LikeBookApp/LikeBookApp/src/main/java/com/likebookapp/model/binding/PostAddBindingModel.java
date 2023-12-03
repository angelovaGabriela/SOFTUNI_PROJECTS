package com.likebookapp.model.binding;

import com.likebookapp.model.entity.MoodNameEnum;

import javax.validation.constraints.NotNull;

public class PostAddBindingModel {

    @NotNull
    private String content;

    @NotNull
    private MoodNameEnum mood;

    public PostAddBindingModel() {}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MoodNameEnum getMood() {
        return mood;
    }

    public void setMood(MoodNameEnum mood) {
        this.mood = mood;
    }
}
