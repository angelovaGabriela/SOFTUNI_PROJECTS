package com.likebookapp.service;

public interface HomeService {
    void removePost(Long postId, Long userID);

    void likePost(Long postId, Long userId);
}
