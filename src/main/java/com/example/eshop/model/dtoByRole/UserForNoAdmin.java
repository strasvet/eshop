package com.example.eshop.model.dtoByRole;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserForNoAdmin {
    private String firstName;
    private String lastName;
    private String email;
}
