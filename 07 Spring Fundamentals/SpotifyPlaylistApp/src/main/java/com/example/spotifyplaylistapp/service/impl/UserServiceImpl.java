package com.example.spotifyplaylistapp.service.impl;

import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.model.service.UserServiceModel;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        this.userRepository.save(user);
    }

    @Override
    public UserServiceModel findByUserNameAndPassword(String username, String password) {

        return this.userRepository.findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class)).orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {
        currentUser.setId(id);
        currentUser.setUsername(username);
    }

    @Override
    public void addSongToUser(Long userId, Song song) {

        User user = this.getUSerById(userId);
        if (user.getPlaylist().stream().noneMatch(s -> s.getId().equals(song.getId()))) {
            user.addSongToPlaylist(song);
            this.userRepository.save(user);
        }

    }

    @Override
    public void deleteAllSongs(Long userId) {
        User user = getUSerById(userId);
        user.deleteAllSongFromPlaylist();
        this.userRepository.save(user);
    }

    private User getUSerById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow();
    }


}
