package com.arkinefed.kingpaimonrest.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        super("username " + username + " is already taken");
    }
}