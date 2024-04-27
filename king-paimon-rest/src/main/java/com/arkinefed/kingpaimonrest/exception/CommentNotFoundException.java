package com.arkinefed.kingpaimonrest.exception;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(Long id) {
        super("comment " + String.valueOf(id) + " not found");
    }
}
