package com.example.eshop.model.web;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ErrorResponse {

    private Object errorMessage;
    private Date occurredOn;
    private String hostName;
    private String status;

}
