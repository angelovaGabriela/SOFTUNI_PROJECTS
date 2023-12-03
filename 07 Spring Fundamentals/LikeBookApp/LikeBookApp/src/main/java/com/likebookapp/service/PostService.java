package com.likebookapp.service;

import com.likebookapp.model.entity.Post;
import com.likebookapp.model.service.PostServiceModel;
import com.likebookapp.model.views.PostViewModel;

import java.util.List;

public interface PostService {
    void addPost(PostServiceModel postServiceModel);


    List<PostViewModel> findAllMyPosts();
}
