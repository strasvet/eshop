package com.example.eshop.controller;

import com.example.eshop.exception.InputValidationException;
import com.example.eshop.model.User;
import com.example.eshop.model.web.LoginRequest;
import com.example.eshop.model.web.RegistrationRequest;
import com.example.eshop.repository.UserRepository;
import com.example.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

/*    @PostMapping("/create/{username}")
    public User create(@PathVariable("username") String username){
        User user = new User();
        user.setFirstName(username);
        return userRepository.save(user);
    }*/

    @PostMapping("/login")
    public User login(@RequestBody @Valid LoginRequest request, BindingResult result){
        if (result.hasErrors()) {
            throw new InputValidationException(result);
        }
        return userService.logIn(request);
    }
    @PutMapping("/logout")
    public void logOut(@RequestHeader("Authorization") String sessionId){
        //if (result.hasErrors()) { throw new InputValidationException(result); }
        userService.invalidateSession(sessionId);
    }

    @PostMapping("/register")
    public User register(@RequestBody RegistrationRequest request, BindingResult result){
        if (result.hasErrors()) {
//            List<String> collect = result.getFieldErrors()
//                    .stream()
//                    .map(x ->
//                            String.format("%s: %s",
//                                    x.getField(),
//                                    x.getDefaultMessage())
//                    )
//                    .collect(Collectors.toList());
//            throw new RuntimeException(collect.toString());
            throw new InputValidationException(result);
        }
        return userService.create(request);

    }

    @GetMapping("/all")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{email}")
    public User getUserById(@PathVariable("email") String email,@RequestHeader("Authorization") String sessionId) {
        return userService.findByEmail(email);
    }
    /*
    @GetMapping("/all")
    public List<User> findAll(@RequestParam("page") Integer page, @RequestParam("count") Integer count){
        Sort.Order userNameSort = Sort.Order.by("username").with(Sort.Direction.DESC);
        Sort.Order dateSort = Sort.Order.by("createdOn").with(Sort.Direction.ASC);
        Sort sort = Sort.by(userNameSort, dateSort);
        return userRepository.findAllByCreatedOnNotNull(PageRequest.of(page,count,sort)).getContent();

    }
    */
    /*
    @GetMapping("/all-sorted")
    public List<User> getSorted() {
        Sort.Order userNameSort = Sort.Order.by("username").with(Sort.Direction.DESC);
        Sort.Order dateSort = Sort.Order.by("createdOn").with(Sort.Direction.DESC).nullsNative();
        return userRepository.findAll(Sort.by(userNameSort, dateSort));
    }

    @GetMapping("/max-age")
    public Integer getMaxAge() {
        return userRepository.findMaxAge();
    }
*/

}
