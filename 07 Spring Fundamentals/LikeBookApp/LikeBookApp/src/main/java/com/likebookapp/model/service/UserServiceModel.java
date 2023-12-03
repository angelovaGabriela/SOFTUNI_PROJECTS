package com.likebookapp.model.service;

import com.likebookapp.model.entity.Post;

import javax.persistence.Column;
import java.util.List;

public class UserServiceModel {


    private Long id;
    private String username;

    private String password;

    private String email;

    private List<Post> myPosts;

    public UserServiceModel() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Post> getMyPosts() {
        return myPosts;
    }

    public void setMyPosts(List<Post> myPosts) {
        this.myPosts = myPosts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

