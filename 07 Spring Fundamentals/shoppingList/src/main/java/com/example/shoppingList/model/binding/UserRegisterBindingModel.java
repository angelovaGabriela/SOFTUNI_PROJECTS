package com.example.shoppingList.model.binding;

import org.hibernate.annotations.BatchSize;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {

    @NotNull
    @Size(min = 3, max = 20)
    private String username;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 3, max = 20)
    private String password;

    @NotNull
    @Size(min = 3, max = 20)
    private String confirmPassword;


    public UserRegisterBindingModel() {}

    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
