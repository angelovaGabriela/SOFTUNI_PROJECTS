package com.likebookapp.service.impl;

import com.likebookapp.model.enums.MoodNameEnum;
import com.likebookapp.model.entity.Mood;
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
    public void initMood() {


        if (moodRepository.count() != 0) {
            return;
        }


        Arrays.stream(MoodNameEnum.values())
                .forEach(moodNameEnum -> {
                    Mood mood = new Mood();
                    mood.setMoodName(moodNameEnum);
                    switch (moodNameEnum) {
                        case Happy:
                            mood.setMoodName(MoodNameEnum.Happy);
                            break;
                        case Sad:
                            mood.setMoodName(MoodNameEnum.Sad);
                            break;
                        case Inspired:
                            mood.setMoodName(MoodNameEnum.Inspired);
                            break;

                    }

                    this.moodRepository.save(mood);

                });

    }
}

