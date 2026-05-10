package com.example.bookstore.controller;

import com.example.bookstore.model.User;
import com.example.bookstore.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body, HttpSession session) {
        String username = body.get("username");
        String email = body.get("email");
        String password = body.get("password");
        String fullName = body.get("fullName");

        if (username == null || username.isBlank() || email == null || email.isBlank()
                || password == null || password.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "All fields are required"));
        }
        if (password.length() < 6) {
            return ResponseEntity.badRequest().body(Map.of("error", "Password must be at least 6 characters"));
        }

        try {
            User user = userService.register(username, email, password, fullName != null ? fullName : username);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("fullName", user.getFullName());
            return ResponseEntity.ok(Map.of(
                "message", "Registration successful",
                "username", user.getUsername(),
                "fullName", user.getFullName()
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body, HttpSession session) {
        String username = body.get("username");
        String password = body.get("password");

        if (username == null || password == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username and password are required"));
        }

        Optional<User> user = userService.login(username, password);
        if (user.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid username or password"));
        }

        session.setAttribute("username", user.get().getUsername());
        session.setAttribute("fullName", user.get().getFullName());
        return ResponseEntity.ok(Map.of(
            "message", "Login successful",
            "username", user.get().getUsername(),
            "fullName", user.get().getFullName()
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return ResponseEntity.ok(Map.of("loggedIn", false));
        }
        return ResponseEntity.ok(Map.of(
            "loggedIn", true,
            "username", username,
            "fullName", session.getAttribute("fullName")
        ));
    }
}
