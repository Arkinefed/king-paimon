package com.arkinefed.kingpaimonrest.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String username) {
        super("user " + username + " not found");
    }
}
