package com.example.test.controller;

import com.example.test.model.User;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String info() {
        return "Hello, send in this path post request to create new user. " +
                "Request should contain header: 'Content-Type: application/json' " +
                "and requests' body necessarily should contain 'username, login' " +
                "and optional parameters 'email, firstName, lastName'.";
    }

    @PostMapping
    public Object create(@RequestBody User user) {

        if (userService.findByLogin(user.getUsername()) != null) {
            return "This username already exist";
        }
        return userService.save(user);
    }
}
