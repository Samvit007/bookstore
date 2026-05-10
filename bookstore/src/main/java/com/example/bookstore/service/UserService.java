package com.example.bookstore.service;

import com.example.bookstore.model.User;
import com.example.bookstore.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        if (userRepository.count() == 0) {
            userRepository.save(new User(null, "admin", "admin@bookverse.com", "admin123", "Admin User"));
            userRepository.save(new User(null, "john", "john@example.com", "pass123", "John Doe"));
        }
    }

    public User register(String username, String email, String password, String fullName) {
        // Check if username or email already exists
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already taken");
        }
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered");
        }
        User user = new User(null, username, email, password, fullName);
        return userRepository.save(user);
    }

    public Optional<User> login(String username, String password) {
        return userRepository.findByUsername(username)
            .filter(u -> u.getPassword().equals(password));
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
