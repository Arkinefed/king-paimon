package com.arkinefed.kingpaimonrest.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long id) {
        super("post " + String.valueOf(id) + " not found");
    }
}
