package com.example.demo.entities.users;

import com.example.demo.exceptions.ValidationException;

public class RegisterDTO {
    /**
     *  Validate the data for registering a user.
     *
     * Email must be . . .
     * Password must be . . .
     *
     * commandParts[0] is skipped because it contains the command name
     * which is not needed in the user object
     *
     * @param commandParts all data read from the console
     *
     * */

    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;

    public RegisterDTO(String [] commandParts) {

        this.email = commandParts[1];
        this.password = commandParts[2];
        this.confirmPassword = commandParts[3];
        this.fullName = commandParts[4];

        this.validate();
    }

    private void validate() {
        // everything is good = nothing happens

        /*
         you are cool :)
         */

        // throw new ValidationException

        int indexOfAt = email.indexOf("@");
        int indexOfDot = email.lastIndexOf(".");

        if (indexOfAt < 0 || indexOfDot < 0 || indexOfAt > indexOfDot) {
            throw new ValidationException("Email must contains [@ and .] ");
        }

        // TODO: VALIDATE PASSWORD

        if (!password.equals(confirmPassword)){

            throw new ValidationException("Password and confirm password must match!");
        }

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }
}
