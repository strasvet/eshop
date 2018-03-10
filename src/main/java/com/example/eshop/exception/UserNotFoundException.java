package com.example.eshop.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends GeneralAPIException {

    public UserNotFoundException() {

    }

    public UserNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public Object getErrors() {
        return super.getErrors();
    }

}