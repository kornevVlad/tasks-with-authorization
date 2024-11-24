package com.my.project.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ResponseException {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "NOT FOUND")
    @ExceptionHandler(ValidNotFound.class)
    public void errorValidation() {
        log.error("NOT FOUND");
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "BAD REQUEST")
    @ExceptionHandler(ValidBedRequest.class)
    public void errorValidationBadRequest() {
        log.error("BAD REQUEST");
    }
}