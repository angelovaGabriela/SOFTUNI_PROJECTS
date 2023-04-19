package com.example.spotifyplaylistapp.service;

public interface HomeService {
    void addSong(Long songId, Long userId);

    void deleteAll(Long userId);
}
