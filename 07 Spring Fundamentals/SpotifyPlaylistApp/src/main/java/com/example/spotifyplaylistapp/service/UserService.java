package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUserNameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    void addSongToUser(Long userId, Song song);

    void deleteAllSongs(Long userId);
}
