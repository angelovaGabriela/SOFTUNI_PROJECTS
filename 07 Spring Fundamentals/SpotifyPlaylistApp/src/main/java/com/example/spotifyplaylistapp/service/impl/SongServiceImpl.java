package com.example.spotifyplaylistapp.service.impl;

import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.service.SongServiceModel;
import com.example.spotifyplaylistapp.model.view.SongViewModel;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.StyleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements SongService {

    private final ModelMapper modelMapper;
    private final SongRepository songRepository;

    private final StyleService styleService;

    public SongServiceImpl(ModelMapper modelMapper,
                           SongRepository songRepository,
                           StyleService styleService) {
        this.modelMapper = modelMapper;
        this.songRepository = songRepository;
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

    @Override
    public Song findSongById(Long songId) {
      return   this.songRepository.findById(songId).orElseThrow();
    }

    @Override
    public Set<SongViewModel> getPlaylist(Long id) {

        return this.songRepository.findAllByUserId(id)
                .stream()
                .map(this::mapSongViewModel)
                .collect(Collectors.toSet());


    }

    @Override
    public String getTotalDuration(Long id) {
        Long sum = this.getPlaylist(id).stream().mapToLong(SongViewModel::getDuration).sum();

        Long hours = sum / 3600;
        Long minutes = (sum % 3600) / 60;
        Long seconds = sum % 60;

        if (hours <= 0) {
            return String.format("%02d:%02d",minutes, seconds);
        } else {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }
    }

    private SongViewModel mapSongViewModel(Song song) {
        SongViewModel songView = new SongViewModel();
        songView.setId(song.getId());
        songView.setDuration(song.getDuration());
        songView.setPerformer(song.getPerformer());
        songView.setTitle(song.getTitle());
        return songView;
    }
}
