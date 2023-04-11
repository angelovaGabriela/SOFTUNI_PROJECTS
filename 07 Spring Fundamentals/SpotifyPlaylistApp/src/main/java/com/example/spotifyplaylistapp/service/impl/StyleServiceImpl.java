package com.example.spotifyplaylistapp.service.impl;

import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.StyleNameEnum;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.service.StyleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StyleServiceImpl implements StyleService {

    private final StyleRepository styleRepository;

    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void initStyles() {

   if(this.styleRepository.count() == 0) {
       Arrays.stream(StyleNameEnum.values())
               .forEach(styleName -> {
                   Style style = new Style();
                   style.setStyleName(styleName);
                   this.styleRepository.save(style);
               });
   }
    }
}
