package com.example.eshop.service;

import com.example.eshop.exception.InputValidationException;
import com.example.eshop.exception.UserNotFoundException;
import com.example.eshop.exception.myn.UserUnAuthorizedException;
import com.example.eshop.model.Enum.Role;
import com.example.eshop.model.User;
import com.example.eshop.model.UserSession;
import com.example.eshop.model.web.LoginRequest;
import com.example.eshop.model.web.RegistrationRequest;
import com.example.eshop.repository.SessionRepository;
import com.example.eshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserService userService;
/*    @Autowired
    private EntityManager entityManager;*/
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
        user.setPassword(request.getPassword());
        user.setUserName(request.getUserName());
        user.setBalance(request.getBalance());
        List<Role> roles = new ArrayList<>();
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            roles.add(Role.BUYER);
            roles.add(Role.SELLER);
            roles.add(Role.ADMIN);
        }else{
            roles.add(Role.BUYER);
            roles.add(Role.SELLER);
        }
        user.setRoles(roles);
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
/*        if(!sessionRepository.getBySessionId(sessionId).getIsValid()){
            String errorMessage = String.format("Unauthorized [%s] in the system", sessionId);
            throw new InputValidationException("unauthorized", errorMessage);
        }else{
            */
            return userRepository.findByEmail(email);

    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User logIn(LoginRequest request) {
        User user = userRepository.getByUserNameAndPassword(request.getUserName(), request.getPassword());
        if (user == null) {
            throw new UserNotFoundException("User by username [" + request.getUserName() + "] and password [" + request.getPassword() + "] not found in the system");
        }
        UserSession userSession = new UserSession();


        UserSession finedSession = new UserSession();
        /*
        finedSession = sessionRepository.getBySessionId(user.getId());
        if(finedSession!= null && !finedSession.getIsValid()){
            entityManager.refresh(user);
        }
        */
        finedSession = sessionRepository.getBySessionId(user.getId());
        if (finedSession == null) {
            userSession.setSessionId(user.getId());
            ////
            userSession.setUser(user);
            userSession.setIsValid(true);
            sessionRepository.save(userSession);
            return user;
        } else if (!finedSession.getIsValid()) {
            finedSession.setIsValid(true);
            sessionRepository.save(finedSession);
            return user;
        } else {
            String errorMessage = String.format("You are already in the system [%s]", finedSession.getSessionId());
            throw new InputValidationException("alreadylogin", errorMessage);
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Boolean invalidateSession(String sessionId) {
        UserSession userSession = sessionRepository.getBySessionId(sessionId);
        System.err.println(userSession);
        if(userSession == null || !userSession.getIsValid()) {
            System.err.println("NOT FOUND USER SESSION/INVALID SESSION!!!");
            System.err.println(userSession);
            return false;
        }else{
            userSession.setIsValid(false);
            sessionRepository.save(userSession);
            System.err.println(userSession);
            return true;
        }
    }

    @Override
    public void logOut(String sessionId) {
        Boolean logOut = userService.invalidateSession(sessionId);
        if(!logOut){
            String errorMessage = String.format("NotFound Session or session is InValid ");
            throw new InputValidationException("logOut", errorMessage);
        }
            String errorMessage = String.format("You are exit from system ");
            throw new InputValidationException("logOut", errorMessage);
    }
}
