package com.example.eshop.controller;

import com.example.eshop.exception.GeneralAPIException;
import com.example.eshop.model.web.ErrorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ErrorController {

    @Value("${spring.application.name}")
    private String host;

    @ExceptionHandler(GeneralAPIException.class)
    public ResponseEntity<ErrorResponse> getAPIResponse(GeneralAPIException e) {
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .errorMessage(e.getErrors())
                .occurredOn(new Date())
                .status(e.getHttpStatus().getReasonPhrase())
                .hostName(host)
                .build();
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> getOtherErrors(Exception e) {
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .errorMessage(e.getMessage())
                .occurredOn(new Date())
                .hostName(host)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

