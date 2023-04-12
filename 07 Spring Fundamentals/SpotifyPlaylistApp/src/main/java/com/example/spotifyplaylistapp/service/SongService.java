package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.service.SongServiceModel;
import com.example.spotifyplaylistapp.model.view.SongViewModel;

import java.util.List;

public interface SongService {
    void addSong(SongServiceModel songServiceModel);

    List<SongViewModel> findAllSongs();

}
