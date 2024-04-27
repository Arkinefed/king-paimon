package com.arkinefed.kingpaimonrest.exception;

public class EmailAlreadyTakenException extends RuntimeException {
    public EmailAlreadyTakenException(String email) {
        super("email " + email + " was aready used to register an account");
    }
}
