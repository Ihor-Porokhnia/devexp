package com.example.test.controller;

import com.example.test.model.User;
import com.example.test.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("{username}")
    public User getOne(@PathVariable("username") String username) {
        return userService.findByLogin(username);
    }

    @PostMapping
    public Object create(@RequestBody User user) {

        if (userService.findByLogin(user.getUsername()) != null) {
            return "This username already exist";
        }
        return userService.save(user);
    }

    @PutMapping
    public Object update(@RequestBody User user) throws NoSuchFieldException, IllegalAccessException {
        User userFromDb = userService.getOne(user.getId());
        BeanUtils.copyProperties(user, userFromDb, "id");
        return userService.save(userFromDb);
    }

    @DeleteMapping
    public void delete(@RequestBody User user) {
        userService.delete(userService.findByLogin(user.getUsername()));
    }
}
