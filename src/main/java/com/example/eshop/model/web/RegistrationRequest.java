package com.example.eshop.model.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationRequest {

    @NotBlank
    @Length(max = 50)
    private String firstName;
    @NotBlank
    @Length(max = 50)
    private String lastName;
    @NotBlank
    @Length(max = 50)
    @Email
    private String email;

    private Double balance;

    @NotBlank
    @Length(min = 3, max = 50)
    private String password;

    @NotBlank
    @Length(max = 50)
    private String userName;
}
