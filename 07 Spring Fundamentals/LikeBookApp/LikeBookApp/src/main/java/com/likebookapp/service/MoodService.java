package com.likebookapp.service;

import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.entity.MoodNameEnum;

public interface MoodService {
    void initMoods();



    Mood findByMoodName(MoodNameEnum mood);
}
