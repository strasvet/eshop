package com.example.eshop.model.web;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginRequest {

    @NotBlank
    @Length(max = 50)
    private String userName;
    @NotBlank
    @Length(max = 50)
    private String password;

}
