package com.example.eshop.exception;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.*;
import java.util.stream.Collectors;

public class InputValidationException extends GeneralAPIException {

    private Map<String, List<String>> errors;
    private BindingResult result;

    public InputValidationException(BindingResult result) {
        this.result = result;
    }

    public InputValidationException(String fieldName, String errorMessage) {
        this.errors = new HashMap<>();
        this.errors.put(fieldName, Collections.singletonList(errorMessage));
    }

    public Map<String, List<String>> getErrors() {
        if (this.errors != null) {
            return this.errors;
        }
        return result.getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(DefaultMessageSourceResolvable::getDefaultMessage,
                                Collectors.toList())
                ));
    }
}