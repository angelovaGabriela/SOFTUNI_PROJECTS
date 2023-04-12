package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.StyleNameEnum;

public interface StyleService {
    void initStyles();

    Style findByStyleNameEnum(StyleNameEnum styleNameEnum);
}
