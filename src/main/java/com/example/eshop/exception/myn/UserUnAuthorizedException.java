package com.example.eshop.exception.myn;

import com.example.eshop.exception.GeneralAPIException;
import org.springframework.http.HttpStatus;

public class UserUnAuthorizedException extends GeneralAPIException {

    public UserUnAuthorizedException() {

    }

    public UserUnAuthorizedException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }

    @Override
    public Object getErrors() {
        return super.getErrors();
    }

}