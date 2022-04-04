package com.example.demo.exceptions;

public class UserNotLoggedInException extends RuntimeException {

public  UserNotLoggedInException() {
    super("Execute Login command first!");
}

}
