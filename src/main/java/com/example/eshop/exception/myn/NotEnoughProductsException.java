package com.example.eshop.exception.myn;

import com.example.eshop.exception.GeneralAPIException;
import org.springframework.http.HttpStatus;

public class NotEnoughProductsException extends GeneralAPIException {

    public NotEnoughProductsException() {

    }

    public NotEnoughProductsException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_ACCEPTABLE;
    }

    @Override
    public Object getErrors() {
        return super.getErrors();
    }

}