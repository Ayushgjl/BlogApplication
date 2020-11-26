package com.example.blog.controllers;


import com.example.blog.entities.Role;
import com.example.blog.entities.User;
import com.example.blog.pojos.UserRegistration;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenStore tokenStore;

    @PostMapping(value = "/register")
    public String register(@RequestBody UserRegistration userRegistration) {
    if (!userRegistration.getPassword().equals(userRegistration.getPasswordConfirmation()))
        return "Password not match";

    else if (userService.getUser(userRegistration.getUsername()) != null)
        return "User already exists";

        userService.save(new User(userRegistration.getUsername(), userRegistration.getPassword(), Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
        return "User created";
    }

    @GetMapping
    public List<User> users(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/logouts")
    public void logout(@RequestParam(value = "access_token") String accessToken){
        tokenStore.removeAccessToken(tokenStore.readAccessToken(accessToken));
    }

    @GetMapping(value ="/getUsername")
    public String getUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
