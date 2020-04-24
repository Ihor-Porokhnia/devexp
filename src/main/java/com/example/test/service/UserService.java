package com.example.test.service;

import com.example.test.dao.UserRepository;
import com.example.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements ParentService<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {


        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    public User findByLogin(String login) {
        return userRepository.findByUsername(login);
    }
}
