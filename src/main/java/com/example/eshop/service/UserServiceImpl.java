package com.example.eshop.service;

import com.example.eshop.exception.InputValidationException;
import com.example.eshop.exception.UserNotFoundException;
import com.example.eshop.model.User;
import com.example.eshop.model.web.RegistrationRequest;
import com.example.eshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(RegistrationRequest request) {
        User findUser = userRepository.findByEmail(request.getEmail());
        if (findUser != null) {
            String errorMessage = String.format("Email [%s] is already present in the system", request.getEmail());
            throw new InputValidationException("email", errorMessage);
        }
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setBalance(request.getBalance());
        return userRepository.save(user);
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID [" + id + "] not found"));
    }

    @Override
    public List<User> findAll() {
        List<User> list = userRepository.findAll();
        if(list.isEmpty()) throw new UserNotFoundException("Database is empty, users not found.");
        return list;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
