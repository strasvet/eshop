package com.example.eshop.exception.myn;

import com.example.eshop.exception.GeneralAPIException;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends GeneralAPIException {

    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
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
