package com.likebookapp.service.impl;

import com.likebookapp.model.entity.Post;
import com.likebookapp.model.entity.User;
import com.likebookapp.model.service.PostServiceModel;
import com.likebookapp.repository.PostRepository;
import com.likebookapp.service.MoodService;
import com.likebookapp.service.PostService;
import com.likebookapp.service.UserService;
import com.likebookapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private final UserService userService;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final MoodService moodService;

    public PostServiceImpl(UserService userService, PostRepository postRepository,
                           ModelMapper modelMapper,
                           CurrentUser currentUser,
                           MoodService moodService) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.moodService = moodService;
    }

    @Override
    public void addPost(PostServiceModel postServiceModel) {
        Post post = modelMapper.map(postServiceModel, Post.class);
        post.setMood(this.moodService.findByMoodName(postServiceModel.getMood()));

        User user = this.userService.findUser(currentUser.getId());
        post.setUser(user);
        this.postRepository.save(post);

        user.offerAddedByMe(post);
        this.userService.saveUser(user);

    }
}
