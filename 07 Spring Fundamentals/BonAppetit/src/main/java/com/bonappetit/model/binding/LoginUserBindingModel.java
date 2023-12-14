package com.bonappetit.model.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginUserBindingModel {
    @NotNull
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;

    @NotNull
    @Size(min = 3, max = 20,  message = "Password length must be between 3 and 20 characters!")
    private String password;

    public LoginUserBindingModel() {}

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

}
