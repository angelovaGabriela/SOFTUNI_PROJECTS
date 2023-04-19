package com.example.spotifyplaylistapp.service.impl;

import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.service.HomeService;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {

    private final UserService userService;
    private final SongService songService;

    public HomeServiceImpl(UserService userService, SongService songService) {
        this.userService = userService;
        this.songService = songService;
    }

    @Override
    public void addSong(Long songId, Long userId) {
        Song song = this.songService.findSongById(songId);
        this.userService.addSongToUser(userId, song);
    }

    @Override
    public void deleteAll(Long userId) {
        this.userService.deleteAllSongs(userId);
    }
}
