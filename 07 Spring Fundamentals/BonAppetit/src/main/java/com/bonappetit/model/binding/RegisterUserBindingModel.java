package com.bonappetit.model.binding;

import com.bonappetit.vallidation.FieldMatch;
import com.bonappetit.vallidation.UniqueUserEmail;
import com.bonappetit.vallidation.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords do not match!"
)
public class RegisterUserBindingModel {
    @NotNull
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    @UniqueUsername(message = "The username is already taken!")
    private String username;


    @Email(message = "Must contain '@'!")
    @NotEmpty(message = "E-mail cannot be empty!")
    @UniqueUserEmail(message = "We already have a user with this e-mail!")
    private String email;

    @NotNull
    @Size(min = 3, max = 20,  message = "Password length must be between 3 and 20 characters!")
    private String password;

    @NotNull
    @Size(min = 3, max = 20, message = "")
    private String confirmPassword;

    public RegisterUserBindingModel() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
