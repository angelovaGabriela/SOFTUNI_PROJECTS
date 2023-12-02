package com.likebookapp.service.impl;

import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.entity.MoodNameEnum;
import com.likebookapp.repository.MoodRepository;
import com.likebookapp.service.MoodService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MoodServiceImpl implements MoodService {

    private final MoodRepository moodRepository;

    public MoodServiceImpl(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Override
    public void initMoods() {
        if (this.moodRepository.count() == 0) {
            Arrays.stream(MoodNameEnum.values())
                    .forEach(moodName -> {
                        Mood mood = new Mood();
                        mood.setName(moodName);
                        this.moodRepository.save(mood);
                    });
        }

    }
}
