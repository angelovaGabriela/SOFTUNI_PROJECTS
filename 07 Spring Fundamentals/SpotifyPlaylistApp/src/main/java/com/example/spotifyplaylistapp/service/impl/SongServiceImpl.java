package com.example.spotifyplaylistapp.service.impl;

import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.service.SongServiceModel;
import com.example.spotifyplaylistapp.model.view.SongViewModel;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.StyleService;
import com.example.spotifyplaylistapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements SongService {

    private final ModelMapper modelMapper;
    private final SongRepository songRepository;
    private final UserService userService;
    private final StyleService styleService;

    public SongServiceImpl(ModelMapper modelMapper,
                           SongRepository songRepository,
                           UserService userService,
                           StyleService styleService) {
        this.modelMapper = modelMapper;
        this.songRepository = songRepository;
        this.userService = userService;
        this.styleService = styleService;
    }

    @Override
    public void addSong(SongServiceModel songServiceModel) {
        Song song = modelMapper.map(songServiceModel, Song.class);
        song.setStyle(styleService.findByStyleNameEnum(songServiceModel.getStyle()));
        songRepository.save(song);
    }

    @Override
    public List<SongViewModel> findAllSongs() {
      return songRepository.findAll()
              .stream()
              .map(song -> modelMapper.map(song, SongViewModel.class))
              .collect(Collectors.toList());
    }
}
