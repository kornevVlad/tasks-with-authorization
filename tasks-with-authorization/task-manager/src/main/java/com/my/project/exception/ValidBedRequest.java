package com.my.project.exception;

public class ValidBedRequest extends RuntimeException {

    public ValidBedRequest(String message) {
        super(message);
    }
}
