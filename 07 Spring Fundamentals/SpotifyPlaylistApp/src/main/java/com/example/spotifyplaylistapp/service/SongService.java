package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.service.SongServiceModel;
import com.example.spotifyplaylistapp.model.view.SongViewModel;

import java.util.List;
import java.util.Set;

public interface SongService {
    void addSong(SongServiceModel songServiceModel);

    List<SongViewModel> findAllSongs();

    Song findSongById(Long songId);

    Set<SongViewModel> getPlaylist(Long id);

    String getTotalDuration(Long id);
}
