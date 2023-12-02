package com.likebookapp.service.impl;

import com.likebookapp.repository.PostRepository;
import com.likebookapp.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
