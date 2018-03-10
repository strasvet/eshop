package com.example.eshop.service;

import com.example.eshop.model.User;
import com.example.eshop.model.web.RegistrationRequest;

import java.util.List;

public interface UserService {
    User create(RegistrationRequest request);

    User getById(Integer id);

    List<User> findAll();

    User findByEmail(String email);
}
