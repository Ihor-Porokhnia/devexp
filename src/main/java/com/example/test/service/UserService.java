package com.example.test.service;

import com.example.test.dao.RoleRepository;
import com.example.test.dao.UserRepository;
import com.example.test.model.Role;
import com.example.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements ParentService<User> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        Role role = roleRepository.findByRoleName("USER");
        user.getRoles().add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    public User getOne(Integer id) {
        return userRepository.getOne(id);
    }
}