package com.my.project.exception;


public class ValidNotFound extends RuntimeException {
    public ValidNotFound(String message) {
        super(message);
    }
}