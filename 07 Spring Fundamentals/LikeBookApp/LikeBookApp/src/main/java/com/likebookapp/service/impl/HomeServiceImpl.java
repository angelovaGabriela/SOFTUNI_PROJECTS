package com.likebookapp.service.impl;

import com.likebookapp.model.entity.Post;
import com.likebookapp.model.entity.User;
import com.likebookapp.service.HomeService;
import com.likebookapp.service.PostService;
import com.likebookapp.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class HomeServiceImpl implements HomeService {

    private final UserService userService;
    private final PostService postService;

    public HomeServiceImpl(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }


    @Transactional
    @Override
    public void removePost(Long postId, Long userID) {
        User user = this.userService.findUser(userID);
        Post post = this.postService.findPost(postId);

        user.removePost(post);
        this.userService.saveUser(user);

        this.postService.removePost(post);

    }
}
