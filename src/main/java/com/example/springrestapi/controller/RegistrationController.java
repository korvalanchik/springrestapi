package com.example.springrestapi.controller;

import com.example.springrestapi.domain.User;
import com.example.springrestapi.model.RegistrationRequest;
import com.example.springrestapi.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        // Check if username already exists
        if (userRepository.findByUsername(registrationRequest.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        // Create new user entity
        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        // You may want to hash the password before saving it to the database
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        // Set other user properties as needed

        // Save the user to the database
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }
}